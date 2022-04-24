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
        if (args.length > 0) {
            if (!sender.hasPermission("btelnyy.command.whereami.others")) {
                Player player = (Player) sender;
                Location pos = player.getEyeLocation();
                int x = pos.getBlockX();
                int y = pos.getBlockY();
                int z = pos.getBlockZ();
                sender.sendMessage(ChatColor.GRAY + "Your position: (XYZ) " + x + " " + y + " " + z);
                return true;
            }
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatColor.RED + "Error: Player not found.");
                return true;
            } else {
                Player player_target = Bukkit.getPlayer(args[0]);
                Location pos = player_target.getEyeLocation();
                int x = pos.getBlockX();
                int y = pos.getBlockY();
                int z = pos.getBlockZ();
                sender.sendMessage(ChatColor.GRAY + player_target.getName() + "'s position: (XYZ) " + x + " " + y + " " + z);
            }
        } else {
            Player player = (Player) sender;
            Location pos = player.getEyeLocation();
            int x = pos.getBlockX();
            int y = pos.getBlockY();
            int z = pos.getBlockZ();
            sender.sendMessage(ChatColor.GRAY + "Your position: (XYZ) " + x + " " + y + " " + z);
            return true;
        }
        return true;
    }
}