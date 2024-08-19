package chikachi.discord.core.config.validator;

import java.util.ArrayList;

import org.slf4j.Logger;

import chikachi.discord.core.DiscordIntegrationLogger;
import chikachi.discord.core.config.validator.rules.ChannelCommandPrefixEmptyRule;
import chikachi.discord.core.config.validator.rules.ChannelDescriptionsEnabledButEmptyRule;
import chikachi.discord.core.config.validator.rules.ChannelRelayChatTrueButMessageEmptyRule;
import chikachi.discord.core.config.validator.rules.DiscordTokenMustBeSetRule;
import chikachi.discord.core.config.validator.rules.DuplicatedCommandOrAliasRule;
import chikachi.discord.core.config.validator.rules.IMCEnabledAndBlacklistEmptyRule;
import chikachi.discord.core.config.validator.rules.MinecraftChatPrefixTooLongRule;

public abstract class ConfigurationValidator {

    private static final Logger log = DiscordIntegrationLogger.getLogger(ConfigurationValidator.class);

    private static ArrayList<IConfigurationValidationRule> rules = new ArrayList<>();

    static {
        addRule(new DiscordTokenMustBeSetRule());
        addRule(new DuplicatedCommandOrAliasRule());
        addRule(new ChannelCommandPrefixEmptyRule());
        addRule(new ChannelDescriptionsEnabledButEmptyRule());
        addRule(new ChannelRelayChatTrueButMessageEmptyRule());
        addRule(new MinecraftChatPrefixTooLongRule());
        addRule(new IMCEnabledAndBlacklistEmptyRule());
    }

    public static void addRule(IConfigurationValidationRule rule) {
        rules.add(rule);
    }

    public static ValidationResult[] validateAll() {
        return rules.stream().map(IConfigurationValidationRule::validate).toArray(ValidationResult[]::new);
    }

    public static void validateAndPrintAll() {
        log.trace("Validating the configuration..");
        ValidationResult[] results = validateAll();
        int invalid = 0;

        for (ValidationResult result : results) {
            if (!result.successful) {
                log.debug("[HINT] {}", result.hint);
                invalid++;
            }
        }

        log.trace(
                "Configuration validated. {} of {} rules were successful.",
                getTotalTestCount() - invalid,
                getTotalTestCount());
    }

    public static int getTotalTestCount() {
        return rules.size();
    }
}
