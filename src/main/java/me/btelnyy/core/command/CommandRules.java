package me.btelnyy.core.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.Main;

public class CommandRules implements CommandExecutor{
	private static String rules = "";
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

    	String[] rulelines = rules.split("\\r?\\n");
    	for(String rule : rulelines){
    		sender.sendMessage(rule);
    	}
    	return true;
	}
	public static void LoadMessages() {
    	File f = new File(Globals.ConfigPath + "/rules.txt");
    	Path p = Path.of(Globals.ConfigPath + "/rules.txt");
    	if(!f.exists()){
    	    try {
				f.createNewFile();
				Main.log(Level.WARNING, "No rules.txt exists.");
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
    	}else {
    		try {
				rules = Files.readString(p);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
    	}
	}
}
