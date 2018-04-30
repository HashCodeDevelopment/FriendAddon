package net.hashcodedevelopment.friendaddon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Utils {

	public static HashSet<String[]> getFriends(UUID uuid){
		HashSet<String[]> hashSet = new HashSet<>();

		File file = new File("plugins//LobbySystem//Playerdata//"+uuid+".yml");
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		
		List<String> list = new ArrayList<>();
		
		if(!configuration.contains("Friends")){
			configuration.set("Friends", list);
			
			try {
				configuration.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(String key : configuration.getStringList("Friends")){
			hashSet.add(key.split(":"));
		}
		
		if(hashSet.size() == 0){
			String[] data = {
				"§4✗", "§4✗"	
			};
			hashSet.add(data);
		}
		
		return hashSet;
	}
	
	public static boolean isFriend(UUID uuid1, UUID uuid2){
		if(new File("plugins//LobbySystem//Playerdata//"+uuid1+".yml").exists() && new File("plugins//LobbySystem//Playerdata//"+uuid2+".yml").exists()){
			File file1 = new File("plugins//LobbySystem//Playerdata//"+uuid1+".yml");
			FileConfiguration configuration1 = YamlConfiguration.loadConfiguration(file1);
			
			File file2 = new File("plugins//LobbySystem//Playerdata//"+uuid2+".yml");
			FileConfiguration configuration2 = YamlConfiguration.loadConfiguration(file2);
			
			List<String> f1 = configuration1.getStringList("Friends");
			List<String> f2 = configuration2.getStringList("Friends");
			
			int i = 0;
			
			for(String key : f1){
				String[] data = key.split(";");
				if(data[0].equals(uuid2.toString())){
					i++;
				}
			}
			for(String key : f2){
				String[] data = key.split(";");
				if(data[0].equals(uuid1.toString())){
					i++;
				}
			}
			
			if(i == 2){
				return true;
			}
		}
		return false;	
	}
	
}
