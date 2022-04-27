package me.btelnyy.core.command;

import com.github.writingbettercodethanyou.gamerpluginframework.command.RegisterForCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.util.RespawnUtil;
import me.btelnyy.core.util.MessageUtility;

@RegisterForCommand("revive")
public class CommandRevive implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Error: Invalid syntax. Usage: /revive <player>");
            return true;
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(ChatColor.RED + "Error: Player not found.");
            return true;
        }
        Player revivalTarget = Bukkit.getPlayer(args[0]);
        if (!Globals.deadPlayers.contains(revivalTarget)) {
            sender.sendMessage(ChatColor.RED + "Error: Player is not dead.");
            return true;
        }
        RespawnUtil.revivePlayer(revivalTarget);
        sender.sendMessage(ChatColor.GRAY + "Player " + revivalTarget.getName() + " has been revived.");
        MessageUtility.messageOperators("Has revived " + revivalTarget.getName(), sender);
        return true;
    }
}
