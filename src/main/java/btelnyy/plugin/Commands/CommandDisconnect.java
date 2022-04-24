package btelnyy.plugin.Commands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class CommandDisconnect implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		// TODO Auto-generated method stub
		if(sender instanceof Player ) {
			((Player) sender).kickPlayer("Disconnected");
		}
		sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
		return true;
	}
	
}