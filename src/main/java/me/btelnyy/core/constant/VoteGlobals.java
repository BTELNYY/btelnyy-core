package me.btelnyy.core.constant;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

public class VoteGlobals {
	public static Player target = null;
	public static int VoteYes = 0;
	public static int VoteNo = 0;
	public static List<Player> VotedPlayers = new ArrayList<>();
	public static boolean VoteExists = false;
	public static String VoteType = "";
	public static int VoteTimer = 30;
	public static Player starter = null;
}
