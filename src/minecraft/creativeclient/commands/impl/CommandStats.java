package creativeclient.commands.impl;

import creativeclient.commands.BaseCommand;
import creativeclient.util.MinecraftAPI;
import creativeclient.util.StringUtils;
import net.hypixel.api.util.ILeveling;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import creativeclient.Creative;

public class CommandStats extends BaseCommand {

	public CommandStats() {
		super("stats", "Gets server ping.", "stats", "s");
		
	}

	@Override
	public void onCommand(String[] args, String command) {
		String getuuid;
		
		List<String> mode = new ArrayList<String>();
		
		String arg1 = args[0];
		
		if(args[0].equals("player")) {
			if(args.length == 2) {
				arg1 = args[1];
			} else {
				arg1 = "";
			}
			
			mode.add("none");
		} else if(args[0].equals("duels")) {
			if(args.length == 2) {
				arg1 = args[1];
			} else {
				arg1 = "";
			}
			mode.add("duels");
		} else if(args[0].equals("bedwars")) {
			if(args.length == 2) {
				arg1 = args[1];
			} else {
				arg1 = "";
			}
			mode.add("bedwars");
		} else {
			mode.add("none");
			
		}
		
		
		try {
			if(!arg1.equals("")) {
				
				getuuid = MinecraftAPI.getUUID(arg1);
				
				
			} else {
				
				getuuid = MinecraftAPI.getUUID(Minecraft.getMinecraft().thePlayer.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
			getuuid = null;
		}
		
		if(getuuid == null) {
			return;
		}
		
		System.out.println(getuuid);
		
		
		try {
			Creative.INSTANCE.API.getPlayerByUuid(UUID.fromString(getuuid)).whenComplete((response, error) -> {
				
			    // Check if there was an API error
			    if (error != null) {
			        error.printStackTrace();
			        return;
			    }
			    
			    // Get the player object from the response. This object stores any available information about a given player
			    JsonObject player = response.getPlayer();
			    

			    if (player != null) {

			        /*
			        The player was found; print some basic info about them
			        Not every player has all of these fields (for example, staff members don't have
			        "mostRecentGameType"), so I added a handy little method to check if a field
			        exists. If it doesn't, it returns "N/A" rather than throwing a
			        NullPounterException
			         */
			    	if(mode.get(0).equals("none")) {
			    		addChatDivider("§9");
			    		addChatMessage(StringUtils.getFormattedName(player));
			    		
				        double networkLevel = ILeveling.getExactLevel(StringUtils.getFieldOr0("networkExp", player));
				        networkLevel = Math.round(networkLevel * 100);
				        networkLevel /= 100;
				        System.out.println(player.get("achievements").getAsJsonObject().get("bedwars_level"));
				        addChatMessage(("Network Level: " + String.valueOf(networkLevel)));
				        addChatMessage("Duels Title: " + StringUtils.getFormattedDuelsTitle(player));
				        addChatMessage("Bedwars Level: " + StringUtils.getFormattedBedwarsLevel(player.get("achievements").getAsJsonObject().get("bedwars_level").getAsInt()));
				        addChatMessage("Skywars Level: " + player.get("stats").getAsJsonObject().get("SkyWars").getAsJsonObject().get("levelFormatted").getAsString().replaceAll("\"", ""));
				        addChatMessage("Karma: " + StringUtils.getFieldOr0("karma", player));
				        addChatMessage(StringUtils.getFormattedOnline(player));
				        addChatDivider("§9");
			    	} else if(mode.get(0).equals("duels")) {
			    		addChatDivider("§9");
			    		JsonObject duelsData = player.get("stats").getAsJsonObject().get("Duels").getAsJsonObject();
			    		float wins = StringUtils.getFieldOr0("wins", duelsData);
			    		float losses = StringUtils.getFieldOr0("losses", duelsData);
			    		float kills = StringUtils.getFieldOr0("kills", duelsData);
			    		float deaths = StringUtils.getFieldOr0("deaths", duelsData);
			    		addChatMessage(StringUtils.getFormattedName(player));
			    		addChatMessage("Duels Title: " + StringUtils.getFormattedDuelsTitle(player));
			    		addChatMessage("Duels Wins: " + wins);
			    		addChatMessage("Duels Kills: " + duelsData.get("kills").getAsInt());
			    		float wlr = Math.round((wins / losses) * 100);
			    		wlr /= 100;
			    		
			    		addChatMessage("WLR: " + StringUtils.goodKDR(wlr));
			    		float kdr = Math.round((kills / deaths) * 100);
			    		kdr /= 100;
			    		
			    		addChatMessage("KDR: " + StringUtils.goodKDR(kdr));
			    		addChatMessage("Best Game: " + StringUtils.getFormattedBestGameDuels(player));
			    		addChatDivider("§9");
			    	} else if(mode.get(0).equals("bedwars")) {
			    		addChatDivider("§9");
			    		addChatMessage(StringUtils.getFormattedName(player));
			    		int level = player.get("achievements").getAsJsonObject().get("bedwars_level").getAsInt();
			    		addChatMessage("Level: " + StringUtils.getFormattedBedwarsLevel(level));
			    		JsonObject bedwarsData = player.get("stats").getAsJsonObject().get("Bedwars").getAsJsonObject();
			    		int wins = bedwarsData.get("wins_bedwars").getAsInt();
			    		int losses = bedwarsData.get("losses_bedwars").getAsInt();
			    		int kills = bedwarsData.get("kills_bedwars").getAsInt();
			    		int deaths = bedwarsData.get("deaths_bedwars").getAsInt();
			    		int finalkills = bedwarsData.get("final_kills_bedwars").getAsInt();
			    		int finaldeaths = bedwarsData.get("final_deaths_bedwars").getAsInt();
			    		float wlr = Math.round(((wins * 100) / (losses)));
			    		wlr /= 100;
			    		float kdr = Math.round(((kills * 100) / (deaths)));
			    		kdr /= 100;
			    		float fkdr = Math.round(((finalkills * 100) / (finaldeaths)));
			    		fkdr /= 100;
			    		addChatMessage("§aWins: §f" + wins);
			    		addChatMessage("§aWLR: §f" + wlr);
			    		addChatMessage("§cKills: §f" + kills);
			    		addChatMessage("§cKDR: §f" + kdr);
			    		addChatMessage("§4Final Kills: §f" + finalkills);
			    		addChatMessage("§4FKDR: §f" + fkdr);
			    		int nextfkdr = (int) fkdr + 1;
			    		int tillnextfkdr = (nextfkdr * finaldeaths) - finalkills;
			    		addChatMessage("§8Final Kills Till " + nextfkdr + " FKDR: §f" + tillnextfkdr);
			    		addChatDivider("§9");
			    		
			    	}
			    	

			    } else {

			        // If we're here, it means that Hypixel has no info on this player
			    	addChatMessage("That player was not found");
			    }
			});
		} catch (Exception e) {
			addChatMessage("Error looking up name.");
			e.printStackTrace();
		}
			
		
	}

	
	
	private static void addChatDivider() {
		Creative.addChatMessage("\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
		
	}

	private static void addChatMessage(String message) {
		Creative.addChatMessage("§1[§9Stats§1] §f" + message);
	}
	
	
	private static void addChatDivider(String prefix) {
		Creative.addChatMessage(prefix + "\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
		
	}
	
	
	
	
}