/*
 * Copyright (C) 2017 Chikachi This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General
 * Public License along with this program. If not, see http://www.gnu.org/licenses.
 */

package chikachi.discord.core;

import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import discordintegration.Tags;

public class DiscordIntegrationLogger {

    public static final Logger logger = new SimpleLoggerFactory().getLogger(CoreConstants.MODNAME);

    public static void Log(String message) {
        Log(message, false);
    }

    public static void warn(String message, Object... params) {
        logger.warn("[{}] {}", Tags.GRADLETOKEN_VERSION, message, params);
    }

    public static void info(String message) {
        logger.info("[{}] {}", Tags.GRADLETOKEN_VERSION, message);
    }

    public static void Log(String message, boolean warning) {
        if (warning) {

        } else {
            logger.info("[{}] {}", Tags.GRADLETOKEN_VERSION, message);
        }
    }
}
