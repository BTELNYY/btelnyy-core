package btelnyy.plugin;
import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
public class KickPlayer extends BukkitRunnable {
    private JavaPlugin plugin = main.getInstance();
    public void start(int seconds) throws InterruptedException {
    		runTaskLater(plugin, 600);
    }
	@Override
	public void run() {
    	VoteGlobals.VoteExists = false;
    	BanList BanList = Bukkit.getBanList(Type.NAME);
    	if(VoteGlobals.VoteYes > VoteGlobals.VoteNo) {
    		Bukkit.broadcastMessage(ChatColor.GREEN + "Vote passed, " + VoteGlobals.target.getName() + " has received a " + VoteGlobals.VoteType);
    		if(VoteGlobals.VoteType == "kick"){
    			kick(VoteGlobals.target, ChatColor.RED, "You were kicked by vote");
    		};
    		if(VoteGlobals.VoteType == "ban"){
    			BanList.addBan(VoteGlobals.target.getName(), ChatColor.RED + "You were banned by vote", null, null);
    			kick(VoteGlobals.target, ChatColor.RED, "You were banned by vote");
    		};
    	}else{
    		Bukkit.broadcastMessage(ChatColor.RED + "Vote failed, not enough votes to kick.");
    	};
	}
    static void kick(Player p, ChatColor Color, String Message) {
    	p.kickPlayer(Color + Message);
    }
}
