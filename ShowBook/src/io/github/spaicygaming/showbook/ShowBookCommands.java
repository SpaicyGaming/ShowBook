package io.github.spaicygaming.showbook;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShowBookCommands implements CommandExecutor{

	private ShowBook main = ShowBook.getInstance();
	
	public boolean onCommand(CommandSender s, Command cmd, String alias, String[] args){
		
		if (!alias.equalsIgnoreCase("showbook")){
			return false;
		}
		
		// Reload
		if (args.length == 1 && args[0].equalsIgnoreCase("reload")){
			if (!s.hasPermission("showbook.reload")){
				s.sendMessage(ChatColor.RED + "I'm sorry, you don't have enought permissions.");
				return false;
			}
			main.reloadConfig();
			s.sendMessage(ChatColor.RED + "Config Reloaded");
		}
		// Else
		else{
			printHelpMenu(s);
		}
		
		return false;
	}
	
	private void printHelpMenu(CommandSender sender){
		List<String> coloredMessages = main.c(main.getConfig().getStringList("helpMenu"));
		for (String message : coloredMessages){
			sender.sendMessage(message);
		}
	}
	
	
}
