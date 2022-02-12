package creativeclient.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.hypixel.api.HypixelAPI;



public class MinecraftAPI {
	
	
	
	public static String getUUID(String username) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
        URLConnection connection = url.openConnection();
        
 
//         `HttpURLConnection` connection = (HttpURLConnection) url.openConnection();
//         connection.setRequestMethod("GET");
 
        try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())))
        {
        	
            String line;
            
            while ((line = in.readLine()) != null) {
            	
            	
        		Gson g = new Gson();
        		JsonObject jsonobj = g.fromJson(line, JsonObject.class).getAsJsonObject();
        		
        		String uuid = insertDashUUID(jsonobj.get("id").getAsString());
        		
        		return uuid;
            }
        }
        
        return null;
    }

	public static String insertDashUUID(String uuid) {
	    StringBuilder sb = new StringBuilder(uuid);
	    sb.insert(8, "-");
	    sb = new StringBuilder(sb.toString());
	    sb.insert(13, "-");
	    sb = new StringBuilder(sb.toString());
	    sb.insert(18, "-");
	    sb = new StringBuilder(sb.toString());
	    sb.insert(23, "-");

	    return sb.toString();
	  }
	

}
