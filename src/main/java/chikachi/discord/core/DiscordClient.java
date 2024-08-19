/*
 * Copyright (C) 2017 Chikachi This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General
 * Public License along with this program. If not, see http://www.gnu.org/licenses.
 */

package chikachi.discord.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import org.slf4j.Logger;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.minecraft.MinecraftConfig;
import chikachi.discord.core.config.types.MessageConfig;

public class DiscordClient extends ListenerAdapter {

    private static final Logger log = DiscordIntegrationLogger.getLogger(DiscordClient.class);

    private static DiscordClient instance;
    private ArrayList<EventListener> eventListeners = new ArrayList<>();
    private boolean isReady = false;
    private JDA jda;

    private DiscordClient() {}

    public static DiscordClient getInstance() {
        if (instance == null) {
            instance = new DiscordClient();
        }

        return instance;
    }

    @Override
    public void onReady(ReadyEvent event) {
        log.info("Logged in as {}", getSelf().getName());

        this.isReady = true;

        MinecraftConfig minecraftConfig = Configuration.getConfig().minecraft;

        DiscordClient.getInstance().broadcast(
                new Message(minecraftConfig.dimensions.generic.messages.serverStart),
                minecraftConfig.dimensions.generic.relayServerStart
                        .getChannels(minecraftConfig.dimensions.generic.discordChannel));

        this.isReady = false;
    }

    public void connect() {
        connect(false);
    }

    @SuppressWarnings("SameParameterValue")
    private void connect(boolean noMessage) {
        if (this.jda != null) {
            if (noMessage) {
                log.warn("Is already connected");
            }
            return;
        }

        String token = Configuration.getConfig().discord.token;

        if (token == null || token.isEmpty()) {
            if (noMessage) {
                log.error("Missing token");
            }
            return;
        }

        try {
            JDABuilder builder = JDABuilder.createLight(token)
                    .enableIntents(
                            GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.GUILD_PRESENCES,
                            GatewayIntent.MESSAGE_CONTENT)
                    .setBulkDeleteSplittingEnabled(false).setMemberCachePolicy(MemberCachePolicy.ONLINE)
                    .enableCache(CacheFlag.getPrivileged()).addEventListeners(this);

            for (EventListener eventListener : this.eventListeners) {
                builder.addEventListeners(eventListener);
            }

            this.jda = builder.build();
        } catch (Exception e) {
            log.error("Failed to connect to Discord", e);
        }
    }

    public void addEventListener(EventListener eventListener) {
        if (eventListener != null) {
            if (this.eventListeners.contains(eventListener)) {
                return;
            }

            this.eventListeners.add(eventListener);

            if (this.jda != null) {
                this.jda.addEventListener(eventListener);
            }
        }
    }

    public void removeEventListener(EventListener eventListener) {
        if (eventListener != null) {
            if (!this.eventListeners.contains(eventListener)) {
                return;
            }

            this.eventListeners.remove(eventListener);

            if (this.jda != null) {
                this.jda.removeEventListener(eventListener);
            }
        }
    }

    public boolean isConnected() {
        return this.jda != null && (this.isReady || this.jda.getStatus() == JDA.Status.CONNECTED);
    }

    public void disconnect() {
        disconnect(false);
    }

    void disconnect(boolean noMessage) {
        if (this.jda == null) {
            if (!noMessage) {
                log.warn("Is already disconnected");
            }
            return;
        }

        this.jda.shutdown();
        if (!noMessage) {
            log.warn("Disconnected from Discord");
        }
        this.jda = null;
    }

    public JDA getJda() {
        return this.jda;
    }

    public SelfUser getSelf() {
        if (this.jda == null) {
            return null;
        }

        return this.jda.getSelfUser();
    }

    public User getUser(long userId) {
        if (this.jda == null) {
            return null;
        }

        return this.jda.getUserById(userId);
    }

    void broadcast(MessageConfig message, List<Long> channels) {
        broadcast(new Message(message), channels);
    }

    public void broadcast(Message message, Long... channels) {
        broadcast(message, Arrays.asList(channels));
    }

    public void broadcast(Message message, List<Long> channels) {
        if (channels == null || channels.size() == 0
                || this.jda == null
                || (!this.isReady && this.jda.getStatus() != JDA.Status.CONNECTED)) {
            return;
        }

        for (Long channelId : channels) {
            TextChannel channel = this.jda.getTextChannelById(channelId);
            if (channel == null) {
                log.info("Could not find channel {}", channelId);
            } else {
                if (!channel.canTalk()) {
                    log.warn("Missing permission to write in channel {} ({})", channel.getName(), channelId);
                    continue;
                }

                if (Configuration.getConfig().discord.channels.channels.containsKey(channelId)) {
                    if (!Configuration.getConfig().discord.channels.channels.get(channelId).webhook.trim().isEmpty()) {
                        WebhookMessage webhookMessage = message.toWebhook(channel);
                        if (webhookMessage.queue(this.jda, channelId)) {
                            continue;
                        }
                    }
                }

                String text = message.getFormattedTextDiscord(channel);

                if (text.length() > 2000) {
                    text = text.substring(0, 1997) + "...";
                }

                channel.sendMessage(text).queue();
            }
        }
    }
}
