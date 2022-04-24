package me.btelnyy.core.task;

import me.btelnyy.core.constant.VoteGlobals;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartServerRunnable extends BukkitRunnable {

    public void start(int seconds) throws InterruptedException {
        runTaskLater(JavaPlugin.getProvidingPlugin(getClass()), 600);
    }

    @Override
    public void run() {
        VoteGlobals.VoteExists = false;
        VoteGlobals.VoteType = "";
        VoteGlobals.VotedPlayers.clear();
        if (VoteGlobals.VoteYes > VoteGlobals.VoteNo) {
            VoteGlobals.VoteYes = 0;
            VoteGlobals.VoteNo = 0;
            for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                target.kickPlayer(ChatColor.RED + "Server restarting.");
            }
            Bukkit.shutdown();
            return;
        } else {
            VoteGlobals.VoteYes = 0;
            VoteGlobals.VoteNo = 0;
            Bukkit.broadcastMessage(ChatColor.RED + "Vote failed, not enough votes to restart server.");
            return;
        }
    }
}