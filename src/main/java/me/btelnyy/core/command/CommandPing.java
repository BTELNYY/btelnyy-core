package main.java.me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class CommandPing implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int ping = player.getPing();
            sender.sendMessage(ChatColor.GRAY + "Ping: " + ping + "ms");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
            return true;
        }
    }
}
