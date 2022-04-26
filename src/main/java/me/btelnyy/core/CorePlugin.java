package me.btelnyy.core;

import com.github.writingbettercodethanyou.chadpluginframework.ChadJavaPlugin;
import com.github.writingbettercodethanyou.chadpluginframework.message.MessageService;
import me.btelnyy.core.command.*;
import me.btelnyy.core.listener.EventListener;
import me.btelnyy.core.service.ConfigLoaderService;
import me.btelnyy.core.service.TextFileMessageService;

import java.io.File;
import java.util.logging.Level;

public class CorePlugin extends ChadJavaPlugin {

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

        // load the messages
        MessageService messageService = createYamlMessageService("messages.yml");

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
        getServer().getPluginManager().registerEvents(new EventListener(messageService, motdMessageService), this);

        registerCommandExecutor("breload",     new CommandReload(configLoaderService, rulesMessageService, motdMessageService, suicideMessageService));
        registerCommandExecutor("dc",          new CommandDisconnect());
        registerCommandExecutor("hardcore",    new CommandHardcore());
        registerCommandExecutor("myspawn",     new CommandMySpawn());
        registerCommandExecutor("ping",        new CommandPing());
        registerCommandExecutor("pvp",         new CommandPvp());
        registerCommandExecutor("revive",      new CommandRevive());
        registerCommandExecutor("reviveall",   new CommandReviveAll());
        registerCommandExecutor("rules",       new CommandRules(rulesMessageService));
        registerCommandExecutor("suicide",     new CommandSuicide(suicideMessageService));
        registerCommandExecutor("vote",        new CommandVote());
        registerCommandExecutor("voterestart", new CommandVoteServerRestart());
        registerCommandExecutor("vtp",         new CommandVTP());
        registerCommandExecutor("whereami",    new CommandCoords(messageService));

        getLogger().log(Level.INFO, "Check out the project on GitHub!: https://github.com/BTELNYY/btelnyy-core");
    }
}
