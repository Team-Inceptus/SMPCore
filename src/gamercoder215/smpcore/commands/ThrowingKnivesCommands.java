package com.stephen.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gamercoder215.smpcore.main;



public class Commands implements CommandExecutor {


	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Use This Command!");
			return true;
		}else {
			Player player = (Player) sender;
			if(player.isOp()) {
				if(cmd.getName().equalsIgnoreCase("givethrowingknife1")) {
					player.getInventory().addItem(ItemsManager.ThrowingKnife);
				}
				else if(cmd.getName().equalsIgnoreCase("givethrowingknife2")) { 
					player.getInventory().addItem(ItemsManager.ThrowingKnife2);
					
				}
				else if(cmd.getName().equalsIgnoreCase("givethrowingknife3")) {
					player.getInventory().addItem(ItemsManager.ThrowingKnife3);
				}
				else if(cmd.getName().equalsIgnoreCase("givethrowingknife4")) {
					player.getInventory().addItem(ItemsManager.ThrowingKnife4);
				}
      }
    }
  }
