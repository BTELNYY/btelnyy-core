package me.btelnyy.core.command;

import com.github.writingbettercodethanyou.gamerpluginframework.command.RegisterForCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.command.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;

@RegisterForCommand("myspawn")
public class CommandMySpawn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player player)){
            sender.sendMessage(ChatColor.RED + "Error: You must be a player in order to run this command.");
            return true;
        }

        Location spawnLocation = player.getBedSpawnLocation();
        if(spawnLocation == null) {
            spawnLocation = player.getWorld().getSpawnLocation();
            sender.sendMessage(ChatColor.GRAY + "You do not have a bed.");
        }

        sender.sendMessage(String.format("%sYour respawn location: %d %d %d", ChatColor.GRAY, spawnLocation.getBlockX(), spawnLocation.getBlockY(), spawnLocation.getBlockZ()));
        return true;
    }
}
