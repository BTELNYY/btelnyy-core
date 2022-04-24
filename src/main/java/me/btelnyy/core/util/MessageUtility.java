package me.btelnyy.core.util;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageUtility {

    public static void SendToOps(String message, CommandSender sender) {
        String name;
        //if it is the server, change the name to avoid an error
        if (!(sender instanceof Player)) {
            name = "Server";
        } else {
            name = sender.getName();
        }
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p.isOp()) {
                if (name != "Server") {
                    if (p != (Player) sender) {
                        p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[" + name + ": " + message + "]");
                    }
                }
            }
        }
    }
}
