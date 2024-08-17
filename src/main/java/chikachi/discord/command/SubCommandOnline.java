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
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

import com.google.common.base.Joiner;

import chikachi.discord.DiscordCommandSender;

class SubCommandOnline {

    static void execute(ICommandSender sender) {
        boolean isDiscord = sender instanceof DiscordCommandSender;

        List<String> playerNames = new ArrayList<>();

        // noinspection unchecked
        List<EntityPlayerMP> players = new ArrayList<>();
        // noinspection unchecked
        MinecraftServer.getServer().getConfigurationManager().playerEntityList.stream()
                .filter(EntityPlayerMP.class::isInstance).forEach(player -> players.add((EntityPlayerMP) player));

        for (EntityPlayerMP player : players) {
            String playerName = player.getDisplayName();
            if (playerName.startsWith("@")) {
                continue;
            }
            playerNames.add(playerName);
        }

        int playersOnline = playerNames.size();
        if (playersOnline == 0) {
            sender.addChatMessage(new ChatComponentText("No players online"));
            return;
        }

        if (playersOnline == 1) {
            sender.addChatMessage(
                    new ChatComponentText(
                            String.format(
                                    isDiscord ? "Currently 1 player online: `%s`" : "Currently 1 player online: %s",
                                    playerNames.get(0))));
            return;
        }

        sender.addChatMessage(
                new ChatComponentText(
                        String.format(
                                isDiscord ? "Currently %d players online:\n`%s`" : "Currently %d players online:\n%s",
                                playersOnline,
                                Joiner.on(isDiscord ? "`, `" : ", ").join(playerNames))));
    }
}
