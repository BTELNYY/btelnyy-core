package btelnyy.plugin;
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
			((Player) sender).setHealth(0);
			String[] array = { "You committed a Sayori.", "You looked at SCP 096's face.", "You forgot how to breath.", "You pissed me off.", "You were 'cured' of the plague.", "Segmentation Fault (core dumped)", "You blinked in front of SCP 173.", "Tesla gates exist dumbass.", "ouch.", "Crunchy neck.", "You typed 'kill' into console.", "Melted by a highly corrosive substance.", "Broke your back while trying to do a backflip."  };			
			Random rand = new Random();
			int random = rand.nextInt(array.length) + 0;
			sender.sendMessage(ChatColor.RED + array[random]);
			return true;
		}
		sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
		return true;
	}
	
}
