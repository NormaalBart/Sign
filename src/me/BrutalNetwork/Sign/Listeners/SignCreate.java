package me.BrutalNetwork.Sign.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import me.BrutalNetwork.Sign.Utils.Main;
import me.BrutalNetwork.Sign.Utils.Messages;
import me.BrutalNetwork.Sign.Utils.Utils;

public class SignCreate extends Utils implements Listener {
	
	private Main m;
	public SignCreate(Main m) {
		this.m = m;
		Bukkit.getPluginManager().registerEvents(this, this.m);
	}
	
	@EventHandler
	public void on(SignChangeEvent e){
		if(e.getLine(0).equalsIgnoreCase("[SSell]")){
			if (e.getPlayer().hasPermission("Sign.create")){
				if(e.getLine(1).equalsIgnoreCase("All")){
					if(isMaterial(e.getLine(2))){
						if (isDouble(e.getLine(3))){
							e.getPlayer().sendMessage(Messages.Succesfull);
							e.setLine(0, Messages.Prefix);
							return;
						}else{
							for (String s : Messages.formatException){
								e.getPlayer().sendMessage(s);
							}
						}
					}else{
						for (String s : Messages.formatException){
							e.getPlayer().sendMessage(s);
						}
					}
				}
				else{
					for (String s : Messages.formatException){
						e.getPlayer().sendMessage(s);
					}
				}
			}else{
				e.getPlayer().sendMessage(Messages.noPerm);
			}
		}
	}
}
