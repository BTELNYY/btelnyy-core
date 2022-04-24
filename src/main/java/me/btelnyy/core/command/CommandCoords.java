package me.btelnyy.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCoords implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
            return true;
        }

        // Select the player
        Player targetPlayer;
        if (args.length == 0 || !sender.hasPermission("btelnyy.command.whereami.others"))
            targetPlayer = (Player) sender;
        else {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if(targetPlayer == null) {
                sender.sendMessage(ChatColor.RED + "Error: Player not found.");
                return true;
            }
        }

        Location pos = targetPlayer.getEyeLocation();
        sender.sendMessage(
                String.format(
                        "%s%s position: (XYZ) %d %d %d",
                        ChatColor.GRAY,
                        targetPlayer == sender ? "Your" : targetPlayer.getName() + "'s",
                        pos.getBlockX(),
                        pos.getBlockY(),
                        pos.getBlockZ()));

        return true;
    }
}
