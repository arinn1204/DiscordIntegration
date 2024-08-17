package chikachi.discord.core.test.impl;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.BulkBanResponse;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.GuildWelcomeScreen;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.ScheduledEvent;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.entities.VanityInvite;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.entities.automod.AutoModRule;
import net.dv8tion.jda.api.entities.automod.build.AutoModRuleData;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.ForumChannel;
import net.dv8tion.jda.api.entities.channel.concrete.MediaChannel;
import net.dv8tion.jda.api.entities.channel.concrete.NewsChannel;
import net.dv8tion.jda.api.entities.channel.concrete.StageChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.entities.channel.unions.DefaultGuildChannelUnion;
import net.dv8tion.jda.api.entities.emoji.RichCustomEmoji;
import net.dv8tion.jda.api.entities.sticker.GuildSticker;
import net.dv8tion.jda.api.entities.sticker.StickerSnowflake;
import net.dv8tion.jda.api.entities.templates.Template;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.PrivilegeConfig;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.privileges.IntegrationPrivilege;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.AutoModRuleManager;
import net.dv8tion.jda.api.managers.GuildManager;
import net.dv8tion.jda.api.managers.GuildStickerManager;
import net.dv8tion.jda.api.managers.GuildWelcomeScreenManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.dv8tion.jda.api.requests.restaction.CommandEditAction;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.requests.restaction.MemberAction;
import net.dv8tion.jda.api.requests.restaction.RoleAction;
import net.dv8tion.jda.api.requests.restaction.ScheduledEventAction;
import net.dv8tion.jda.api.requests.restaction.order.CategoryOrderAction;
import net.dv8tion.jda.api.requests.restaction.order.ChannelOrderAction;
import net.dv8tion.jda.api.requests.restaction.order.RoleOrderAction;
import net.dv8tion.jda.api.requests.restaction.pagination.AuditLogPaginationAction;
import net.dv8tion.jda.api.requests.restaction.pagination.BanPaginationAction;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.cache.MemberCacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.api.utils.cache.SortedChannelCacheView;
import net.dv8tion.jda.api.utils.cache.SortedSnowflakeCacheView;
import net.dv8tion.jda.api.utils.concurrent.Task;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings({ "DataFlowIssue", "unchecked", "NullableProblems" })
public class FakeGuild implements Guild {

    @Override
    public RestAction<List<Command>> retrieveCommands(boolean withLocalizations) {
        return null;
    }

    @Override
    public RestAction<Command> retrieveCommandById(String id) {
        return null;
    }

    @Override
    public RestAction<Command> upsertCommand(CommandData command) {
        return null;
    }

    @Override
    public CommandListUpdateAction updateCommands() {
        return null;
    }

    @Override
    public CommandEditAction editCommandById(String id) {
        return null;
    }

    @Override
    public RestAction<Void> deleteCommandById(String commandId) {
        return null;
    }

    @Override
    public RestAction<List<IntegrationPrivilege>> retrieveIntegrationPrivilegesById(String targetId) {
        return null;
    }

    @Override
    public RestAction<PrivilegeConfig> retrieveCommandPrivileges() {
        return null;
    }

    @Override
    public RestAction<EnumSet<Region>> retrieveRegions() {
        return null;
    }

    @Override
    public RestAction<EnumSet<Region>> retrieveRegions(boolean includeDeprecated) {
        return null;
    }

    @Override
    public RestAction<List<AutoModRule>> retrieveAutoModRules() {
        return null;
    }

    @Override
    public RestAction<AutoModRule> retrieveAutoModRuleById(String id) {
        return null;
    }

    @Override
    public AuditableRestAction<AutoModRule> createAutoModRule(AutoModRuleData data) {
        return null;
    }

    @Override
    public AutoModRuleManager modifyAutoModRuleById(String id) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> deleteAutoModRuleById(String id) {
        return null;
    }

