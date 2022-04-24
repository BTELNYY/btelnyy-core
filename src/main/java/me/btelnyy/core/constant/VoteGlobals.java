package me.btelnyy.core.constant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class VoteGlobals {

    public static Player target = null;
    public static int yesCount = 0;
    public static int noCount = 0;
    public static List<Player> playersWhoHaveVoted = new ArrayList<>();
    public static boolean voteExists = false;
    public static String voteType = "";
    public static int voteTimer = 30;
    public static Player starter = null;
}
