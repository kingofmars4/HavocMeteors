package me.bukkit.kingofmars.hmeteors.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.PluginEvent;

import me.bukkit.kingofmars.hmeteors.files.fileMeteor;

public class meteorFall implements Listener{
	
	FileConfiguration i = fileMeteor.get();
	
	World w = Bukkit.getWorld("world");
	double x = i.getDouble("x");
	double y = i.getDouble("y");;
	double z = i.getDouble("z");
	Location l = new Location (w, x, y, z);
	
	@EventHandler
	public void onPluginEnable(PluginEnableEvent plugin) {
		System.out.println("ALGO EXPLODU!");
		w.playEffect(l, Effect.EXPLOSION_HUGE, 1, 1);
	}
	
	
}
