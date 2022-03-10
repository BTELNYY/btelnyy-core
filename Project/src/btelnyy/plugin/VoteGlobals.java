package btelnyy.plugin;
import org.bukkit.entity.Player;
public class VoteGlobals {
	public static Player target = null;
	public static int VoteYes = 0;
	public static int VoteNo = 0;
	public static Player[] VotedPlayers = {};
	public static boolean VoteExists = false;
	public static String VoteType = "";
	public static int VoteTimer = 30;
}
