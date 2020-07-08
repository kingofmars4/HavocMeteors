package me.bukkit.kingofmars.hmeteors.listeners;


import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.bukkit.kingofmars.hmeteors.Main;
import me.bukkit.kingofmars.hmeteors.files.fileMeteor;

public class meteorFall implements Listener {
	
	
	
	FileConfiguration i = fileMeteor.get();
	
	World w = Bukkit.getWorld("world");
	double x = i.getDouble("Meteors .1.x");
	double y = i.getDouble("Meteors .1.y");
	double z = i.getDouble("Meteors .1.z");
	Location l = new Location (w, x, y, z);
	
	public void fallingMeteor() {
		
		w.playEffect(l, Effect.EXPLOSION_HUGE, 300);
		
	}
	
	
}
