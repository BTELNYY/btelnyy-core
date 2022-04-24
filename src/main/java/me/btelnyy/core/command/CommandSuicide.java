package me.btelnyy.core.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.Main;

public class CommandSuicide implements CommandExecutor {
	public static String[] array = {};
	public static void LoadMessages() {
		File f = new File(Globals.ConfigPath + "/death_msg.txt");
    	Path p = Path.of(Globals.ConfigPath + "/death_msg.txt");
    	String deaths = "";
    	if(!f.exists()){
    	    try {
				f.createNewFile();
				Main.log(java.util.logging.Level.WARNING, Globals.ConfigPath +"/death_msg.txt does not exist.");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else {
    		try {
				deaths = Files.readString(p);
				array = deaths.split("\\r?\\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		// TODO Auto-generated method stub
		if(sender instanceof Player ) {
			((Player) sender).setHealth(0);
			Random rand = new Random();
			int random = rand.nextInt(array.length) + 0;
			sender.sendMessage(ChatColor.RED + array[random]);
			return true;
		}
		sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
		return true;
	}
	
}
