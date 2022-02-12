package creativeclient.commands;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class BaseCommand {
	
	public String name, description, syntax;
	public List<String> aliases = new ArrayList<String>();
	
	
	public BaseCommand(String name, String description, String syntax, String... aliases) {
		this.name = name;
		this.description = description;
		this.syntax = syntax;
		this.aliases = Arrays.asList(aliases);
	}
	
	public abstract void onCommand(String[] args, String command) throws Exception;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getSyntax() {
		return syntax;
	}


	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}


	public List<String> getAliases() {
		return aliases;
	}


	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
	
	
	
}