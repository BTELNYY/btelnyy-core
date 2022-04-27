package me.btelnyy.core.command;

import com.github.writingbettercodethanyou.gamerpluginframework.command.RegisterForCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

@RegisterForCommand("ping")
public class CommandPing implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
            return true;
        }

        sender.sendMessage(String.format("%sPing: %dms", ChatColor.GRAY, ((Player) sender).getPing()));
        return true;
    }
}
