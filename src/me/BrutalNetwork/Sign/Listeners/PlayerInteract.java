package me.BrutalNetwork.Sign.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.BrutalNetwork.Sign.Utils.Main;
import me.BrutalNetwork.Sign.Utils.Messages;
import me.BrutalNetwork.Sign.Utils.Utils;

public class PlayerInteract extends Utils implements Listener {

	private Main m;
	public PlayerInteract(Main m) {
		this.m = m;
		Bukkit.getPluginManager().registerEvents(this, this.m);
	}
	
	@EventHandler
	public void on(PlayerInteractEvent e){
		Action a = e.getAction();
		Player p = e.getPlayer();
		try{
			if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK ||
					a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK){
				Material m = e.getClickedBlock().getType();
				if (m == Material.SIGN_POST || m == Material.WALL_SIGN){
					Sign sign = (Sign)e.getClickedBlock().getState();
					if (sign.getLine(0).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Messages.Prefix))){
						if (sign.getLine(1).equalsIgnoreCase("All")){
							if(isMaterial(sign.getLine(2))){
								if (isDouble(sign.getLine(3))){
									sellAll(p, sign);
									return;
								}else{
									p.sendMessage(Messages.ClickException);
									return;
								}
							}
						}else{
							p.sendMessage(Messages.ClickException);
							return;
						}
					}
				}
			}
		}catch(NullPointerException npe){
			
		}
	}	
}
