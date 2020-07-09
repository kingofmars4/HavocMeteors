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
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.bukkit.kingofmars.hmeteors.commands.setmeteor;
import me.bukkit.kingofmars.hmeteors.files.fileMeteor;
import me.bukkit.kingofmars.hmeteors.listeners.useShard;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_8_R3.EntityFireball;

public class Main extends JavaPlugin{
	
	private static Plugin plugin;
	public int atoa;
	
	public static Economy econ;
	
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
		
		if (!setupEconomy()) {
			Bukkit.shutdown();
		}
		
		registerEvents(this, new useShard());
		
		
	}
	
	public void onDisable() {
		
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
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
        , 0, 12450); // manda logo no inicio / espera 10 min e 10 sec
    }
    
	
	public void timer()
    {
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable()
        {
        	
            public void run()
            {
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
            	Bukkit.broadcastMessage(ChatColor.RED + "An meteor will fall in under 1 minute at " + ChatColor.WHITE + "X:" + fileMeteor.get().getInt("Meteors ." + atoa + ".x") + " Z:" + fileMeteor.get().getInt("Meteors ." + atoa + ".z"));
            }
        }
        , 10820, 12400); 
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
        , /**120*/20, 12400); 
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
					
					y = y - 4;
					
					Location l = new Location (w, x, y, z);
					w.playEffect(l, Effect.EXPLOSION_HUGE, 300);
					w.playEffect(l, Effect.BLAZE_SHOOT, 300);
					w.playEffect(l, Effect.SMOKE, 300);
					
					int atoa1 = mu_rand(1, 1000);
					int atoa2 = mu_rand(1, 1000);
					int atoa3 = mu_rand(1, 1000);
					int atoa4 = mu_rand(1, 1000);
					int atoa5 = mu_rand(1, 1000);
					String atoa11 = Integer.toString(atoa1);
					String atoa12 = Integer.toString(atoa2);
					String atoa13 = Integer.toString(atoa3);
					String atoa14 = Integer.toString(atoa4);
					String atoa15 = Integer.toString(atoa5);
					
					if (y <= i.getDouble("Meteors ." + atoa + ".y")) {
						
						cancel();
						
						y = y + 1;
						
						int atoar11 = mu_rand(-4, 4);
						int atoar12 = mu_rand(-4, 4);
						double xr1 = i.getDouble("Meteors ." + atoa + ".x") + atoar11;
						double zr1 = i.getDouble("Meteors ." + atoa + ".z") + atoar12;
						Location lr1 = new Location(w, xr1, y, zr1);
						
						int atoar21 = mu_rand(-4, 4);
						int atoar22 = mu_rand(-4, 4);
						double xr2 = i.getDouble("Meteors ." + atoa + ".x") + atoar21;
						double zr2 = i.getDouble("Meteors ." + atoa + ".z") + atoar22;
						Location lr2 = new Location(w, xr2, y, zr2);
						
						int atoar31 = mu_rand(-4, 4);
						int atoar32 = mu_rand(-4, 4);
						double xr3 = i.getDouble("Meteors ." + atoa + ".x") + atoar31;
						double zr3 = i.getDouble("Meteors ." + atoa + ".z") + atoar32;
						Location lr3 = new Location(w, xr3, y, zr3);
						
						int atoar41 = mu_rand(-4, 4);
						int atoar42 = mu_rand(-4, 4);
						double xr4 = i.getDouble("Meteors ." + atoa + ".x") + atoar41;
						double zr4 = i.getDouble("Meteors ." + atoa + ".z") + atoar42;
						Location lr4 = new Location(w, xr4, y, zr4);
						
						int atoar51 = mu_rand(-4, 4);
						int atoar52 = mu_rand(-4, 4);
						double xr5 = i.getDouble("Meteors ." + atoa + ".x") + atoar51;
						double zr5 = i.getDouble("Meteors ." + atoa + ".z") + atoar52;
						Location lr5 = new Location(w, xr5, y, zr5);
						
						ArrayList<String> lore1 = new ArrayList<String>();
						lore1.add(atoa11);
						ItemStack shard1 = new ItemStack(Material.NETHER_STAR);
						ItemMeta shardm1 = shard1.getItemMeta();
						shardm1.setLore(lore1);
						shard1.setItemMeta(shardm1);
						
						ArrayList<String> lore2 = new ArrayList<String>();
						lore2.add(atoa12);
						ItemStack shard2 = new ItemStack(Material.NETHER_STAR);
						ItemMeta shardm2 = shard2.getItemMeta();
						shardm2.setLore(lore2);
						shard2.setItemMeta(shardm2);
						
						ArrayList<String> lore3 = new ArrayList<String>();
						lore3.add(atoa13);
						ItemStack shard3 = new ItemStack(Material.NETHER_STAR);
						ItemMeta shardm3 = shard3.getItemMeta();
						shardm3.setLore(lore3);
						shard3.setItemMeta(shardm3);
						
						ArrayList<String> lore4 = new ArrayList<String>();
						lore4.add(atoa14);
						ItemStack shard4 = new ItemStack(Material.NETHER_STAR);
						ItemMeta shardm4 = shard4.getItemMeta();
						shardm4.setLore(lore4);
						shard4.setItemMeta(shardm4);
						
						ArrayList<String> lore5 = new ArrayList<String>();
						lore5.add(atoa15);
						ItemStack shard5 = new ItemStack(Material.NETHER_STAR);
						ItemMeta shardm5 = shard5.getItemMeta();
						shardm5.setLore(lore5);
						shard5.setItemMeta(shardm5);
						
						w.dropItemNaturally(lr1, shard1);
						w.dropItemNaturally(lr2, shard2);
						w.dropItemNaturally(lr3, shard3);
						w.dropItemNaturally(lr4, shard4);
						w.dropItemNaturally(lr5, shard5);
						
						
					}
				}
				
			}.runTaskTimer(this, 5, 5);
			
		}
				

	
}
