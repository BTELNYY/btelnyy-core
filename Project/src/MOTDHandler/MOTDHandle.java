package MOTDHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTDHandle {
	static String getRandomMOTD() {
		File f = new File("./plugins/btelnyy/random_motd.txt");
    	Path p = Path.of("./plugins/btelnyy/random_motd.txt");
    	String motds = "";
    	if(!f.exists()){
    	    try {
				f.createNewFile();
				return "No MOTD File Exists";
			} catch (IOException e) {
				e.printStackTrace();
				return "An error occured while parsing MOTD";
			}
    	}else {
    		try {
				motds = Files.readString(p);
			} catch (IOException e) {
				e.printStackTrace();
				return "An error occured while reading file";
			}
			String[] array = motds.split("\\r?\\n");
			Random rand = new Random();
			int random = rand.nextInt(array.length) + 0;
			return array[random];
    	}
	}
	public static void ChangeMOTD(ServerListPingEvent event) {
		event.setMotd(getRandomMOTD());
	}
}
