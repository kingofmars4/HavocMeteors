package me.bukkit.kingofmars.hmeteors;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.print.CancelablePrintJob;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.bukkit.kingofmars.hmeteors.commands.setmeteor;
import me.bukkit.kingofmars.hmeteors.files.fileMeteor;
import me.bukkit.kingofmars.hmeteors.listeners.meteorFall;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EntityFireball;

public class Main extends JavaPlugin{
	
	private static Plugin plugin;
	
	public int atoa;
	
	public void onEnable() {
		plugin = this;
		
		ligarConfig();
		ligarCustomConfigs();
		ligarComandos();
		timer();
		timer2();
		timer3();
		timerUltimo();
		acabouQueda();
		
	}
	
	public void onDisable() {
		
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
	
	public int mu_rand(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
	
	public void acabouQueda()
    {
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable()
        {
        	
            public void run()
            {
				atoa = mu_rand(1, 10);
				
			
            }
        }
        , 0, 12100); // manda logo no inicio / espera 10 min e 10 sec
    }
    
	public void timer()
    {
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable()
        {
        	
            public void run()
            {
            	System.out.println("Atoa igual a "+ atoa);
				Bukkit.broadcastMessage(ChatColor.RED + "An meteor will fall in under 10 minutes at " + ChatColor.WHITE + "X:" + fileMeteor.get().getInt("Meteors ." + atoa + ".x") + " Z:" + fileMeteor.get().getInt("Meteors ." + atoa + ".z"));
            }
        }
        , 20, 12400); // manda logo no inicio / espera 10 min e 10 sec
    }
	
	public void timer2()
    {
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable()
        {
        	
            public void run()
            {
            	System.out.println("Atoa igual a "+ atoa);
            	Bukkit.broadcastMessage(ChatColor.RED + "An meteor will fall in under 5 minutes at " + ChatColor.WHITE + "X:" + fileMeteor.get().getInt("Meteors ." + atoa + ".x") + " Z:" + fileMeteor.get().getInt("Meteors ." + atoa + ".z"));
            	}
            }
        , 6020, 12400); // espera 5 min e 10 sec
    }
	
	public void timer3()
    {
		
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable()
        {
            public void run()
            {
            	System.out.println("Atoa igual a "+ atoa);
            	Bukkit.broadcastMessage(ChatColor.RED + "An meteor will fall in under 1 minute at " + ChatColor.WHITE + "X:" + fileMeteor.get().getInt("Meteors ." + atoa + ".x") + " Z:" + fileMeteor.get().getInt("Meteors ." + atoa + ".z"));
            }
        }
        , 10820, 12400); // manda espera 10 sgundos
    }
	
	public void timerUltimo()
    {
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable()
        {

            public void run()
            {
            	System.out.println("Atoa igual a "+ atoa);
            	World w = Bukkit.getWorld("world");
    		    FileConfiguration i = fileMeteor.get();
    		    double x = i.getDouble("Meteors ." + atoa + ".x");
    			double y = i.getDouble("Meteors ." + atoa + ".y");
    			double z = i.getDouble("Meteors ." + atoa + ".z");
    			
            	outroRunnable();
            	Bukkit.broadcastMessage(ChatColor.RED + "An meteor is falling on the coordinates: " + ChatColor.WHITE + "X:" + fileMeteor.get().getInt("Meteors ." + atoa + ".x") + " Z:" + fileMeteor.get().getInt("Meteors ." + atoa + ".z"));
            }
        }
        , 60, 12400); 
    }
	
	public void outroRunnable() {		
		final BukkitTask loop = new BukkitRunnable() {
			
			
		    World w = Bukkit.getWorld("world");
		    FileConfiguration i = fileMeteor.get();
		    double x = i.getDouble("Meteors ." + atoa + ".x");
			double y = i.getDouble("Meteors ." + atoa + ".y") + 120;
			double z = i.getDouble("Meteors ." + atoa + ".z");
		    
			@Override
			public void run() {
				
				
				y = y - 2;
				Location l = new Location (w, x, y, z);
				w.playEffect(l, Effect.EXPLOSION_HUGE, 300);
				System.out.println("explodiuuu em " + x + " " + y + " " + z);
				
				if (y <= i.getDouble("Meteors ." + atoa + ".y")) {
					cancel();
					
					ItemStack sFragment = new ItemStack(Material.NETHER_STAR);
					
					/*ItemMeta fragment = sFragment.getItemMeta();
				    fragment.setDisplayName(ChatColor.BOLD + "Meteor Shard");*/
					
					y = i.getDouble("Meteors ." + atoa + ".y") + 5;
					
					System.out.println("dropou em " + l);
					w.dropItem(l, sFragment);
				};
			}
		}.runTaskTimerAsynchronously(this, 5, 5);
		
	}
	
	
	
}
