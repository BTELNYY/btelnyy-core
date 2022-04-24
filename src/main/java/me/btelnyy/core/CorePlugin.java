package me.btelnyy.core;

import me.btelnyy.core.command.*;
import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.listener.EventListener;
import me.btelnyy.core.util.MOTDUtil;
import me.btelnyy.core.command.CommandVTP;
import me.btelnyy.core.command.CommandVote;
import me.btelnyy.core.constant.VoteGlobals;
import me.btelnyy.core.command.CommandVoteServerRestart;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;

public class CorePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // check if our config exists
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                saveDefaultConfig();
            } catch (Exception e) {
                getLogger().log(Level.SEVERE, "Config.yml could not be created. Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        // event handle
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        // load the rules
        CommandRules.loadMessages();
        // load MOTD on plugin enable
        MOTDUtil.loadMOTD();
        // load suicide messages on enable
        CommandSuicide.loadMessages();
        // load config
        loadConfig();
        this.getCommand("suicide").setExecutor(new CommandSuicide());
        this.getCommand("dc").setExecutor(new CommandDisconnect());
        this.getCommand("rules").setExecutor(new CommandRules());
        this.getCommand("vtp").setExecutor(new CommandVTP());
        this.getCommand("vote").setExecutor(new CommandVote());
        this.getCommand("ping").setExecutor(new CommandPing());
        this.getCommand("voterestart").setExecutor(new CommandVoteServerRestart());
        this.getCommand("pvp").setExecutor(new CommandPvp());
        this.getCommand("hardcore").setExecutor(new CommandHardcore());
        this.getCommand("revive").setExecutor(new CommandRevive());
        this.getCommand("reviveall").setExecutor(new CommandReviveAll());
        this.getCommand("whereamI").setExecutor(new CommandCoords());
        this.getCommand("breload").setExecutor(new CommandReload());
    }

    public static void loadConfig() {
        JavaPlugin plugin = JavaPlugin.getProvidingPlugin(CorePlugin.class);

        FileConfiguration config = plugin.getConfig();
        VoteGlobals.voteTimer = config.getInt("vote_timer");
        plugin.getLogger().log(Level.INFO, "vote_timer: " + VoteGlobals.voteTimer);
        Globals.pvpToggled = config.getBoolean("default_pvp_toggle");
        plugin.getLogger().log(Level.INFO, "default_pvp_toggle: " + Globals.pvpToggled);
        Globals.hardcoreResult = config.getString("on_hardcore_death");
        plugin.getLogger().log(Level.INFO, "on_hardcore_death: (pre-check) " + Globals.hardcoreResult);
        if (!Arrays.asList(Globals.hOptions).contains(Globals.hardcoreResult)) {
            plugin.getLogger().log(Level.WARNING, "Your configuration for hardcore result is incorrect, loaded default value.");
            Globals.hardcoreResult = Globals.hOptions[0];
        } else {
            Globals.hardcoreResult = config.getString("on_hardcore_death");
        }
        plugin.getLogger().log(Level.INFO, "on_hardcore_death: " + Globals.hardcoreResult);
        Globals.tpToDeathHardcore = config.getBoolean("tp_to_death_hardcore");
        plugin.getLogger().log(Level.INFO, "tp_to_death_hardcore: " + Globals.tpToDeathHardcore);
        Globals.showDeathTitle = config.getBoolean("show_death_title");
        plugin.getLogger().log(Level.INFO, "show_death_title: " + Globals.showDeathTitle);
    }
}
