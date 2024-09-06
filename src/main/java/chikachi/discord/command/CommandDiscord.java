/*
 * Copyright (C) 2017 Chikachi This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General
 * Public License along with this program. If not, see http://www.gnu.org/licenses.
 */

package chikachi.discord.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.ParametersAreNonnullByDefault;

import chikachi.discord.core.DiscordIntegrationLogger;
import net.dv8tion.jda.api.entities.User;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import org.jetbrains.annotations.NotNull;

import chikachi.discord.core.DiscordClient;
import chikachi.discord.core.MinecraftFormattingCodes;
import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.linking.LinkingRequest;
import org.slf4j.Logger;

@ParametersAreNonnullByDefault
public class CommandDiscord extends CommandBase {
    private static final Logger log = DiscordIntegrationLogger.getLogger(CommandDiscord.class);

    @Override
    public String getCommandName() {
        return "discord";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/discord <config|online" + (Configuration.getConfig().discord.allowLinking ? "|link|unlink" : "")
                + "|tps|unstuck|uptime> [options]";
    }

    @Override
    public List<String> getCommandAliases() {
        return new ArrayList<>();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            return;
        }

        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args));
        String commandName = argsList.remove(0).toLowerCase();
        UUID minecraftUUID;

        switch (commandName) {
            case "config":
                SubCommandConfig.execute(sender, argsList);
                break;
            case "online":
                SubCommandOnline.execute(sender);
                break;
            case "link":
                if (!Configuration.getConfig().discord.allowLinking) {
                    sender.addChatMessage(
                            new ChatComponentText(MinecraftFormattingCodes.DARK_RED + "Linking is not enabled"));
                    break;
                }

                if (!(sender instanceof EntityPlayer)) {
                    sender.addChatMessage(
                            new ChatComponentText(MinecraftFormattingCodes.DARK_RED + "You need to be a player"));
                    break;
                }

                minecraftUUID = ((EntityPlayer) sender).getGameProfile().getId();
                Long discordId = Configuration.getLinking().getDiscordId(minecraftUUID);

                if (discordId != null) {
                    User discordUser = DiscordClient.getInstance().getJda().getUserById(discordId);
                    sender.addChatMessage(
                            new ChatComponentText(
                                    String.format(
                                            MinecraftFormattingCodes.YELLOW + "You're already linked to %s#%s",
                                            discordUser.getName(),
                                            discordUser.getDiscriminator())));
                    break;
                }

                if (argsList.size() == 0) {
                    sender.addChatMessage(new ChatComponentText(MinecraftFormattingCodes.DARK_RED + "Missing code"));
                    break;
                }

                Optional<LinkingRequest> requestOptional = Configuration.getLinking()
                        .getRequestByCode(argsList.remove(0));
                if (requestOptional.isPresent()) {
                    LinkingRequest request = requestOptional.get();

                    if (request.hasExpired()) {
                        sender.addChatMessage(
                                new ChatComponentText(
                                        MinecraftFormattingCodes.DARK_RED + "Linking request has expired"));
                        break;
                    }

                    request.executeLinking(minecraftUUID);
                    sender.addChatMessage(new ChatComponentText(MinecraftFormattingCodes.GREEN + "Linked"));
                } else {
                    sender.addChatMessage(
                            new ChatComponentText(MinecraftFormattingCodes.DARK_RED + "Linking request not found"));
                    break;
                }
                break;
            case "unlink":
                if (!Configuration.getConfig().discord.allowLinking) {
                    sender.addChatMessage(
                            new ChatComponentText(MinecraftFormattingCodes.DARK_RED + "Linking is not enabled"));
                    break;
                }

                if (!(sender instanceof EntityPlayer)) {
                    sender.addChatMessage(
                            new ChatComponentText(MinecraftFormattingCodes.DARK_RED + "You need to be a player"));
                    break;
                }

                minecraftUUID = ((EntityPlayer) sender).getGameProfile().getId();

                if (Configuration.getLinking().getDiscordId(minecraftUUID) == null) {
                    sender.addChatMessage(
                            new ChatComponentText(MinecraftFormattingCodes.DARK_RED + "You aren't linked"));
                    break;
                }

                Configuration.getLinking().removeLink(minecraftUUID);
                sender.addChatMessage(new ChatComponentText(MinecraftFormattingCodes.GREEN + "Unlinked"));
                break;
            case "tps":
                SubCommandTps.execute(sender, argsList);
                break;
            case "unstuck":
                SubCommandUnstuck.execute(sender, argsList);
                break;
            case "uptime":
                SubCommandUptime.execute(sender);
                break;
            default:
                sender.addChatMessage(new ChatComponentText("Unknown command"));
                break;
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        boolean canUseCommand = sender.canCommandSenderUseCommand(4, getCommandName());
        log.info("User {} can{} execute {}", sender.getCommandSenderName(), canUseCommand ? "" : " not", getCommandName());
        return canUseCommand;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return args.length > 1 && args[0].equalsIgnoreCase("unstuck") && index == 1;
    }

    @Override
    public int compareTo(@NotNull ICommand o) {
        return super.compareTo(o);
    }
}
