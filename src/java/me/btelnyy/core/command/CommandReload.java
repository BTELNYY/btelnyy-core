package java.me.btelnyy.core.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.me.btelnyy.core.service.ConfigLoaderService;
import java.me.btelnyy.core.service.TextFileMessageService;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class CommandReload implements CommandExecutor {

    private final ConfigLoaderService      configLoaderService;
    private final TextFileMessageService[] messageServices;

    public CommandReload(ConfigLoaderService configLoaderService, TextFileMessageService... messageServices) {
        this.configLoaderService = configLoaderService;
        this.messageServices     = messageServices;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GRAY + "Reloading plugin configuration and text files.");
        for (TextFileMessageService messageService : messageServices)
            messageService.loadMessages();
        configLoaderService.loadConfig();
        sender.sendMessage(ChatColor.GRAY + "Done.");
        return true;
    }
}
