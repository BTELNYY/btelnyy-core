package btelnyy.plugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
public class CommandGive implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		// TODO Auto-generated method stub
		if(sender instanceof Player ) {
			Material itemType = Material.matchMaterial(args[1]);
			if (itemType == null) { //check whether the material exists
			    sender.sendMessage(ChatColor.RED + "Error: Unknown item.");
			    return true;
			}

			ItemStack itemStack = new ItemStack(itemType);
			((Player) sender).getInventory().addItem(itemStack);
			sender.sendMessage("Here you go!");
			return true;
		}
		sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
		return true;
	}
}