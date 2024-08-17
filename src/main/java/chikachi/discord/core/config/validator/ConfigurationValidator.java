package chikachi.discord.core.config.validator;

import java.util.ArrayList;

import chikachi.discord.core.DiscordIntegrationLogger;
import chikachi.discord.core.config.validator.rules.ChannelCommandPrefixEmptyRule;
import chikachi.discord.core.config.validator.rules.ChannelDescriptionsEnabledButEmptyRule;
import chikachi.discord.core.config.validator.rules.ChannelRelayChatTrueButMessageEmptyRule;
import chikachi.discord.core.config.validator.rules.DiscordTokenMustBeSetRule;
import chikachi.discord.core.config.validator.rules.DuplicatedCommandOrAliasRule;
import chikachi.discord.core.config.validator.rules.IMCEnabledAndBlacklistEmptyRule;
import chikachi.discord.core.config.validator.rules.MinecraftChatPrefixTooLongRule;

public abstract class ConfigurationValidator {

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
        DiscordIntegrationLogger.Log("Validating the configuration..");
        ValidationResult[] results = validateAll();
        int invalid = 0;

        for (ValidationResult result : results) {
            if (!result.successful) {
                DiscordIntegrationLogger.Log(String.format("[HINT] %s", result.hint));
                invalid++;
            }
        }

        DiscordIntegrationLogger.Log(
                String.format(
                        "Configuration validated. %d of %d rules were successful.",
                        getTotalTestCount() - invalid,
                        getTotalTestCount()));
    }

    public static int getTotalTestCount() {
        return rules.size();
    }
}
