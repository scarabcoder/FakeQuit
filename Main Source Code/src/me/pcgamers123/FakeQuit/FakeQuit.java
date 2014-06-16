package me.pcgamers123.FakeQuit;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class FakeQuit extends JavaPlugin implements Listener{
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public void onEnable() {	  
		initiateConfig();
		logger.info("FakeQuit has been enabled!");
	}
	
	public void onDisable() {
		saveConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		Player player = (Player) sender;
		
	if(label.equalsIgnoreCase("fakejoin")){
		String fakejoin = ((String) getConfig().get("FakeJoinMessage")).replaceAll("%e", ChatColor.YELLOW + "").replaceAll("player", player.getName() + "")
				.replaceAll("%a", ChatColor.GREEN + "").replaceAll("%d", ChatColor.LIGHT_PURPLE + "").replaceAll("%c", ChatColor.RED + "").replaceAll("%g", ChatColor.GOLD + "")
				.replaceAll("%b", ChatColor.DARK_RED + "").replaceAll("%q", ChatColor.AQUA + "").replaceAll("%b", ChatColor.BLACK + "").replaceAll("%r", ChatColor.GRAY + "")
				.replaceAll("%u", ChatColor.BLUE + "").replaceAll("%w", ChatColor.WHITE + "").replaceAll("%s", ChatColor.DARK_BLUE + "").replaceAll("%p", ChatColor.DARK_GREEN + "")
				.replaceAll("%x", ChatColor.DARK_AQUA + "").replaceAll("%o", ChatColor.DARK_PURPLE + "").replaceAll("%h", ChatColor.DARK_GRAY + "");
			if(sender.hasPermission("fakequit.join")){
				Bukkit.broadcastMessage(fakejoin);
				
			}
			else if(!sender.hasPermission("fakequit.join")){
				sender.sendMessage(ChatColor.RED + "You do not have permission for this!");
				return true;
		}
	}
	

    if(label.equalsIgnoreCase("fakeleave")){			
		String fakeleave = ((String) getConfig().get("FakeLeaveMessage")).replaceAll("%e", ChatColor.YELLOW + "").replaceAll("player", player.getName() + "")
				.replaceAll("%a", ChatColor.GREEN + "").replaceAll("%d", ChatColor.LIGHT_PURPLE + "").replaceAll("%c", ChatColor.RED + "").replaceAll("%g", ChatColor.GOLD + "")
				.replaceAll("%b", ChatColor.DARK_RED + "").replaceAll("%q", ChatColor.AQUA + "").replaceAll("%b", ChatColor.BLACK + "").replaceAll("%r", ChatColor.GRAY + "")
				.replaceAll("%u", ChatColor.BLUE + "").replaceAll("%w", ChatColor.WHITE + "").replaceAll("%s", ChatColor.DARK_BLUE + "").replaceAll("%p", ChatColor.DARK_GREEN + "")
				.replaceAll("%x", ChatColor.DARK_AQUA + "").replaceAll("%o", ChatColor.DARK_PURPLE + "").replaceAll("%h", ChatColor.DARK_GRAY + "");
	    		if(player.hasPermission("fakequit.leave")){
					Bukkit.broadcastMessage(fakeleave);
				}
				else if(!sender.hasPermission("fakequit.leave")){
					sender.sendMessage(ChatColor.RED + "You do not have permission for this!");
					return true;
		}
	}
	if(label.equalsIgnoreCase("fakeload")){
		if(sender.hasPermission("fakequit.load")){
			reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "FakeQuit Config has been reloaded!");
		}
		else if(!sender.hasPermission("fakequit.load")){
			sender.sendMessage(ChatColor.RED + "You do not have permission for this!");
			return true;
		}
	}
	return false;
  }
	public void initiateConfig(){
		FileConfiguration config = getConfig();
		
		config.options().header("Default Config for plugin: FakeQuit!" + '\n'
		+ "Supported Color Codes" + '\n'
		+ " %e  = Yellow" + '\n'
		+ " %a  = Green" + '\n'
		+ " %d  = Light_Purple" + '\n'
		+ " %c  = Red" + '\n'
		+ " %g  = Gold" + '\n'
		+ " %q  = Aqua" + '\n'
		+ " %b  = Black" + '\n'
		+ " %r  = Gray" + '\n'
		+ " %u  = Blue" + '\n'
		+ " %w  = White" + '\n'
		+ " %s  = Dark_Blue" + '\n'
		+ " %p  = Dark_Green" + '\n'
		+ " %x  = Dark_Aqua" + '\n'
		+ " %b  = Dark_Red" + '\n'
		+ " %o  = Dark_Purple" + '\n'
		+ " %h  = Dark_Gray" + '\n'
		+ "Use 'player' to get the player name of the player who is executing the command!");
		config.addDefault("FakeJoinMessage", "%eplayer joined the game!");
		config.addDefault("FakeLeaveMessage", "%eplayer left the game!");
		
		config.options().copyDefaults(true);
		saveConfig();
		}
}
