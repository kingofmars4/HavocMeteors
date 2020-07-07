package me.bukkit.kingofmars.hmeteors.files;

import java.io.File;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class fileMeteor {

	private static File file;
	private static FileConfiguration fileMeteor;
	
	public static void setup () {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("HavocMeteors").getDataFolder(), "meteor.yml");
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				
			}
		}
		
		fileMeteor = YamlConfiguration.loadConfiguration(file);
	}
	
	public static FileConfiguration get() {
		return fileMeteor;
	}
	
	public static void save() {
		try {
			fileMeteor.save(file);
		} catch (IOException e) {
			System.out.println("Não foi possivel salvar os ficheiros de meteor.yml");
		}
	}
	
	public static void reload() {
		fileMeteor = YamlConfiguration.loadConfiguration(file);
	}
	
}
