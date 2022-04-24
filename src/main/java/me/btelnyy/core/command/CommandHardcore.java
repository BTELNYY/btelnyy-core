package main.java.me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import main.java.me.btelnyy.core.constant.Globals;
import main.java.me.btelnyy.core.util.MessageUtility;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

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
