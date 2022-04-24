package me.btelnyy.core;

import me.btelnyy.core.command.*;
import me.btelnyy.core.listener.EventListener;
import me.btelnyy.core.service.ConfigLoaderService;
import me.btelnyy.core.service.TextFileMessageService;
import me.btelnyy.core.command.CommandVTP;
import me.btelnyy.core.command.CommandVote;
import me.btelnyy.core.command.CommandVoteServerRestart;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
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

        // load the rules
        TextFileMessageService rulesMessageService = new TextFileMessageService(new File(getDataFolder(), "rules.txt"), getLogger());
        rulesMessageService.loadMessages();

        // load MOTD on plugin enable
        TextFileMessageService motdMessageService = new TextFileMessageService(new File(getDataFolder(), "random_motd.txt"), getLogger());
        motdMessageService.loadMessages();

        // load suicide messages on enable
        TextFileMessageService suicideMessageService = new TextFileMessageService(new File(getDataFolder(), "death_msg.txt"), getLogger());
        suicideMessageService.loadMessages();

        // load config
        ConfigLoaderService configLoaderService = new ConfigLoaderService(getConfig(), getLogger());
        configLoaderService.loadConfig();

        // event handle
        getServer().getPluginManager().registerEvents(new EventListener(motdMessageService), this);

        this.getCommand("suicide").setExecutor(new CommandSuicide(suicideMessageService));
        this.getCommand("dc").setExecutor(new CommandDisconnect());
        this.getCommand("rules").setExecutor(new CommandRules(rulesMessageService));
        this.getCommand("vtp").setExecutor(new CommandVTP());
        this.getCommand("vote").setExecutor(new CommandVote());
        this.getCommand("ping").setExecutor(new CommandPing());
        this.getCommand("voterestart").setExecutor(new CommandVoteServerRestart());
        this.getCommand("pvp").setExecutor(new CommandPvp());
        this.getCommand("hardcore").setExecutor(new CommandHardcore());
        this.getCommand("revive").setExecutor(new CommandRevive());
        this.getCommand("reviveall").setExecutor(new CommandReviveAll());
        this.getCommand("whereamI").setExecutor(new CommandCoords());
        this.getCommand("breload").setExecutor(new CommandReload(configLoaderService, rulesMessageService, motdMessageService, suicideMessageService));
    }
}
