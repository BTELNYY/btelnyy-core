package btelnyy.plugin;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
public class KickPlayer {
	Timer timer;
    public void run(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000); // schedule the task
   	}
    public void start(int seconds) {
    		run(seconds);
    }
    class RemindTask extends TimerTask {
        public void run() {
        	VoteGlobals.VoteExists = false;
        	BanList BanList = Bukkit.getBanList(Type.NAME);
        	if(VoteGlobals.VoteNo > VoteGlobals.VoteYes) {
        		Bukkit.broadcastMessage(ChatColor.GREEN + "Vote passed," + VoteGlobals.target.getName() + "has received a " + VoteGlobals.VoteType);
        		if(VoteGlobals.VoteType == "kick."){
        			VoteGlobals.target.kickPlayer(ChatColor.RED + "You were kicked by vote");
        		};
        		if(VoteGlobals.VoteType == "ban"){
        			BanList.addBan(VoteGlobals.target.getName(), ChatColor.RED + "You were banned by vote", null, null);
        		};
        	}else{
        		Bukkit.broadcastMessage(ChatColor.RED + "Vote failed, not enough votes to kick.");
        	};
            timer.cancel(); //Terminate the timer thread
        };
    }
}
