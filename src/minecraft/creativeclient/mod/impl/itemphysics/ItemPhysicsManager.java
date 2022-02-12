package creativeclient.mod.impl.itemphysics;

import java.util.ArrayList;

import creativeclient.mod.GuiButtonManager;
import creativeclient.settings.BooleanSetting;
import creativeclient.settings.NumberSetting;
import creativeclient.settings.Setting;
import creativeclient.util.config.Config;

public class ItemPhysicsManager extends GuiButtonManager {
	
	public ArrayList<Setting> settings;
	
	public BooleanSetting old;
	
	private Config config;
	
	public ItemPhysicsManager() {
		settings = new ArrayList<Setting>();
		
		this.config = new Config();
		config.loadModConfig();
		
		try {
			settings.add(old = new BooleanSetting("Old ItemPhysics", (boolean) config.config.get("itemphysics old value")));
			
		} catch (NullPointerException e) {
			settings.add(old = new BooleanSetting("Old ItemPhysics", false));
		}
		
	}

}
