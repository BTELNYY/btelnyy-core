package me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.btelnyy.core.CorePlugin;
import me.btelnyy.core.util.MOTDUtil;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class CommandReload implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GRAY + "Reloading plugin configuration and text files.");
        MOTDUtil.loadMOTD();
        CommandRules.loadMessages();
        CommandSuicide.loadMessages();
        CorePlugin.loadConfig();
        sender.sendMessage(ChatColor.GRAY + "Done.");
        return true;
    }
}