    @Override
    public MemberAction addMember(String accessToken, UserSnowflake user) {
        return null;
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public void pruneMemberCache() {

    }

    @Override
    public boolean unloadMember(long userId) {
        return false;
    }

    @Override
    public int getMemberCount() {
        return 0;
    }

    @Override
    public String getName() {
        return "FakeGuild";
    }

    @Nullable
    @Override
    public String getIconId() {
        return "";
    }

    @Override
    public Set<String> getFeatures() {
        return Collections.EMPTY_SET;
    }

    @Nullable
    @Override
    public String getSplashId() {
        return "";
    }

    @Nullable
    @Override
    public String getVanityCode() {
        return "";
    }

    @Override
    public RestAction<VanityInvite> retrieveVanityInvite() {
        return null;
    }

    @Nullable
    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public DiscordLocale getLocale() {
        return null;
    }

    @Nullable
    @Override
    public String getBannerId() {
        return "";
    }

    @Override
    public BoostTier getBoostTier() {
        return null;
    }

    @Override
    public int getBoostCount() {
        return 0;
    }

    @Override
    public List<Member> getBoosters() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public int getMaxMembers() {
        return 0;
    }

    @Override
    public int getMaxPresences() {
        return 0;
    }

    @Override
    public RestAction<MetaData> retrieveMetaData() {
        return null;
    }

    @Nullable
    @Override
    public VoiceChannel getAfkChannel() {
        return null;
    }

    @Nullable
    @Override
    public TextChannel getSystemChannel() {
        return null;
    }

    @Nullable
    @Override
    public TextChannel getRulesChannel() {
        return null;
    }

    @Nullable
    @Override
    public TextChannel getCommunityUpdatesChannel() {
        return null;
    }

    @Nullable
    @Override
    public Member getOwner() {
        return null;
    }

    @Override
    public long getOwnerIdLong() {
        return 0;
    }

    @Override
    public Timeout getAfkTimeout() {
        return null;
    }

    @Override
    public boolean isMember(UserSnowflake user) {
        return false;
    }

    @Override
    public Member getSelfMember() {
        return null;
    }

    @Override
    public NSFWLevel getNSFWLevel() {
        return null;
    }

    @Nullable
    @Override
    public Member getMember(UserSnowflake user) {
        return null;
    }

    @Override
    public MemberCacheView getMemberCache() {
        return null;
    }

    @Override
    public SortedSnowflakeCacheView<ScheduledEvent> getScheduledEventCache() {
        return null;
    }

    @Override
    public SortedSnowflakeCacheView<StageChannel> getStageChannelCache() {
        return null;
    }

    @Override
    public SortedSnowflakeCacheView<ThreadChannel> getThreadChannelCache() {
        return null;
    }

    @Override
    public SortedSnowflakeCacheView<Category> getCategoryCache() {
        return null;
    }

    @Override
    public SortedSnowflakeCacheView<TextChannel> getTextChannelCache() {
        return null;
    }

    @Override
    public SortedSnowflakeCacheView<NewsChannel> getNewsChannelCache() {
        return null;
    }

    @Override
    public SortedSnowflakeCacheView<VoiceChannel> getVoiceChannelCache() {
        return null;
    }

    @Override
    public SortedSnowflakeCacheView<ForumChannel> getForumChannelCache() {
        return null;
    }

    @Override
    public SnowflakeCacheView<MediaChannel> getMediaChannelCache() {
        return null;
    }

    @Override
    public SortedChannelCacheView<GuildChannel> getChannelCache() {
        return null;
    }

    @Override
    public List<GuildChannel> getChannels(boolean includeHidden) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public SortedSnowflakeCacheView<Role> getRoleCache() {
        return null;
    }

    @Override
    public SnowflakeCacheView<RichCustomEmoji> getEmojiCache() {
        return null;
    }

    @Override
    public SnowflakeCacheView<GuildSticker> getStickerCache() {
        return null;
    }

    @Override
    public RestAction<List<RichCustomEmoji>> retrieveEmojis() {
        return null;
    }

    @Override
    public RestAction<RichCustomEmoji> retrieveEmojiById(String id) {
        return null;
    }

    @Override
    public RestAction<List<GuildSticker>> retrieveStickers() {
        return null;
    }

    @Override
    public RestAction<GuildSticker> retrieveSticker(StickerSnowflake sticker) {
        return null;
    }

    @Override
    public GuildStickerManager editSticker(StickerSnowflake sticker) {
        return null;
    }

    @Override
    public BanPaginationAction retrieveBanList() {
        return null;
    }

    @Override
    public RestAction<Ban> retrieveBan(UserSnowflake user) {
        return null;
    }

    @Override
    public RestAction<Integer> retrievePrunableMemberCount(int days) {
        return null;
    }

    @Override
    public Role getPublicRole() {
        return null;
    }

    @Nullable
    @Override
    public DefaultGuildChannelUnion getDefaultChannel() {
        return null;
    }

    @Override
    public GuildManager getManager() {
        return null;
    }

    @Override
    public boolean isBoostProgressBarEnabled() {
        return false;
    }

    @Override
    public AuditLogPaginationAction retrieveAuditLogs() {
        return null;
    }

    @Override
    public RestAction<Void> leave() {
        return null;
    }

    @Override
    public RestAction<Void> delete() {
        return null;
    }

    @Override
    public RestAction<Void> delete(@Nullable String mfaCode) {
        return null;
    }

    @Override
    public AudioManager getAudioManager() {
        return null;
    }

    @Override
    public Task<Void> requestToSpeak() {
        return null;
    }

    @Override
    public Task<Void> cancelRequestToSpeak() {
        return null;
    }

    @Override
    public JDA getJDA() {
        return null;
    }

    @Override
    public RestAction<List<Invite>> retrieveInvites() {
        return null;
    }

    @Override
    public RestAction<List<Template>> retrieveTemplates() {
        return null;
    }

    @Override
    public RestAction<Template> createTemplate(String name, @Nullable String description) {
        return null;
    }

    @Override
    public RestAction<List<Webhook>> retrieveWebhooks() {
        return null;
    }

    @Override
    public RestAction<GuildWelcomeScreen> retrieveWelcomeScreen() {
        return null;
    }

    @Override
    public List<GuildVoiceState> getVoiceStates() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public VerificationLevel getVerificationLevel() {
        return null;
    }

    @Override
    public NotificationLevel getDefaultNotificationLevel() {
        return null;
    }

    @Override
    public MFALevel getRequiredMFALevel() {
        return null;
    }

    @Override
    public ExplicitContentLevel getExplicitContentLevel() {
        return null;
    }

    @Override
    public Task<Void> loadMembers(Consumer<Member> callback) {
        return null;
    }

    @Override
    public CacheRestAction<Member> retrieveMemberById(long id) {
        return null;
    }

    @Override
    public Task<List<Member>> retrieveMembersByIds(boolean includePresence, long... ids) {
        return null;
    }

    @Override
    public Task<List<Member>> retrieveMembersByPrefix(String prefix, int limit) {
        return null;
    }

    @Override
    public RestAction<List<ThreadChannel>> retrieveActiveThreads() {
        return null;
    }

    @Override
    public CacheRestAction<ScheduledEvent> retrieveScheduledEventById(String id) {
        return null;
    }

    @Override
    public RestAction<Void> moveVoiceMember(Member member, @Nullable AudioChannel audioChannel) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> modifyNickname(Member member, @Nullable String nickname) {
        return null;
    }

    @Override
    public AuditableRestAction<Integer> prune(int days, boolean wait, Role... roles) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> kick(UserSnowflake user) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> ban(UserSnowflake user, int deletionTimeframe, TimeUnit unit) {
        return null;
    }

    @Override
    public AuditableRestAction<BulkBanResponse> ban(Collection<? extends UserSnowflake> users,
            @Nullable Duration deletionTime) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> unban(UserSnowflake user) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> timeoutUntil(UserSnowflake user, TemporalAccessor temporal) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> removeTimeout(UserSnowflake user) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> deafen(UserSnowflake user, boolean deafen) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> mute(UserSnowflake user, boolean mute) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> addRoleToMember(UserSnowflake user, Role role) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> removeRoleFromMember(UserSnowflake user, Role role) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> modifyMemberRoles(Member member, @Nullable Collection<Role> rolesToAdd,
            @Nullable Collection<Role> rolesToRemove) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> modifyMemberRoles(Member member, Collection<Role> roles) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> transferOwnership(Member newOwner) {
        return null;
    }

    @Override
    public ChannelAction<TextChannel> createTextChannel(String name, @Nullable Category parent) {
        return null;
    }

    @Override
    public ChannelAction<NewsChannel> createNewsChannel(String name, @Nullable Category parent) {
        return null;
    }

    @Override
    public ChannelAction<VoiceChannel> createVoiceChannel(String name, @Nullable Category parent) {
        return null;
    }

    @Override
    public ChannelAction<StageChannel> createStageChannel(String name, @Nullable Category parent) {
        return null;
    }

    @Override
    public ChannelAction<ForumChannel> createForumChannel(String name, @Nullable Category parent) {
        return null;
    }

    @Override
    public ChannelAction<MediaChannel> createMediaChannel(String name, @Nullable Category parent) {
        return null;
    }

    @Override
    public ChannelAction<Category> createCategory(String name) {
        return null;
    }

    @Override
    public RoleAction createRole() {
        return null;
    }

    @Override
    public AuditableRestAction<RichCustomEmoji> createEmoji(String name, Icon icon, Role... roles) {
        return null;
    }

    @Override
    public AuditableRestAction<GuildSticker> createSticker(String name, String description, FileUpload file,
            Collection<String> tags) {
        return null;
    }

    @Override
    public AuditableRestAction<Void> deleteSticker(StickerSnowflake id) {
        return null;
    }

    @Override
    public ScheduledEventAction createScheduledEvent(String name, String location, OffsetDateTime startTime,
            OffsetDateTime endTime) {
        return null;
    }

    @Override
    public ScheduledEventAction createScheduledEvent(String name, GuildChannel channel, OffsetDateTime startTime) {
        return null;
    }

    @Override
    public ChannelOrderAction modifyCategoryPositions() {
        return null;
    }

    @Override
    public ChannelOrderAction modifyTextChannelPositions() {
        return null;
    }

    @Override
    public ChannelOrderAction modifyVoiceChannelPositions() {
        return null;
    }

    @Override
    public CategoryOrderAction modifyTextChannelPositions(Category category) {
        return null;
    }

    @Override
    public CategoryOrderAction modifyVoiceChannelPositions(Category category) {
        return null;
    }

    @Override
    public RoleOrderAction modifyRolePositions(boolean useAscendingOrder) {
        return null;
    }

    @Override
    public GuildWelcomeScreenManager modifyWelcomeScreen() {
        return null;
    }

    @Override
    public long getIdLong() {
        return 0;
    }
}
