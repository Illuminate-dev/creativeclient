package creativeclient.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import creativeclient.Creative;
import creativeclient.commands.impl.CommandStats;
import creativeclient.event.EventTarget;
import creativeclient.event.impl.SendMessageEvent;

public class CommandManager {
	
	private ArrayList<BaseCommand> commands;
	String prefix = "/";
	
	public CommandManager() {
		commands = new ArrayList();
		
		addCommand(new CommandStats());
	}
	
	public void addCommand(BaseCommand c) {
		commands.add(c);
	}
	
	public ArrayList<BaseCommand> getCommands() {
		return commands;
	}
	
	public void callCommand(String input) {
		String[] split = input.split(" ");
		String command = split[0];
		String args = input.substring(command.length()).trim();
		
		
		for(BaseCommand c : getCommands()) {
			
			if(c.getAliases().contains(command.toLowerCase()) || c.getName().contains(command.toLowerCase())) {
				
				try {
					c.onCommand(args.split(" "), args);
				} catch (Exception e) {
					Creative.addChatMessage("Invalid command usage.");
					Creative.addChatMessage(c.getSyntax());
					e.printStackTrace();
				}
				
				return;
			}
		}
		Creative.addChatMessage("Command not found.");
	}


}
