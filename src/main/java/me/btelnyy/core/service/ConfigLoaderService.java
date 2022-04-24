package me.btelnyy.core.service;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.constant.VoteGlobals;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
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
        VoteGlobals.voteTimer = config.getInt("vote_timer");
        logger.log(Level.INFO, "vote_timer: " + VoteGlobals.voteTimer);
        Globals.pvpToggled = config.getBoolean("default_pvp_toggle");
        logger.log(Level.INFO, "default_pvp_toggle: " + Globals.pvpToggled);
        Globals.hardcoreResult = config.getString("on_hardcore_death");
        logger.log(Level.INFO, "on_hardcore_death: (pre-check) " + Globals.hardcoreResult);
        if (!Arrays.asList(Globals.hOptions).contains(Globals.hardcoreResult)) {
            logger.log(Level.WARNING, "Your configuration for hardcore result is incorrect, loaded default value.");
            Globals.hardcoreResult = Globals.hOptions[0];
        } else {
            Globals.hardcoreResult = config.getString("on_hardcore_death");
        }
        logger.log(Level.INFO, "on_hardcore_death: " + Globals.hardcoreResult);
        Globals.tpToDeathHardcore = config.getBoolean("tp_to_death_hardcore");
        logger.log(Level.INFO, "tp_to_death_hardcore: " + Globals.tpToDeathHardcore);
        Globals.showDeathTitle = config.getBoolean("show_death_title");
        logger.log(Level.INFO, "show_death_title: " + Globals.showDeathTitle);
    }
}
