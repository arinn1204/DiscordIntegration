package chikachi.discord.core.test.impl;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ClientType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.attribute.IPermissionContainer;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.entities.channel.unions.DefaultGuildChannelUnion;
import net.dv8tion.jda.api.entities.emoji.RichCustomEmoji;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FakeMember implements Member {

    private final FakeGuild guild;
    private final FakeUser user;

    FakeMember(FakeGuild guild, FakeUser user) {
        this.guild = guild;
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public Guild getGuild() {
        return this.guild;
    }

    @NotNull
    @Override
    public EnumSet<Permission> getPermissions() {
        return null;
    }

    @NotNull
    @Override
    public EnumSet<Permission> getPermissions(@NotNull GuildChannel channel) {
        return null;
    }

    @NotNull
    @Override
    public EnumSet<Permission> getPermissionsExplicit() {
        return null;
    }

    @NotNull
    @Override
    public EnumSet<Permission> getPermissionsExplicit(@NotNull GuildChannel channel) {
        return null;
    }

    @Override
    public boolean hasPermission(@NotNull Permission... permissions) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Collection<Permission> permissions) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull GuildChannel channel, @NotNull Permission... permissions) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull GuildChannel channel, @NotNull Collection<Permission> permissions) {
        return false;
    }

    @Override
    public boolean canSync(@NotNull IPermissionContainer targetChannel, @NotNull IPermissionContainer syncSource) {
        return false;
    }

    @Override
    public boolean canSync(@NotNull IPermissionContainer channel) {
        return false;
    }

    @NotNull
    @Override
    public JDA getJDA() {
        return null;
    }

    @NotNull
    @Override
    public OffsetDateTime getTimeJoined() {
        return null;
    }

    @Override
    public boolean hasTimeJoined() {
        return false;
    }

    @Nullable
    @Override
    public OffsetDateTime getTimeBoosted() {
        return null;
    }

    @Override
    public boolean isBoosting() {
        return false;
    }

    @Nullable
    @Override
    public OffsetDateTime getTimeOutEnd() {
        return null;
    }

    @Nullable
    @Override
    public GuildVoiceState getVoiceState() {
        return null;
    }

    @NotNull
    @Override
    public List<Activity> getActivities() {
        return Collections.EMPTY_LIST;
    }

    @NotNull
    @Override
    public OnlineStatus getOnlineStatus() {
        return null;
    }

    @NotNull
    @Override
    public OnlineStatus getOnlineStatus(@NotNull ClientType type) {
        return null;
    }

    @NotNull
    @Override
    public EnumSet<ClientType> getActiveClients() {
        return null;
    }

    @Nullable
    @Override
    public String getNickname() {
        return "";
    }

    @NotNull
    @Override
    public String getEffectiveName() {
        return "";
    }

    @Nullable
    @Override
    public String getAvatarId() {
        return "";
    }

    @Override
    public List<Role> getRoles() {
        return this.user.getRoles();
    }

    @Nullable
    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int getColorRaw() {
        return 0;
    }

    @Override
    public int getFlagsRaw() {
        return 0;
    }

    @Override
    public boolean canInteract(@NotNull Member member) {
        return false;
    }

    @Override
    public boolean canInteract(@NotNull Role role) {
        return false;
    }

    @Override
    public boolean canInteract(@NotNull RichCustomEmoji emoji) {
        return false;
    }

    @Override
    public boolean isOwner() {
        return false;
    }

    @Override
    public boolean isPending() {
        return false;
    }

    @Nullable
    @Override
    public DefaultGuildChannelUnion getDefaultChannel() {
        return null;
    }

    @Override
    public String getAsMention() {
        return this.user.getAsMention();
    }

    @NotNull
    @Override
    public String getDefaultAvatarId() {
        return "";
    }

    @Override
    public long getIdLong() {
        return 0;
    }
}
