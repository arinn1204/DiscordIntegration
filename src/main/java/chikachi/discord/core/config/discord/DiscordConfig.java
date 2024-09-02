/*
 * Copyright (C) 2017 Chikachi This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General
 * Public License along with this program. If not, see http://www.gnu.org/licenses.
 */

package chikachi.discord.core.config.discord;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.dv8tion.jda.api.entities.User;

import com.google.gson.annotations.Since;

public class DiscordConfig {

    @Since(3.0)
    public String token = "";
    @Since(3.0)
    public boolean ignoresBots = true;
    @Since(3.0)
    public boolean allowLinking = true;
    @Since(3.0)
    public ArrayList<String> ignoresUsers = new ArrayList<>();
    @Since(3.0)
    public DiscordMainChannelConfig channels = new DiscordMainChannelConfig();

    public Set<String> ignoredCommands = new HashSet<>();

    public void fillFields() {
        if (this.token == null && System.getenv().containsKey("DISCORD_TOKEN")) {
            this.token = System.getenv("DISCORD_TOKEN");
        } else {
            this.token = "";
        }

        if (this.ignoresUsers == null) {
            this.ignoresUsers = new ArrayList<>();
        }

        if (this.channels == null) {
            this.channels = new DiscordMainChannelConfig();
        }
        this.channels.fillFields();

        if (ignoredCommands == null) {
            ignoredCommands = new HashSet<>();
        }

        ignoredCommands.addAll(Set.of(
           "trash_can"
        ));
    }

    public boolean isIgnoringUser(User user) {
        return ignoresUsers.contains(user.getId()) || ignoresUsers.contains(user.getName());
    }

    public ArrayList<CommandConfig> getCommandConfigs() {
        ArrayList<CommandConfig> list = new ArrayList<>();

        list.addAll(channels.generic.commands);
        channels.channels.forEach((key, value) -> list.addAll(value.commands));

        return list;
    }
}
