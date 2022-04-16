package btelnyy.plugin.Commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import btelnyy.plugin.Globals;

import org.bukkit.ChatColor;

public class CommandRules implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
    	File f = new File("./plugins/" +  Globals.ConfigPath + "/rules.txt");
    	Path p = Path.of("./plugins/" +  Globals.ConfigPath + "/rules.txt");
    	String rules = "";
    	if(!f.exists()){
    	    try {
				f.createNewFile();
				sender.sendMessage(ChatColor.RED + "Error: No rules.txt exists.");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return true;
			}
    	}else {
    		try {
				rules = Files.readString(p);
			} catch (IOException e) {
				e.printStackTrace();
				return true;
			}
    	}
    	String[] rulelines = rules.split("\\r?\\n");
    	for(String rule : rulelines){
    		sender.sendMessage(rule);
    	}
    	return true;
	}
}
