package net.hashcodedevelopment.friendaddon;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.hashcodedevelopment.friendaddon.LanguageManager.Language;

public class FriendAddon extends JavaPlugin {
	
	public static File file = new File("plugins//LobbySystem//Einstellungen//config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static Language language;
	private Plugin lobbySystem;
	
	@Override
	public void onEnable() {
		getCommand("friend").setExecutor(new FriendCmd());
		
		load();
		
		switch (language){
		case DE:
			if(lobbySystem == null){
				System.err.println("Das FriendAddon funktioniert nur, wenn das Lobbysystem installiert ist!");
				Bukkit.getPluginManager().disablePlugin(this);
			} else {
				System.out.println("FriendAddon aktiviert!");
			}
			break;
		case EN:
			if(lobbySystem == null){
				System.err.println("The FriendSystem works only if our lobby system is installed!");
				Bukkit.getPluginManager().disablePlugin(this);
			} else {
				System.out.println("FriendAddon activated!");
			}
			break;
		default:
			break;
		}
	}

	private void load() {
		if(!cfg.contains("Language")){
			cfg.set("Language", "EN");
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		language = Language.valueOf(cfg.getString("Language"));
		
		lobbySystem = Bukkit.getPluginManager().getPlugin("HashCodeDev-LobbySystem");
	}

}
