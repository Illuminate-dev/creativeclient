package creativeclient.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class StringUtils {
	
	public static String firstLetterUp(String input) {
		return input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
	}
	
	public static String getFormattedOnline(JsonObject apiData) {
		if(getOnline(apiData)) {
			return "§a[ONLINE]";
		} else {
			return "§c[OFFLINE]";
		}
	}
	
	public static boolean getOnline(JsonObject apiData) {
		long lastlogin = apiData.get("lastLogin").getAsLong();
		long lastlogout = apiData.get("lastLogout").getAsLong();
		return lastlogin > lastlogout;
	}
	
	public static String getFormattedDuelsTitle(JsonObject apiData) {
		JsonElement testDuels = apiData.get("stats");
		
		if(testDuels == null) {
			return "None";
		} else {
			testDuels = testDuels.getAsJsonObject().get("Duels");
		}
		if(testDuels == null) {
			return "None";
		}	
		JsonObject duelsData = testDuels.getAsJsonObject();
		JsonElement selectedDuelsTitle1 = duelsData.get("active_cosmetictitle");
		
		String title = "";
		if(selectedDuelsTitle1 == null) {
			return "None";
		}
		String selectedDuelsTitle = selectedDuelsTitle1.getAsString();
		System.out.println(selectedDuelsTitle);
		String[] duelsTitle = selectedDuelsTitle.split("_");
		Map<String, String> prefixMap = getDuelsPrefixMap();
		
		if (duelsTitle[0].equals("custom")) {
			
			return duelsData.get("equipped_custom_titles").getAsString();
		}
		System.out.println(duelsTitle[1]);
		if(duelsTitle[1].equals("all")) {
			
			String keyword = "all_modes_" + duelsTitle[0] + "_title_prestige";
			int prestige = duelsData.get(keyword).getAsInt();
			title += prefixMap.get(StringUtils.firstLetterUp(duelsTitle[0]).toString());
			title += "[";
			title += StringUtils.firstLetterUp(duelsTitle[0]);
			title += " " + IntegerToRomanNumeral(prestige);
			title += "]";
		} else {
			
			String word = duelsTitle[1];
			String word1 = duelsTitle[0];
			if(duelsTitle[1].equals("uhc")) {
				word = "UHC";
			}
			String keyword = duelsTitle[1] + "_" + duelsTitle[0] + "_title_prestige";
			if(duelsTitle[1].equals("silver")) {
				word = duelsTitle[0];
				word1 = "Iron";
				keyword = duelsTitle[0] + "_iron_title_prestige";
			}
			int prestige = duelsData.get(keyword).getAsInt();
			
			title += prefixMap.get(StringUtils.firstLetterUp(word1).toString());
			title += "[" + StringUtils.firstLetterUp(word) + " ";
			title += StringUtils.firstLetterUp(word1);
			if(!IntegerToRomanNumeral(prestige).equals("I")) {
				title += " " + IntegerToRomanNumeral(prestige);
			}
			
			title += "]";
		}
		
		return title;
	}
	
	public static Map getDuelsPrefixMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Rookie", "§8");
		map.put("Iron", "§f");
		map.put("Gold", "§6");
		map.put("Diamond", "§3");
		map.put("Master", "§2");
		map.put("Legend", "§4");
		map.put("Grandmaster", "§e");
		map.put("Godlike", "§5");
		return map;
	}
	
	public static String getFormattedName(JsonObject apiData) {
		String prefix = "";
    	String newPackageRank = getFieldOrNA("newPackageRank", apiData);
    	if(newPackageRank.equals("MVP_PLUS")) {
    		boolean mvp_plus_plus = getFieldOrNA("monthlyPackageRank", apiData).equals("SUPERSTAR");
    		String monthlyRankColor = getFieldOrNA("monthlyRankColor", apiData);
    		System.out.println(getFieldOrNA("monthlyRankColor", apiData));
    		if((monthlyRankColor.equals("GOLD") || monthlyRankColor.equals("N/A")) && mvp_plus_plus) {
    			prefix += "§6[MVP";
    		} else {
    			prefix += "§b[MVP";
    		}
    		
    		String x = "+";
    		System.out.println(getFieldOrNA("rankPlusColor", apiData));
    		if(mvp_plus_plus) {
    			x = "++";
    		}
    		String rankPlusColor = getFieldOrNA("rankPlusColor", apiData);
    		
    		switch(rankPlusColor) {
    		case "BLUE":
    			prefix += "§9" + x;
    			break;
    		case "GOLD":
    			prefix += "§6" + x;
    			break;
    		case "BLACK":
    			prefix += "§0" + x;
    			break;
    		case "DARK_GRAY":
    			prefix += "§8" + x;
    			break;
    		case "DARK_GREEN":
    			prefix += "§2" + x;
    			break;
    		case "GREEN":
    			prefix += "§a" + x;
    			break;
    		case "LIGHT_PURPLE":
    			prefix += "§d" + x;
    			break;
    		case "WHITE":
    			prefix += "§f" + x;
    			break;
    		case "YELLOW":
    			prefix += "§e" + x;
    			break;
    		case "DARK_RED":
    			prefix += "§4" + x;
    			break;
    		case "DARK_AQUA":
    			prefix += "§3" + x;
    			break;
    		case "DARK_BLUE":
    			prefix += "§1" + x;
    			break;
    		default:
    			prefix += "§c" + x;
    			break;
    		}
    		if((monthlyRankColor.equals("GOLD") || monthlyRankColor.equals("N/A")) && mvp_plus_plus) {
    			prefix += "§6] ";
    		}else {
    			prefix += "§b] ";
    		}
    		
    	} else if (newPackageRank.equals("MVP")) {
    		prefix += "§b[MVP] ";
    		
    	} else if(newPackageRank.equals("VIP_PLUS")) {
    		prefix += "§a[VIP§6+§a] ";
    	} else if(newPackageRank.equals("VIP")) {
    		prefix += "§a[VIP] ";
    	} else {
    		prefix += "§7";
    	}
    	
    	return prefix + getFieldOrNA("displayname", apiData);
    	
	}
	
	public static String getFormattedBedwarsLevel(int level) {
		if(level < 100) {
			return "§7[" + String.valueOf(level) + "\u272b]";
		} else if(level < 200) {
			return "§f[" + String.valueOf(level) + "\u272b]";
		} else if(level < 300) {
			return "§6[" + String.valueOf(level) + "\u272b]";
		} else if(level < 400) {
			return "§b[" + String.valueOf(level) + "\u272b]";
		} else if(level < 500) {
			return "§2[" + String.valueOf(level) + "\u272b]";
		} else if(level < 600) {
			return "§3[" + String.valueOf(level) + "\u272b]";
		} else if(level < 700) {
			return "§4[" + String.valueOf(level) + "\u272b]";
		} else if(level < 800) {
			return "§d[" + String.valueOf(level) + "\u272b]";
		} else if(level < 900) {
			return "§1[" + String.valueOf(level) + "\u272b]";
		} else if(level < 1000) { 
			return "§5[" + String.valueOf(level) + "\u272b]";
		} else if(level < 1100) {
			String[] list = String.valueOf(level).split("");
			return "§c[§6" + list[0] + "§e" + list[1] + "§a" + list[2] + "§b" + list[3] + "§d\u272b§5]";
		} else if(level < 1200) {
			return "§7[§f" + String.valueOf(level) + "§7\u272a]";
		} else if(level < 1300) {
			return "§7[§e" + String.valueOf(level) + "§6\u272a§7]";
		} else if(level < 1400) {
			return "§7[§b" + String.valueOf(level) + "§3\u272a§7]";
		} else if(level < 1500) {
			return "§7[§a" + String.valueOf(level) + "§2\u272a§7]";
		} else if(level < 1600) {
			return "§7[§3" + String.valueOf(level) + "§9\u272a§7]";
		} else if(level < 1700) {
			return "§7[§c" + String.valueOf(level) + "§4\u272a§7]";
		} else if(level < 1800) {
			return "§7[§d" + String.valueOf(level) + "§5\u272a§7]";
		} else if(level < 1900) {
			return "§7[§9" + String.valueOf(level) + "§1\u272a§7]";
		} else if(level < 2000) {
			return "§7[§5" + String.valueOf(level) + "§8\u272a§7";
		} else if(level < 2100) {
			String[] list = String.valueOf(level).split("");
			return "§8[§7" + list[0] + "§f" + list[1] + list[2] + "§7" + list[3] + "\u272a§8]";
		} else if(level < 2200) {
			String[] list = String.valueOf(level).split("");
			return "§f[" + list[0] + "§e" + list[1] + list[2] + "§6" + list[3] + "\u269d]";
		} else if(level < 2300) {
			String[] list = String.valueOf(level).split("");
			return "§6[" + list[0] + "§f" + list[1] + list[2] + "§b" + list[3] + "§3\u269d]";
		} else if(level < 2400) {
			String[] list = String.valueOf(level).split("");
			return "§5[" + list[0] + "§d" + list[1] + list[2] + "§6" + list[3] + "§e\u269d]";
		} else if(level < 2500) {
			String[] list = String.valueOf(level).split("");
			return "§b[" + list[0] + "§f" + list[1] + list[2] + "§7" + list[3] + "\u269d§8]";
		} else if(level < 2600) {
			String[] list = String.valueOf(level).split("");
			return "§f[" + list[0] + "§a" + list[1] + list[2] + "§2" + list[3] + "\u269d]";
		} else if(level < 2700) {
			String[] list = String.valueOf(level).split("");
			return "§4[" + list[0] + "§c" + list[1] + list[2] + "§d" + list[3] + "\u269d§5]";
		} else if(level < 2800) {
			String[] list = String.valueOf(level).split("");
			return "§e[" + list[0] + "§f" + list[1] + list[2] + "§8" + list[3] + "\u269d]";
		} else if(level < 2900) {
			String[] list = String.valueOf(level).split("");
			return "§a[" + list[0] + "§2" + list[1] + list[2] + "§6" + list[3] + "\u269d§e]";
		} else if(level < 3000) {
			String[] list = String.valueOf(level).split("");
			return "§b[" + list[0] + "§3" + list[1] + list[2] + "§9" + list[3] + "\u269d§1]";
		} else {
			String[] list = String.valueOf(level).split("");
			return "§e[" + list[0] + "§6" + list[1] + list[2] + "§c" + list[3] + "\u269d§4]";
		}
		
	}
	
	public static String getFieldOrNA(String field, JsonObject json) {
        JsonElement value = json.get(field);
        if (value != null) {
            // If the field was found, return its value
            return value.getAsString();
        } else {
            // Otherwise, return "N/A"
            return "N/A";
        }
    }
	
	public static String getFormattedBestGameDuels(JsonObject apiData) {
		JsonObject duelsData = apiData.get("stats").getAsJsonObject().get("Duels").getAsJsonObject();
		String bestgame = "";
		
		final int uhc_wins = getFieldOr0("uhc_duel_wins", duelsData) + getFieldOr0("uhc_doubles_wins", duelsData) + getFieldOr0("uhc_four_wins", duelsData) + getFieldOr0("uhc_meetup_wins", duelsData);
		final int sw_wins = getFieldOr0("sw_duel_wins", duelsData) + getFieldOr0("sw_doubles_wins", duelsData);
		final int mw_wins = getFieldOr0("mw_duel_wins", duelsData) + getFieldOr0("mw_doubles_wins", duelsData);
		final int sumo_wins = getFieldOr0("sumo_wins", duelsData);
		final int op_wins = getFieldOr0("op_duel_wins", duelsData) + getFieldOr0("op_doubles_wins", duelsData);
		final int bridge_wins = getFieldOr0("bridge_duel_wins", duelsData) + getFieldOr0("bridge_doubles_wins", duelsData) + getFieldOr0("bridge_3v3v3v3_wins", duelsData) + getFieldOr0("bridge_four_wins", duelsData);
		final int bow_wins = getFieldOr0("bow_duel_wins", duelsData);
		final int classic_wins = getFieldOr0("classic_duel_wins", duelsData);
		final int blitz_wins = getFieldOr0("blitz_duel_wins", duelsData);
		final int combo_wins = getFieldOr0("combo_duel_wins", duelsData);
		final int bowspleef_wins = getFieldOr0("bowspleef_duel_wins", duelsData);
		int maxWinGame = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(uhc_wins, bowspleef_wins), blitz_wins), combo_wins), sw_wins), mw_wins), sumo_wins), op_wins), bridge_wins), bow_wins), classic_wins);
		if(maxWinGame == uhc_wins) {
			if(duelsData.get("uhc_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("uhc_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("uhc_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("uhc_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("uhc_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("uhc_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("uhc_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("uhc_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[UHC]";
		} else if(maxWinGame == sw_wins) {
			if(duelsData.get("skywars_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("skywars_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("skywars_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("skywars_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("skywars_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("skywars_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("skywars_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("skywars_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[Skywars]";
		} else if(maxWinGame == mw_wins) {
			if(duelsData.get("mw_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("mw_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("mw_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("mw_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("mw_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("mw_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("mw_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("mw_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[MegaWalls]";
		} else if(maxWinGame == sumo_wins) {
			if(duelsData.get("sumo_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("sumo_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("sumo_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("sumo_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("sumo_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("sumo_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("sumo_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("sumo_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[Sumo]";
		} else if(maxWinGame == op_wins) {
			if(duelsData.get("op_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("op_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("op_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("op_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("op_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("op_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("op_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("op_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[OP]";
		} else if(maxWinGame == bridge_wins) {
			if(duelsData.get("bridge_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("bridge_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("bridge_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("bridge_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("bridge_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("bridge_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("bridge_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("bridge_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[Bridge]";
		} else if(maxWinGame == bow_wins) {
			if(duelsData.get("bow_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("bow_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("bow_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("bow_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("bow_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("bow_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("bow_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("bow_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[Bow]";
		}else if(maxWinGame == classic_wins) {
			if(duelsData.get("classic_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("classic_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("classic_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("classic_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("classic_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("classic_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("classic_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("classic_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[Classic]";
		}else if(maxWinGame == blitz_wins) {
			if(duelsData.get("blitz_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("blitz_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("blitz_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("blitz_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("blitz_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("blitz_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("blitz_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("blitz_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[Blitz]";
		}else if(maxWinGame == combo_wins) {
			if(duelsData.get("combo_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("combo_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("combo_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("combo_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("combo_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("combo_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("combo_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("combo_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[Combo]";
		}else if(maxWinGame == bowspleef_wins) {
			if(duelsData.get("bowspleef_godlike_title_prestige") != null) {
				bestgame += "§5";
			} else if(duelsData.get("bowspleef_grandmaster_title_prestige") != null) {
				bestgame += "§e";
			} else if(duelsData.get("bowspleef_legend_title_prestige") != null) {
				bestgame += "§4";
			} else if(duelsData.get("bowspleef_master_title_prestige") != null) {
				bestgame += "§2";
			} else if(duelsData.get("bowspleef_diamond_title_prestige") != null) {
				bestgame += "§3";
			} else if(duelsData.get("bowspleef_gold_title_prestige") != null) {
				bestgame += "§6";
			} else if(duelsData.get("bowspleef_iron_title_prestige") != null) {
				bestgame += "§f";
			} else if(duelsData.get("bowspleef_rookie_title_prestige") != null) {
				bestgame += "§7";
			}
			bestgame += "[Bow Spleef]";
		}
		return bestgame;
	}
	
	public static String IntegerToRomanNumeral(int input) {
	    if (input < 1 || input > 3999)
	        return "Invalid Roman Number Value";
	    String s = "";
	    while (input >= 1000) {
	        s += "M";
	        input -= 1000;        }
	    while (input >= 900) {
	        s += "CM";
	        input -= 900;
	    }
	    while (input >= 500) {
	        s += "D";
	        input -= 500;
	    }
	    while (input >= 400) {
	        s += "CD";
	        input -= 400;
	    }
	    while (input >= 100) {
	        s += "C";
	        input -= 100;
	    }
	    while (input >= 90) {
	        s += "XC";
	        input -= 90;
	    }
	    while (input >= 50) {
	        s += "L";
	        input -= 50;
	    }
	    while (input >= 40) {
	        s += "XL";
	        input -= 40;
	    }
	    while (input >= 10) {
	        s += "X";
	        input -= 10;
	    }
	    while (input >= 9) {
	        s += "IX";
	        input -= 9;
	    }
	    while (input >= 5) {
	        s += "V";
	        input -= 5;
	    }
	    while (input >= 4) {
	        s += "IV";
	        input -= 4;
	    }
	    while (input >= 1) {
	        s += "I";
	        input -= 1;
	    }    
	    return s;
	}
	
	public static String goodKDR(float kdr) {
		if(kdr >= 3) {
			return "§a" + kdr;
		} else if(kdr >= 2) {
			return "§2" + kdr;
		} else if(kdr < 0.5) {
			return "§4" + kdr;
		} else if(kdr < 1) {
			return "§c" + kdr;
		} else {
			return "§f" + kdr;
		}
	}
	
	public static int getFieldOr0(String key, JsonObject json) {
		JsonElement value = json.get(key);
		if(!(value == null)) {
			return value.getAsInt();
		} else {
			return 0;
		}
	}

}
