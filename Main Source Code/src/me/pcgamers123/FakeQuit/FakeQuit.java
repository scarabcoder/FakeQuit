package me.akaslowfoe.FakeQuit;

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
		String myString = ""; //we're going to store the arguments here    

		for(int i = 0; i < args.length; i++){ //loop threw all the arguments
		    String arg = args[i] + ""; //get the argument, and add a space so that the words get spaced out
		    myString = myString + arg; //add the argument to myString
		}
		
		String fakejoin = ((String) getConfig().get("FakeJoinMessage")).replaceAll("%e", ChatColor.YELLOW + "").replaceAll("player", player.getName() + "")
				.replaceAll("%a", ChatColor.GREEN + "").replaceAll("%d", ChatColor.LIGHT_PURPLE + "").replaceAll("%c", ChatColor.RED + "").replaceAll("%g", ChatColor.GOLD + "")
				.replaceAll("%b", ChatColor.DARK_RED + "").replaceAll("%q", ChatColor.AQUA + "").replaceAll("%b", ChatColor.BLACK + "").replaceAll("%r", ChatColor.GRAY + "")
				.replaceAll("%u", ChatColor.BLUE + "").replaceAll("%w", ChatColor.WHITE + "").replaceAll("%s", ChatColor.DARK_BLUE + "").replaceAll("%p", ChatColor.DARK_GREEN + "")
				.replaceAll("%x", ChatColor.DARK_AQUA + "").replaceAll("%o", ChatColor.DARK_PURPLE + "").replaceAll("%h", ChatColor.DARK_GRAY + "");
		
		if(args.length == 1 && sender.hasPermission("fakequit.join")){
			String fakejoin1 = ((String) getConfig().get("FakeJoinMessage")).replaceAll("%e", ChatColor.YELLOW + "").replaceAll("player", myString + "")
					.replaceAll("%a", ChatColor.GREEN + "").replaceAll("%d", ChatColor.LIGHT_PURPLE + "").replaceAll("%c", ChatColor.RED + "").replaceAll("%g", ChatColor.GOLD + "")
					.replaceAll("%b", ChatColor.DARK_RED + "").replaceAll("%q", ChatColor.AQUA + "").replaceAll("%b", ChatColor.BLACK + "").replaceAll("%r", ChatColor.GRAY + "")
					.replaceAll("%u", ChatColor.BLUE + "").replaceAll("%w", ChatColor.WHITE + "").replaceAll("%s", ChatColor.DARK_BLUE + "").replaceAll("%p", ChatColor.DARK_GREEN + "")
					.replaceAll("%x", ChatColor.DARK_AQUA + "").replaceAll("%o", ChatColor.DARK_PURPLE + "").replaceAll("%h", ChatColor.DARK_GRAY + "");
			Bukkit.broadcastMessage(fakejoin1);
		}
		else {
		
			if(sender.hasPermission("fakequit.join")){
				Bukkit.broadcastMessage(fakejoin);
				
			}
			else if(!sender.hasPermission("fakequit.join")){
				sender.sendMessage(ChatColor.RED + "You do not have permission for this!");
				return true;
		}
	}
	}
	
	
	
	

    if(label.equalsIgnoreCase("fakeleave")){
		String myString = ""; //we're going to store the arguments here    

		for(int i = 0; i < args.length; i++){ //loop threw all the arguments
		    String arg = args[i] + ""; //get the argument, and add a space so that the words get spaced out
		    myString = myString + arg; //add the argument to myString
		}
    	
		String fakeleave = ((String) getConfig().get("FakeLeaveMessage")).replaceAll("%e", ChatColor.YELLOW + "").replaceAll("player", player.getName() + "")
				.replaceAll("%a", ChatColor.GREEN + "").replaceAll("%d", ChatColor.LIGHT_PURPLE + "").replaceAll("%c", ChatColor.RED + "").replaceAll("%g", ChatColor.GOLD + "")
				.replaceAll("%b", ChatColor.DARK_RED + "").replaceAll("%q", ChatColor.AQUA + "").replaceAll("%b", ChatColor.BLACK + "").replaceAll("%r", ChatColor.GRAY + "")
				.replaceAll("%u", ChatColor.BLUE + "").replaceAll("%w", ChatColor.WHITE + "").replaceAll("%s", ChatColor.DARK_BLUE + "").replaceAll("%p", ChatColor.DARK_GREEN + "")
				.replaceAll("%x", ChatColor.DARK_AQUA + "").replaceAll("%o", ChatColor.DARK_PURPLE + "").replaceAll("%h", ChatColor.DARK_GRAY + "");
		
		if(args.length == 1 && sender.hasPermission("fakequit.leave")){
			String fakeleave1 = ((String) getConfig().get("FakeLeaveMessage")).replaceAll("%e", ChatColor.YELLOW + "").replaceAll("player", myString + "")
					.replaceAll("%a", ChatColor.GREEN + "").replaceAll("%d", ChatColor.LIGHT_PURPLE + "").replaceAll("%c", ChatColor.RED + "").replaceAll("%g", ChatColor.GOLD + "")
					.replaceAll("%b", ChatColor.DARK_RED + "").replaceAll("%q", ChatColor.AQUA + "").replaceAll("%b", ChatColor.BLACK + "").replaceAll("%r", ChatColor.GRAY + "")
					.replaceAll("%u", ChatColor.BLUE + "").replaceAll("%w", ChatColor.WHITE + "").replaceAll("%s", ChatColor.DARK_BLUE + "").replaceAll("%p", ChatColor.DARK_GREEN + "")
					.replaceAll("%x", ChatColor.DARK_AQUA + "").replaceAll("%o", ChatColor.DARK_PURPLE + "").replaceAll("%h", ChatColor.DARK_GRAY + "");
			Bukkit.broadcastMessage(fakeleave1);
		}
		else {
		
	    		if(player.hasPermission("fakequit.leave")){
					Bukkit.broadcastMessage(fakeleave);
				}
				else if(!sender.hasPermission("fakequit.leave")){
					sender.sendMessage(ChatColor.RED + "You do not have permission for this!");
					return true;
		}
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
	
	if(label.equalsIgnoreCase("fakequit")){
			
		if(sender.hasPermission("fakequit.help")){
			if(args.length == 1){
			   if(args[0].equalsIgnoreCase("help")){
					sender.sendMessage(ChatColor.BLUE + "/Fakejoin" + ChatColor.GRAY + " -- " + ChatColor.GREEN + "Broadcast to the server that you 'joined' the server.");
					sender.sendMessage(ChatColor.BLUE + "/Fakeleave" + ChatColor.GRAY + " -- " + ChatColor.GREEN + "Broadcast to the server that you 'left' the server.");
					sender.sendMessage(ChatColor.BLUE + "/Fakeload" + ChatColor.GRAY + " -- " + ChatColor.GREEN + "Reload's the FakeQuit Config.");
					
					sender.sendMessage(ChatColor.RED + "Type either " + ChatColor.DARK_AQUA + "/fakequit help fakejoin" + ChatColor.RED + " or " + 
								ChatColor.DARK_AQUA + "/fakequit help fakeleave" + ChatColor.RED + " to see what else is available to these commands.");
				}
			   
			}	
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("fakejoin")){
					sender.sendMessage(ChatColor.GRAY + "------------------------------");
					sender.sendMessage(ChatColor.BLUE + "This command can also be used like this: " + ChatColor.GREEN + "/fakejoin <argument>");
					sender.sendMessage(ChatColor.BLUE + "If you use it like that, depending on the Join message that has been set in the config"
							+ ChatColor.BLUE + " it will broadcast: " + ChatColor.GREEN + "<argument> <message in the config after the word 'player'>");
					sender.sendMessage(" ");
					sender.sendMessage(ChatColor.YELLOW + "If you only use the command " + ChatColor.GREEN + "/fakejoin" + ChatColor.YELLOW + ", it will"
							+ ChatColor.YELLOW + " only broadcast: <You're name> <message in config>");
					sender.sendMessage(ChatColor.GRAY + "------------------------------");
				
				} else if(args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("fakeleave")){
					sender.sendMessage(ChatColor.GRAY + "------------------------------");
					sender.sendMessage(ChatColor.BLUE + "This command can also be used like this: " + ChatColor.GREEN + "/fakeleave <argument>");
					sender.sendMessage(ChatColor.BLUE + "If you use it like that, depending on the Join message that has been set in the config"
							+ ChatColor.BLUE + " it will broadcast: " + ChatColor.GREEN + "<argument> <message in the config after the word 'player'>");
					sender.sendMessage(" ");
					sender.sendMessage(ChatColor.YELLOW + "If you only use the command " + ChatColor.GREEN + "/fakeleave" + ChatColor.YELLOW + ", it will"
							+ ChatColor.YELLOW + " only broadcast: <You're name> <message in config>");
					sender.sendMessage(ChatColor.GRAY + "------------------------------");
				}
			} else if(args.length == 0){
				sender.sendMessage(ChatColor.GREEN + "Use" + ChatColor.GOLD + " /fakequit help " + ChatColor.GREEN + "to show commands!");
	 		}
	    } else {
	    	sender.sendMessage(ChatColor.DARK_RED + "You don't have permssion to do this!");
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





