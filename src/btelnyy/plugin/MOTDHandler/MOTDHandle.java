package btelnyy.plugin.MOTDHandler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import org.bukkit.event.server.ServerListPingEvent;

import btelnyy.plugin.Globals;
import btelnyy.plugin.Main;

public class MOTDHandle {
	static String motds = "";
	public static void LoadMOTD() {
		File f = new File("./plugins/" + Globals.ConfigPath + "/random_motd.txt");
    	Path p = Path.of("./plugins/" + Globals.ConfigPath + "/random_motd.txt");
    	if(!f.exists()){
    	    try {
				f.createNewFile();
				Main.log(java.util.logging.Level.INFO, "No MOTD File Exists");
			} catch (IOException e) {
				e.printStackTrace();
				Main.log(java.util.logging.Level.SEVERE, "Error parsing MOTD");
			}
    	}else {
    		try {
				motds = Files.readString(p);
			} catch (IOException e) {
				e.printStackTrace();
				Main.log(java.util.logging.Level.SEVERE, "Error reading MOTD file");
			}
    	}
	}
	static String getRandomMOTD() {
		String[] array = motds.split("\\r?\\n");
		Random rand = new Random();
		int random = rand.nextInt(array.length) + 0;
		return array[random];
	}
	public static void ChangeMOTD(ServerListPingEvent event) {
		event.setMotd(getRandomMOTD());
	}
}
