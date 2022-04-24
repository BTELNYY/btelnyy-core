package me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.util.MessageUtility;

public class CommandHardcore implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //if you disable hardcore via command, but server hardcore is still on
        if (!Globals.hardcoreToggled) {
            sender.sendMessage(ChatColor.GRAY + "Hardcore is now enabled.");
            MessageUtility.messageOperators("Enabled Hardcore", sender);
            Globals.hardcoreToggled = true;
            return true;
        }
        if (Globals.hardcoreToggled) {
            sender.sendMessage(ChatColor.GRAY + "Hardcore is now disabled.");
            MessageUtility.messageOperators("Disabled Hardcore", sender);
            Globals.hardcoreToggled = false;
            return true;
        }
        return true;
    }
}
