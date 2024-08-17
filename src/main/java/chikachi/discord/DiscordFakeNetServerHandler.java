/*
 * Copyright (C) 2017 Chikachi This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General
 * Public License along with this program. If not, see http://www.gnu.org/licenses.
 */

package chikachi.discord;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.server.MinecraftServer;

@ParametersAreNonnullByDefault
public class DiscordFakeNetServerHandler extends NetHandlerPlayServer {

    DiscordFakeNetServerHandler(MinecraftServer minecraftServer, NetworkManager networkManager, EntityPlayerMP player) {
        super(minecraftServer, networkManager, player);
    }

    @Override
    public void sendPacket(Packet packet) {}

    @Override
    public void onNetworkTick() {}

    @Override
    public void kickPlayerFromServer(String reason) {

    }

    @Override
    public void processKeepAlive(C00PacketKeepAlive packet) {

    }

    @Override
    public void processClickWindow(C0EPacketClickWindow packet) {

    }

    @Override
    public void processPlayerBlockPlacement(C08PacketPlayerBlockPlacement packet) {

    }

    @Override
    public void processVanilla250Packet(C17PacketCustomPayload packet) {

    }
}
