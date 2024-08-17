package chikachi.discord.core.test.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FakeUser implements User {

    private final long id;
    private final String name;
    private final String discriminator;
    private final List<Role> roles = new ArrayList<>();

    public FakeUser(long id, String nameWithDiscriminator) {
        this.id = id;
        String[] parts = nameWithDiscriminator.split("#");
        this.name = parts[0];
        this.discriminator = parts[1];
    }

    public FakeUser(long id, String name, String discriminator) {
        this.id = id;
        this.name = name;
        this.discriminator = discriminator;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Nullable
    @Override
    public String getGlobalName() {
        return "";
    }

    @Override
    public String getDiscriminator() {
        return this.discriminator;
    }

    @Override
    public String getAvatarId() {
        return null;
    }

    @Override
    public String getAvatarUrl() {
        return null;
    }

    @Override
    public String getDefaultAvatarId() {
        return null;
    }

    @Override
    public String getDefaultAvatarUrl() {
        return null;
    }

    @Override
    public String getEffectiveAvatarUrl() {
        return null;
    }

    @NotNull
    @Override
    public CacheRestAction<Profile> retrieveProfile() {
        return null;
    }

    @NotNull
    @Override
    public String getAsTag() {
        return "";
    }

    @Override
    public boolean hasPrivateChannel() {
        return false;
    }

    @Override
    public CacheRestAction<PrivateChannel> openPrivateChannel() {
        return null;
    }

    @Override
    public List<Guild> getMutualGuilds() {
        return null;
    }

    @Override
    public boolean isBot() {
        return false;
    }

    @Override
    public boolean isSystem() {
        return false;
    }

    @Override
    public JDA getJDA() {
        return null;
    }

    @NotNull
    @Override
    public EnumSet<UserFlag> getFlags() {
        return null;
    }

    @Override
    public int getFlagsRaw() {
        return 0;
    }

    @Override
    public String getAsMention() {
        return "<@" + this.id + ">";
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    List<Role> getRoles() {
        return this.roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
