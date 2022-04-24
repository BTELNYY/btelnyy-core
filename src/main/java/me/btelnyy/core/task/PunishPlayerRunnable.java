package me.btelnyy.core.task;

import java.util.logging.Level;

import me.btelnyy.core.constant.VoteGlobals;
import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PunishPlayerRunnable extends BukkitRunnable {

    public void start(int seconds) throws InterruptedException {
        runTaskLater(JavaPlugin.getProvidingPlugin(getClass()), 600);
    }

    @Override
    public void run() {
        VoteGlobals.VoteExists = false;
        BanList BanList = Bukkit.getBanList(Type.NAME);
        if (VoteGlobals.VoteYes > VoteGlobals.VoteNo) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Vote passed, " + VoteGlobals.target.getName() + " has received a " + VoteGlobals.VoteType);
            VoteGlobals.VoteExists = false;
            VoteGlobals.VotedPlayers.clear();
            VoteGlobals.VoteYes = 0;
            VoteGlobals.VoteNo = 0;
            if (VoteGlobals.VoteType == "kick") {
                kick(VoteGlobals.target, ChatColor.RED, "You were kicked by vote");
                JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, "Player " + VoteGlobals.target.getName() + " has been kicked due to vote");
            }
            ;
            if (VoteGlobals.VoteType == "ban") {
                BanList.addBan(VoteGlobals.target.getName(), ChatColor.RED + "You were banned by vote", null, null);
                kick(VoteGlobals.target, ChatColor.RED, "You were banned by vote");
                JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, "Player " + VoteGlobals.target.getName() + " has been banned due to vote");
            }
            ;
        } else {
            Bukkit.broadcastMessage(ChatColor.RED + "Vote failed, not enough votes to " + VoteGlobals.VoteType + " " + VoteGlobals.target.getName() + ".");
            JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, "Vote failed.");
            VoteGlobals.VoteExists = false;
            VoteGlobals.VotedPlayers.clear();
            VoteGlobals.VoteYes = 0;
            VoteGlobals.VoteNo = 0;
        }
        ;
    }

    static void kick(Player p, ChatColor Color, String Message) {
        p.kickPlayer(Color + Message);
    }
}
