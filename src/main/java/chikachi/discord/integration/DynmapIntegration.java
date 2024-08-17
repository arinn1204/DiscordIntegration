/// *
// * Copyright (C) 2017 Chikachi
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU Affero General Public License as
// * published by the Free Software Foundation, either version 3 of the
// * License, or (at your option) any later version.
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// * GNU Affero General Public License for more details.
// * You should have received a copy of the GNU Affero General Public License
// * along with this program. If not, see http://www.gnu.org/licenses.
// */
//
// package chikachi.discord.integration;
//
// import chikachi.discord.core.DiscordClient;
// import chikachi.discord.core.Message;
// import chikachi.discord.core.config.ConfigWrapper;
// import chikachi.discord.core.config.Configuration;
// import chikachi.discord.core.config.discord.DiscordChannelGenericConfig;
// import chikachi.discord.core.config.discord.DiscordConfig;
// import chikachi.discord.core.config.minecraft.MinecraftGenericConfig;
// import cpw.mods.fml.common.Optional;
// import net.dv8tion.jda.api.entities.channel.ChannelType;
// import net.dv8tion.jda.api.events.Event;
// import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
// import net.dv8tion.jda.api.hooks.EventListener;
//
// import java.util.ArrayList;
// import java.util.HashMap;
//
// @SuppressWarnings("unused")
// @Optional.Interface(iface = "org.dynmap.DynmapCommonAPIListener", modid = "Dynmap")
// public class DynmapIntegration extends DynmapCommonAPIListener implements EventListener {
// private DynmapCommonAPI dynmapCommonAPI;
//
// public DynmapIntegration() {
// DynmapCommonAPIListener.register(this);
// }
//
// @Override
// @Optional.Method(modid = "Dynmap")
// public void apiEnabled(DynmapCommonAPI dynmapCommonAPI) {
// this.dynmapCommonAPI = dynmapCommonAPI;
// DiscordClient.getInstance().addEventListener(this);
// }
//
// @Override
// @Optional.Method(modid = "Dynmap")
// public void apiDisabled(DynmapCommonAPI api) {
// super.apiDisabled(api);
// this.dynmapCommonAPI = null;
// DiscordClient.getInstance().removeEventListener(this);
// }
//
// @Override
// @Optional.Method(modid = "Dynmap")
// public boolean webChatEvent(String source, String name, String message) {
// if (Configuration.getConfig().minecraft.integrations.dynmapEnabled) {
// MinecraftGenericConfig genericConfig = Configuration.getConfig().minecraft.dimensions.generic;
//
// HashMap<String, String> arguments = new HashMap<>();
// arguments.put("MESSAGE", message);
//
// DiscordClient.getInstance().broadcast(
// new Message()
// .setAuthor("[Dynmap]" + (name != null && name.trim().length() > 0 ? " " + name : ""))
// .setMessage(genericConfig.messages.chatMessage)
// .setArguments(arguments),
// genericConfig.relayChat.getChannels(
// genericConfig.discordChannel
// )
// );
// }
// return true;
// }
//
// @Override
// public void onEvent(Event event) {
// if (this.dynmapCommonAPI == null) {
// return;
// }
//
// if (event instanceof MessageReceivedEvent) {
// MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
//
// ConfigWrapper config = Configuration.getConfig();
// DiscordConfig discordConfig = config.discord;
//
// // Ignore bots
// if (discordConfig.ignoresBots && messageReceivedEvent.getAuthor().isBot()) {
// return;
// }
//
// // Ignore self
// if (messageReceivedEvent.getAuthor().getId().equals(DiscordClient.getInstance().getSelf().getId())) {
// return;
// }
//
// // Ignore specified users
// if (discordConfig.isIgnoringUser(messageReceivedEvent.getAuthor())) {
// return;
// }
//
// String content = messageReceivedEvent.getMessage().getContentDisplay().trim();
//
// if (messageReceivedEvent.getChannelType() == ChannelType.TEXT) {
// Long channelId = messageReceivedEvent.getChannel().getIdLong();
//
// DiscordChannelGenericConfig channelConfig;
// ArrayList<Integer> dimensions;
// boolean stripMinecraftCodes = discordConfig.channels.generic.stripMinecraftCodes;
// if (!discordConfig.channels.channels.containsKey(channelId)) {
// // Don't relay messages from channels not configured
// return;
// }
//
// this.dynmapCommonAPI.sendBroadcastToWeb(
// messageReceivedEvent.getAuthor().getName(),
// messageReceivedEvent.getMessage().getContentStripped()
// );
// }
// }
// }
// }
