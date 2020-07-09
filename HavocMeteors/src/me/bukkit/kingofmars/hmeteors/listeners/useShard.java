package me.bukkit.kingofmars.hmeteors.listeners;

import java.util.ArrayList;
import java.util.Random;


import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.bukkit.kingofmars.hmeteors.Main;
import me.bukkit.kingofmars.hmeteors.files.fileMeteor;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class useShard implements Listener{
	
	private Economy econ = Main.econ;
	
	private  Main plugin;
	
	public int  random(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
	
	int atoa;
	
	public static String convertToInvisibleString(String s) {
        String hidden = "";
        for (char c : s.toCharArray()) hidden += ChatColor.COLOR_CHAR+""+c;
        return hidden;
    }
	
	@EventHandler
	public void useShard (PlayerInteractEvent event ) {
		
		Player p = event.getPlayer();
			
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "Right/Left click to USE");
		
		ItemStack shard = new ItemStack(Material.NETHER_STAR);
		ItemMeta shardm = shard.getItemMeta();
		shardm.setDisplayName(ChatColor.DARK_AQUA.BOLD + "Meteor Shard");
		shardm.setLore(lore);
		shard.setItemMeta(shardm);
		
		
		if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA.BOLD + "Meteor Shard")) {
			
			int atoa = random(1, 1000);
			
			if (atoa > 0 && atoa <= 1) {
				p.getInventory().removeItem(p.getInventory().getItemInHand());
				return;
			}
			
			if (atoa > 1 && atoa <= 101) {
				
				p.getInventory().removeItem(p.getInventory().getItemInHand());
				EconomyResponse r = econ.depositPlayer(p, 50);
				p.sendMessage("§9You have used a shard and received §a50$ §c(10% Chance)");
				return;
			}
			
			if (atoa > 101 && atoa <= 181) {
				
				p.getInventory().removeItem(p.getInventory().getItemInHand());
				EconomyResponse r = econ.depositPlayer(p, 100);
				p.sendMessage("§9You have used a shard and received §a100$ §c(8% Chance)");
				return;
			}
			
			if (atoa > 181 && atoa <= 231) {
				
				p.getInventory().removeItem(p.getInventory().getItemInHand());
				EconomyResponse r = econ.depositPlayer(p, 250);
				p.sendMessage("§9You have used a shard and received §a250$ §c(5% Chance)");
				return;
			}

			if (atoa > 231 && atoa <= 261) {
				
				p.getInventory().removeItem(p.getInventory().getItemInHand());
				EconomyResponse r = econ.depositPlayer(p, 500);
				p.sendMessage("§9You have used a shard and received §a500$ §c(3% Chance)");
				return;
			}
			
		}
	}
	
	
	
	@EventHandler
	public void receiveShard (PlayerPickupItemEvent event) {
		
		int atoa = random(1,10000);
		String atoa1 = Integer.toString(atoa);;
		
		Player p = event.getPlayer();
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(convertToInvisibleString(atoa1));
		lore.add(ChatColor.GREEN + "Right/Left click to USE");
		
		ItemStack shard = new ItemStack(Material.NETHER_STAR);
		ItemMeta shardm = shard.getItemMeta();
		shardm.setDisplayName(ChatColor.DARK_AQUA.BOLD + "Meteor Shard");
		shardm.setLore(lore);
		shard.setItemMeta(shardm);
		
		if (event.getItem().getItemStack().getType().equals(Material.NETHER_STAR)) {
			
			
			p.getInventory().addItem(shard);
			
			event.getItem().remove();
			event.setCancelled(true);
		}
	}
}
