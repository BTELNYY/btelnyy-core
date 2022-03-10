package btelnyy.plugin;
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
public class CommandSuicide implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		// TODO Auto-generated method stub
		if(sender instanceof Player ) {
	    	File f = new File("./plugins/btelnyy/death_msg.txt");
	    	Path p = Path.of("./plugins/btelnyy/death_msg.txt");
	    	String deaths = "";
	    	if(!f.exists()){
	    	    try {
					f.createNewFile();
					sender.sendMessage(ChatColor.RED + "Error: No death_msg.txt exists.");
				} catch (IOException e) {
					e.printStackTrace();
					return true;
				}
	    	}else {
	    		try {
					deaths = Files.readString(p);
				} catch (IOException e) {
					e.printStackTrace();
					return true;
				}
	    	}
			((Player) sender).setHealth(0);
			String[] array = deaths.split("\\r?\\n");
			Random rand = new Random();
			int random = rand.nextInt(array.length) + 0;
			sender.sendMessage(ChatColor.RED + array[random]);
			return true;
		}
		sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
		return true;
	}
	
}
