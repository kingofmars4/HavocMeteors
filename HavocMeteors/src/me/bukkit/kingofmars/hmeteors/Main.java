package me.bukkit.kingofmars.hmeteors;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.bukkit.kingofmars.hmeteors.commands.setmeteor;
import me.bukkit.kingofmars.hmeteors.files.fileMeteor;
import me.bukkit.kingofmars.hmeteors.listeners.meteorFall;
import net.minecraft.server.v1_8_R3.EntityFireball;

public class Main extends JavaPlugin{
	
	private static Plugin plugin;
	
	public void onEnable() {
		plugin = this;
		
		ligarConfig();
		ligarCustomConfigs();
		ligarComandos();
		registerEvents(this, new meteorFall());
	}
	
	public void onDisable() {
		
	}
	
	public void ligarListeners() {
		
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
		}
	
	public void ligarConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void ligarCustomConfigs(){
		fileMeteor.setup();
		fileMeteor.get().options().copyDefaults(true);
		fileMeteor.save();
	}
	
	public void ligarComandos() {
		getCommand("setmeteor").setExecutor(new setmeteor());
	}
	
}
