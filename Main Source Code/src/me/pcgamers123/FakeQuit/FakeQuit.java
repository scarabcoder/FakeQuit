package me.pcgamers123.FakeQuit;

import java.util.logging.Logger;

import me.pcgamers123.FakeQuit.Update.ReleaseType;
import me.pcgamers123.FakeQuit.Update.UpdateResult;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FakeQuit extends JavaPlugin implements Listener{
	public final Logger logger = Logger.getLogger("Minecraft");
	
	
	public static boolean update = false;
	public static String name = "";
	public static ReleaseType type = null;
	public static String version = "";
	public static String link = "";
	
	@SuppressWarnings("unused")
	public void updatePlugin(){
		Update updater = new Update(this, 70536, this.getFile(), Update.UpdateType.NO_VERSION_CHECK, true);
    }
	
	UpdateResult noUpdate = UpdateResult.NO_UPDATE;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		  Update updater = new Update(this, 70536, this.getFile(), Update.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
		  update = updater.getResult() == Update.UpdateResult.UPDATE_AVAILABLE; // Determine if there is an update ready for us
		  name = updater.getLatestName(); // Get the latest name
		  version = updater.getLatestGameVersion(); // Get the latest game version
		  type = updater.getLatestType(); // Get the latest file's type
		  link = updater.getLatestFileLink(); // Get the latest link
		  
		saveDefaultConfig();
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
		String fakejoin = (((String) getConfig().get("FakeJoinMessage")).replaceAll("%e", ChatColor.YELLOW + "").replaceAll("name", sender.getName() + "").replaceAll("%a", ChatColor.GREEN + "").replaceAll("%d", ChatColor.LIGHT_PURPLE + "").replaceAll("%c", ChatColor.RED + "").replaceAll("%g", ChatColor.GOLD + ""));

			if(sender.hasPermission("fakequit.join")){
				Bukkit.broadcastMessage(fakejoin);
				
			}
			else if(!sender.hasPermission("fakequit.join")){
				sender.sendMessage(ChatColor.RED + "You do not have permission for this!");
				return true;
		}
	}

    if(label.equalsIgnoreCase("fakeleave")){			
		String fakeleave = ((String) getConfig().get("FakeLeaveMessage")).replaceAll("%e", ChatColor.YELLOW + "").replaceAll("name", sender.getName() + "").replaceAll("%a", ChatColor.GREEN + "").replaceAll("%d", ChatColor.LIGHT_PURPLE + "").replaceAll("%c", ChatColor.RED + "").replaceAll("%g", ChatColor.GOLD + "");
	    		if(sender.hasPermission("fakequit.leave")){
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
	if(label.equalsIgnoreCase("fakeupdate")){
		
		if(sender.hasPermission("fakequit.update")){
			Update updater = new Update(this, 70536, this.getFile(), Update.UpdateType.NO_DOWNLOAD, false);
			if(updater.getResult() == UpdateResult.UPDATE_AVAILABLE){
			updatePlugin();
			sender.sendMessage(ChatColor.GREEN + "Plugin is beeing updated to the latest file!");
			}
			else if(updater.getResult() == UpdateResult.NO_UPDATE){
				player.sendMessage(ChatColor.GREEN + "[" + ChatColor.LIGHT_PURPLE + "FakeQuit" + ChatColor.GREEN + "]" + ChatColor.RED + " No update avaliable!");
				return false;
			}
		}
		else if(!sender.hasPermission("fakequit.update")){
			player.sendMessage(ChatColor.RED + "You are not permitted to do this!");
			return false;
		}
	}
	return false;
  }
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		Update updater = new Update(this, 70536, this.getFile(), Update.UpdateType.NO_DOWNLOAD, false);
		
		if(player.hasPermission("fakequit.update") && updater.getResult() == Update.UpdateResult.UPDATE_AVAILABLE){
			player.sendMessage(ChatColor.RED + "[" + ChatColor.LIGHT_PURPLE + "FakeQuit" + ChatColor.RED + "]" + ChatColor.GREEN + " An update is available: " + FakeQuit.name + ", a " + FakeQuit.type + " for " + FakeQuit.version + ", available at " + ChatColor.DARK_GREEN + FakeQuit.link);
			// Will look like this - An update is available: AntiCheat v1.5.9, a release for CB 1.6.2-R0.1 available at http://media.curseforge.com/XYZ
			player.sendMessage(ChatColor.GOLD + "Type '/fakeupdate' if you would like to automatically update.");
		}
	}
}

















