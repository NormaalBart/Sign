package me.BrutalNetwork.Sign.Utils;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.BrutalNetwork.Sign.Listeners.PlayerInteract;
import me.BrutalNetwork.Sign.Listeners.SignCreate;
import net.milkbowl.vault.economy.Economy;


public class Main extends JavaPlugin{
	
	public static Plugin p;
	public static Economy economy = null;
	
	public void onEnable(){
		p = this;

		new SignCreate(this);
		new PlayerInteract(this);
		setupEconomy();
		Messages.makeConfig();
		
		if(getConfig().getString("Messages.noItem") == null){
			getConfig().set("Messages.noItem", "&3» &cYou don't have the right item!");
			saveConfig();
		}

		Messages.loadConfig();
	}
	
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer()
        		.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
}
