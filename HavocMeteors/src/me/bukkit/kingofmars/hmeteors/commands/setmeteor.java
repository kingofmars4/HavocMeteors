package me.bukkit.kingofmars.hmeteors.commands;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.bukkit.kingofmars.hmeteors.files.fileMeteor;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.Block;

public class setmeteor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("setmeteor") && sender instanceof Player) {
			
			Player p  = (Player) sender;
			Location l = p.getLocation();
			String world = l.getWorld().getName();
			World w = p.getWorld();
			double x = l.getX();
			double y = l.getY();
			double z = l.getZ();
			
			
			if (args.length == 0) {
				p.sendMessage(ChatColor.DARK_AQUA + "Please specify a number for the meteor point !");
				return true;
			}	
			
			if (!sender.hasPermission("meteors.set")) {
				p.sendMessage(ChatColor.DARK_AQUA + "You dont have permissions to execute this command !");
				return true;
			}
			
			fileMeteor.get().set("Meteors ." + args[0] + ".world",world);
			fileMeteor.get().set("Meteors ." + args[0] + ".x",x);
			fileMeteor.get().set("Meteors ." + args[0] + ".y",y);
			fileMeteor.get().set("Meteors ." + args[0] + ".z",z);
			fileMeteor.save();
			fileMeteor.reload();
			
			
			p.sendMessage(ChatColor.DARK_AQUA + "Your current location has been set as Meteor" + ChatColor.YELLOW + "#" + args[0] + ChatColor.DARK_AQUA + "!");
			w.playEffect(l, Effect.EXPLOSION_HUGE, 1, 1);
			
			return true;
		}
		return false;
	}
}
