/*
 * Copyright (C) 2017 Chikachi This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General
 * Public License along with this program. If not, see http://www.gnu.org/licenses.
 */

package chikachi.discord;

import static chikachi.discord.core.DiscordIntegrationLogger.getLogger;

import net.minecraftforge.common.MinecraftForge;

import org.slf4j.Logger;

import chikachi.discord.command.CommandDiscord;
import chikachi.discord.core.CoreConstants;
import chikachi.discord.core.CoreUtils;
import chikachi.discord.core.DiscordClient;
import chikachi.discord.core.Proxy;
import chikachi.discord.listener.DiscordListener;
import chikachi.discord.listener.MinecraftListener;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import discordintegration.Tags;

@Mod(
        modid = CoreConstants.MODID,
        name = CoreConstants.MODNAME,
        version = Tags.GRADLETOKEN_VERSION,
        acceptableRemoteVersions = "*")
public class DiscordIntegration {

    public static final Logger logger = getLogger(DiscordIntegration.class);

    @Mod.Instance
    static DiscordIntegration instance;

    private static Proxy proxy = new Proxy();
    private MinecraftListener minecraftListener = new MinecraftListener();

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        logger.trace("onPreInit");
        proxy.onPreInit(event.getModConfigurationDirectory());

        CoreUtils.addPatterns();

        MinecraftForge.EVENT_BUS.register(minecraftListener);
        FMLCommonHandler.instance().bus().register(minecraftListener);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        logger.trace("onPostInit");
        // event.buildSoftDependProxy("Dynmap", "chikachi.discord.integration.DynmapIntegration");
    }

    @Mod.EventHandler
    public void onServerAboutToStart(FMLServerAboutToStartEvent event) {
        logger.trace("onServerAboutToStart");
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        logger.trace("onServerStarting");
        proxy.onServerStarting();

        DiscordClient.getInstance().addEventListener(new DiscordListener());

        event.registerServerCommand(new CommandDiscord());
    }

    @Mod.EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
        logger.trace("onServerStarted");
        proxy.onServerStarted();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        logger.info("onServerStopping");
        proxy.onServerStopping();
    }

    @Mod.EventHandler
    public void onServerStopped(FMLServerStoppedEvent event) {
        logger.info("onServerStopped");
        proxy.onServerStopped();
    }

    @Mod.EventHandler
    public void imcReceived(FMLInterModComms.IMCEvent event) {
        for (FMLInterModComms.IMCMessage imcMessage : event.getMessages()) {
            IMCHandler.onMessageReceived(imcMessage);
        }
    }
}
