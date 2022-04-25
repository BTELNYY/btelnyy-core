package me.btelnyy.core.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.command.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class CommandMySpawn implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Error: You must be a player in order to run this command.");
            return true;
        }
        Player player = (Player) sender;
        Location bedLocation = player.getBedSpawnLocation();
        Location spawn = Bukkit.getServer().getWorld(player.getLocation().getWorld().getName()).getSpawnLocation();
        int spawn_x = spawn.getBlockX();
        int spawn_y = spawn.getBlockY();
        int spawn_z = spawn.getBlockZ();
        if(bedLocation == null){
            sender.sendMessage(ChatColor.GRAY + "You do not have a bed.");
            sender.sendMessage(ChatColor.GRAY + "Your respawn location: " + spawn_x + " " + spawn_y + " " + spawn_z);
            return true;
        }
        int bed_x = bedLocation.getBlockX();
        int bed_y = bedLocation.getBlockY();
        int bed_z = bedLocation.getBlockZ();
        sender.sendMessage(ChatColor.GRAY + "Your respawn location: " + bed_x + " " + bed_y + " " + bed_z);
        return true;
    }
}
