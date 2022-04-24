package me.btelnyy.core.vote;

import me.btelnyy.core.constant.VoteGlobals;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.btelnyy.core.Main;

public class RestartServer extends BukkitRunnable {
    private JavaPlugin plugin = Main.getInstance();
    public void start(int seconds) throws InterruptedException {
    		runTaskLater(plugin, 600);
    }
	@Override
	public void run() {
		VoteGlobals.VoteExists = false;
		VoteGlobals.VoteType = "";
		VoteGlobals.VotedPlayers.clear();
		if(VoteGlobals.VoteYes > VoteGlobals.VoteNo) {
			VoteGlobals.VoteYes = 0;
			VoteGlobals.VoteNo = 0;
			for (Player target : Bukkit.getServer().getOnlinePlayers()) {
			    target.kickPlayer(ChatColor.RED + "Server restarting.");
			}
			restart();
			return;
		}else {
			VoteGlobals.VoteYes = 0;
			VoteGlobals.VoteNo = 0;
			Bukkit.broadcastMessage(ChatColor.RED + "Vote failed, not enough votes to restart server.");
			return;
		}
	}
    static void restart() {
    	Bukkit.shutdown();
    	return;
    }
}