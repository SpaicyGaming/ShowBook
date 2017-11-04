package io.github.spaicygaming.showbook;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShowBookCommands implements CommandExecutor{

	private ShowBook main = ShowBook.getInstance();
	
	public boolean onCommand(CommandSender s, Command cmd, String alias, String[] args){
		
		return false;
	}
	
}
