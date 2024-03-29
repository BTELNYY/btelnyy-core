package me.btelnyy.core.command;

import me.btelnyy.core.service.TextFileMessageService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSuicide implements CommandExecutor {

    private final TextFileMessageService messageService;

    public CommandSuicide(TextFileMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
            return true;
        }

        ((Player) sender).setHealth(0);
        sender.sendMessage(ChatColor.RED + messageService.getRandomMessage());
        return true;
    }
}
