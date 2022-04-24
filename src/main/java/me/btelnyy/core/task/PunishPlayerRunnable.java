package main.java.me.btelnyy.core.task;

import java.util.logging.Level;

import main.java.me.btelnyy.core.constant.VoteGlobals;
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
        VoteGlobals.voteExists = false;
        BanList banList = Bukkit.getBanList(Type.NAME);
        if (VoteGlobals.yesCount > VoteGlobals.noCount) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Vote passed, " + VoteGlobals.target.getName() + " has received a " + VoteGlobals.voteType);
            VoteGlobals.voteExists = false;
            VoteGlobals.playersWhoHaveVoted.clear();
            VoteGlobals.yesCount = 0;
            VoteGlobals.noCount = 0;
            if (VoteGlobals.voteType.equals("kick")) {
                kick(VoteGlobals.target, "You were kicked by vote");
                JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, "Player " + VoteGlobals.target.getName() + " has been kicked due to vote");
            }
            ;
            if (VoteGlobals.voteType.equals("ban")) {
                banList.addBan(VoteGlobals.target.getName(), ChatColor.RED + "You were banned by vote", null, null);
                kick(VoteGlobals.target, "You were banned by vote");
                JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, "Player " + VoteGlobals.target.getName() + " has been banned due to vote");
            }
            ;
        } else {
            Bukkit.broadcastMessage(ChatColor.RED + "Vote failed, not enough votes to " + VoteGlobals.voteType + " " + VoteGlobals.target.getName() + ".");
            JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, "Vote failed.");
            VoteGlobals.voteExists = false;
            VoteGlobals.playersWhoHaveVoted.clear();
            VoteGlobals.yesCount = 0;
            VoteGlobals.noCount = 0;
        }
        ;
    }

    static void kick(Player player, String message) {
        player.kickPlayer(ChatColor.RED + message);
    }
}
