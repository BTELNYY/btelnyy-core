package me.btelnyy.core.service;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.constant.VoteGlobals;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ConfigLoaderService {

    private final FileConfiguration config;
    private final Logger            logger;

    public ConfigLoaderService(FileConfiguration config, Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    public void loadConfig() {
        VoteGlobals.voteTimer = getAndLog("vote_timer", config::getInt);
        Globals.pvpToggled = getAndLog("default_pvp_toggle", config::getBoolean);
        Globals.hardcoreResult = config.getString("on_hardcore_death");
        Globals.tpToDeathHardcore = getAndLog("tp_to_death_hardcore", config::getBoolean);
        Globals.showDeathTitle = getAndLog("show_death_title", config::getBoolean);
        Globals.elytraAllowed = getAndLog("elytra_allowed", config::getBoolean);

        // TODO 2022/04/26: Add support for choosing from a list
        logger.log(Level.INFO, "on_hardcore_death: (pre-check) " + Globals.hardcoreResult);
        if (!Arrays.asList(Globals.hOptions).contains(Globals.hardcoreResult)) {
            logger.log(Level.WARNING, "Your configuration for hardcore result is incorrect, loaded default value.");
            logger.log(Level.WARNING, "Hardcore result must be ONE of: " + String.join(", ", Globals.hOptions));
            Globals.hardcoreResult = Globals.hOptions[0];
        } else {
            Globals.hardcoreResult = config.getString("on_hardcore_death");
        }
        logger.log(Level.INFO, "on_hardcore_death: " + Globals.hardcoreResult);
    }

    private <T> T getAndLog(String path, Function<String, T> function) {
        T value = function.apply(path);
        logger.log(Level.INFO, path + ": " + value);
        return value;
    }
}
