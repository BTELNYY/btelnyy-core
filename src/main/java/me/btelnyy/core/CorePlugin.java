package me.btelnyy.core;

import com.github.writingbettercodethanyou.gamerpluginframework.ChadJavaPlugin;
import com.github.writingbettercodethanyou.gamerpluginframework.inject.ServiceRegistry;
import com.github.writingbettercodethanyou.gamerpluginframework.message.MessageService;
import me.btelnyy.core.listener.EventListener;
import me.btelnyy.core.service.*;

import java.io.File;
import java.util.logging.Level;

public class CorePlugin extends ChadJavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        // event handle
        EventListener listener = getServiceRegistry().getInstance(EventListener.class);
        getServer().getPluginManager().registerEvents(listener, this);

        registerCommands();

        getLogger().log(Level.INFO, "Check out the project on GitHub!: https://github.com/BTELNYY/btelnyy-core");
    }

    @Override
    protected void onServiceSetup(ServiceRegistry.Builder services) {
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

        // load config
        ConfigLoaderService configLoaderService = new ConfigLoaderService(getConfig(), getLogger());
        configLoaderService.loadConfig();
        services.addSingleton(ConfigLoaderService.class, configLoaderService);

        // load the messages
        MessageService messageService = createYamlMessageService("messages.yml");
        services.addSingleton(MessageService.class, messageService);

        // load the rules
        RulesMessageService rulesMessageService = new RulesMessageService(new File(getDataFolder(), "rules.txt"), getLogger());
        rulesMessageService.loadMessages();
        services.addSingleton(RulesMessageService.class, rulesMessageService);

        // load MOTD
        MotdMessageService motdMessageService = new MotdMessageService(new File(getDataFolder(), "random_motd.txt"), getLogger());
        motdMessageService.loadMessages();
        services.addSingleton(MotdMessageService.class, motdMessageService);

        // load suicide messages
        SuicideMessageService suicideMessageService = new SuicideMessageService(new File(getDataFolder(), "death_msg.txt"), getLogger());
        suicideMessageService.loadMessages();
        services.addSingleton(SuicideMessageService.class, suicideMessageService);

        services.addSingleton(TextFileMessageService[].class, new TextFileMessageService[] { rulesMessageService, motdMessageService, suicideMessageService });
    }
}
