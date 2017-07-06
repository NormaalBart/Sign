package me.BrutalNetwork.Sign.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {
	
	public static void makeConfig() {
		File file = null;
        try {
            if (!Main.p.getDataFolder().exists()) {
            	Main.p.getDataFolder().mkdirs();
            }
            file = new File(Main.p.getDataFolder(), "config.yml");
            if (!file.exists()) {
            	Main.p.getLogger().info("config.yml not found, creating!");
            	Main.p.saveResource("config.yml", true);
            } else {
            	Main.p.getLogger().info("config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void loadConfig() {
        File file = new File(Main.p.getDataFolder(), "config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		noPerm = config.getString("Messages.noPerm").replace("&", "§");
		Succesfull = config.getString("Messages.Succesfull").replace("&", "§");
		Prefix = config.getString("Sign.Prefix").replace("&", "§");
		ClickException = config.getString("Messages.ClickException").replace("&", "§");
		SoldItems = config.getString("Messages.SoldItems").replace("&", "§");
		multiply = config.getDouble("Sign.Multiply");
		noItem = config.getString("Messages.noItem").replace("&", "§");
		for (String s : config.getStringList("Messages.formatException")){
			formatException.add(s.replace("&" , "§"));
		}
	}
	
	public static String noPerm = "&3» &cYou don't have the right permission to create a sell sign";
	public static String Succesfull = "&3» &7The sign is succesfully created!";
	public static String Prefix = "&7[&aSell&7]";
	public static String SoldItems = "&3» &7You succesfully sold &a{AMOUNT} &7items for &a{MONEY}&7!";
	public static String ClickException = "&3» &cThe sign isn't properly configured!";
	public static List<String> formatException = new ArrayList<>();
	public static Double multiply = 2.5;
	public static String noItem = "§3» §cYou don't have the right item!";
}
