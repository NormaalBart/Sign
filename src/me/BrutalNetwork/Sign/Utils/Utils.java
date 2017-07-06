package me.BrutalNetwork.Sign.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.scoreboard.CraftScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import me.BrutalNetwork.Balkan.Utils.BoosterAPI;

public class Utils {

	public boolean isDouble(String line) {
		String l = line.replaceAll("[^0-9.,]+","");
		try{
			 Double.parseDouble(l);
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
	
	public boolean isMaterial(String line) {
		Material mat = null;
		line.toLowerCase();
		if(line.equalsIgnoreCase("log2")){
			return true;
		}else if (line.equalsIgnoreCase("log3")){
			return true;
		}else if (line.equalsIgnoreCase("log4")){
			return true;
		}else if (line.equalsIgnoreCase("log5")){
			return true;
		}else if (line.equalsIgnoreCase("log6")){
			return true;
		}else{
			if (line.contains(" ")){
				line = line.replace(" ", "_");
			}if(line.equalsIgnoreCase("lapis") || line.equalsIgnoreCase("lapis_lapzuli")){
				line = "ink_sack";
			}
			if(mat == null){
				mat = Material.matchMaterial(line);	
			}
			if (mat == null){
				return false;
			}else{
				return true;
			}	
		}
	}
	
	public void sellAll(Player p, Sign sign) {
		try{
			if(sign.getLine(2).toLowerCase().contains("log")){
				sellAllLog(p, sign);
				return;
			}
			Inventory inv = p.getInventory();
			Material m = getMaterial(sign.getLine(2));
			Double price = getDouble(sign.getLine(3));
			Double fullPrice = 0.0;
			Integer items = 0;
			for (int i = 0; i < inv.getSize(); i++){
				ItemStack item = inv.getItem(i);
				if (item == null || item.getType() == null){
				}else{
					if (item.getType() == m){
						items = items+item.getAmount();
						fullPrice = fullPrice+(item.getAmount()*price);
						inv.removeItem(item);
					}	
				}		
			}	
			if(items == 0){
				p.sendMessage(Messages.noItem);
				return;
			}
			if (p.hasPermission("Sign.multiply.7")){
				fullPrice = fullPrice*7;
			}else if (p.hasPermission("Sign.multiply.6.5")){
				fullPrice = fullPrice*6.5;
			}else if (p.hasPermission("Sign.multiply.6")){
				fullPrice = fullPrice*6;
			}else if (p.hasPermission("Sign.multiply.5.5")){
				fullPrice = fullPrice*5.5;
			}else if (p.hasPermission("Sign.multiply.5")){
				fullPrice = fullPrice*5;
			}else if (p.hasPermission("Sign.multiply.4.5")){
				fullPrice = fullPrice*4.5;
			}else if (p.hasPermission("Sign.multiply.4")){
				fullPrice = fullPrice*4;
			}else if (p.hasPermission("Sign.multiply.3.5")){
				fullPrice = fullPrice*3.5;
			}else if (p.hasPermission("Sign.multiply.3")){
				fullPrice = fullPrice*3;
			}else if (p.hasPermission("Sign.multiply.2.5")){
				fullPrice = fullPrice*2.5;
			}else if (p.hasPermission("Sign.multiply.2")){
				fullPrice = fullPrice*2;
			}else if (p.hasPermission("Sign.multiply.1.5")){
				fullPrice = fullPrice*1.5;
			}
			if (BoosterAPI.BoosterStarted() == true){
				fullPrice = fullPrice * Messages.multiply;
			}
			
			fullPrice = round(fullPrice, 2);
			
			Main.economy.depositPlayer(p, fullPrice);
			p.sendMessage(Messages.SoldItems
					.replace("{AMOUNT}", "" + items)
					.replace("{MONEY}", "" + fullPrice));
		}catch(NullPointerException npe){
			p.sendMessage(Messages.noItem);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void sellAllLog(Player p, Sign sign) {
		try{
			Inventory inv = p.getInventory();
			ItemStack is;
			String line = sign.getLine(2);
			if(equals(line, "log")){
				is = new ItemStack(Material.LOG, 1);
			}else if (equals(line, "log2")){
				is = new ItemStack(Material.LOG, 1, (byte)1);
			}else if(equals(line, "log3")){
				is = new ItemStack(Material.LOG, 1, (byte)2);
			}else if(equals(line, "log4")){
				is = new ItemStack(Material.LOG, 1, (byte)3);
			}else if(equals(line, "log5")){
				is = new ItemStack(Material.LOG_2, 1);
			}else if(equals(line, "log6")){
				is = new ItemStack(Material.LOG_2, 1, (byte)1);
			}else{
				is = new ItemStack(Material.LOG, 1);
			}
			Double price = getDouble(sign.getLine(3));
			Double fullPrice = 0.0;
			Integer items = 0;
			for (int i = 0; i < inv.getSize(); i++){
				ItemStack item = inv.getItem(i);
				if (item == null || item.getType() == null){
				}else{
					if (item.getType() == is.getType() && is.getData().getData() == item.getData().getData()){
						items = items+item.getAmount();
						fullPrice = fullPrice+(item.getAmount()*price);
						inv.removeItem(item);
					}	
				}		
			}
			if(items == 0){
				p.sendMessage(Messages.noItem);
				return;
			}
			if (p.hasPermission("Sign.multiply.7")){
				fullPrice = fullPrice*7;
			}else if (p.hasPermission("Sign.multiply.6.5")){
				fullPrice = fullPrice*6.5;
			}else if (p.hasPermission("Sign.multiply.6")){
				fullPrice = fullPrice*6;
			}else if (p.hasPermission("Sign.multiply.5.5")){
				fullPrice = fullPrice*5.5;
			}else if (p.hasPermission("Sign.multiply.5")){
				fullPrice = fullPrice*5;
			}else if (p.hasPermission("Sign.multiply.4.5")){
				fullPrice = fullPrice*4.5;
			}else if (p.hasPermission("Sign.multiply.4")){
				fullPrice = fullPrice*4;
			}else if (p.hasPermission("Sign.multiply.3.5")){
				fullPrice = fullPrice*3.5;
			}else if (p.hasPermission("Sign.multiply.3")){
				fullPrice = fullPrice*3;
			}else if (p.hasPermission("Sign.multiply.2.5")){
				fullPrice = fullPrice*2.5;
			}else if (p.hasPermission("Sign.multiply.2")){
				fullPrice = fullPrice*2;
			}else if (p.hasPermission("Sign.multiply.1.5")){
				fullPrice = fullPrice*1.5;
			}
			if (BoosterAPI.BoosterStarted() == true){
				fullPrice = fullPrice * Messages.multiply;
			}
			fullPrice = round(fullPrice, 2);
			
			Main.economy.depositPlayer(p, fullPrice);
			p.sendMessage(Messages.SoldItems
					.replace("{AMOUNT}", "" + items)
					.replace("{MONEY}", "" + fullPrice));
		}catch(NullPointerException npe){
			p.sendMessage(Messages.noItem);
		}
	}
	
	private boolean equals(String line, String line2){
		if(line.equalsIgnoreCase(line2)) return true;
		return false;
	}

	@SuppressWarnings("unused")
	public Double getDouble(String line) {
		String l = line.replaceAll("[^0-9.,]+","");
		Double i;
		try{
			 i = Double.parseDouble(l);
		}catch(NumberFormatException nfe){
			return null;
		}
		if (i == null){
			return null;
		}else{
			return i;
		}
	}
	
	private Material getMaterial(String material){
		Material mat;
		material.toLowerCase();
		if (material.contains(" ")){
			material = material.replace(" ", "_");
		}
		if(material.equalsIgnoreCase("lapis") || material.equalsIgnoreCase("lapis_lapzuli")){
			material = "INK_SACK";
		}
		mat = Material.matchMaterial(material);
		return mat;
	}
	
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
