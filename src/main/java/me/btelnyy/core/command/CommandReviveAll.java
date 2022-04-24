package me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import me.btelnyy.core.util.RespawnUtil;
import me.btelnyy.core.util.MessageUtility;

public class CommandReviveAll implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
        RespawnUtil.ReviveAll((Player) sender);
        MessageUtility.SendToOps("Revived all players", sender);
        return true;
    }
}