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

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import chikachi.discord.core.DiscordClient;
import chikachi.discord.core.config.Configuration;

public class SubCommandConfig {

    public static void execute(ICommandSender sender, ArrayList<String> args) {
        String subCommand = args.size() > 0 ? args.remove(0).toLowerCase() : "";

        switch (subCommand) {
            case "load":
            case "reload":
                String oldToken = Configuration.getConfig().discord.token;

                Configuration.loadConfig();

                if (!DiscordClient.getInstance().isConnected()) {
                    // Connect to Discord, if not already connected
                    DiscordClient.getInstance().connect();
                } else if (!oldToken.equals(Configuration.getConfig().discord.token)) {
                    // Connect with the new token
                    DiscordClient.getInstance().disconnect();
                    DiscordClient.getInstance().connect();
                }

                sender.addChatMessage(new ChatComponentText("Config reloaded"));
                break;
            case "save":
                Configuration.saveConfig();

                sender.addChatMessage(new ChatComponentText("Config saved"));
                break;
            case "clean":
                Configuration.saveClean();

                sender.addChatMessage(new ChatComponentText("Clean config saved"));
                break;
            default:
                sender.addChatMessage(new ChatComponentText("Unknown command"));
                break;
        }
    }
}
