package creativeclient.mod.impl.itemmodel;

import java.util.ArrayList;

import creativeclient.mod.GuiButtonManager;
import creativeclient.settings.NumberSetting;
import creativeclient.settings.Setting;
import creativeclient.util.config.Config;

public class ItemModelManager extends GuiButtonManager {
	
	public ArrayList<Setting> settings;
	
	public NumberSetting itemX;
	public NumberSetting itemY;
	public NumberSetting size;
	
	private Config config;
	
	public ItemModelManager() {
		settings = new ArrayList<Setting>();
		
		this.config = new Config();
		config.loadModConfig();
		
		try {
			settings.add(itemX = new NumberSetting("Item X", (int) config.config.get("itemmodel x value"), 1, 100, 1));
			settings.add(itemY = new NumberSetting("Item Y", (int) config.config.get("itemmodel y value"), 1, 100, 1));
			settings.add(size = new NumberSetting("Size", (int) config.config.get("itemmodel size value"), 1, 100, 1));
		} catch (NullPointerException e) {
			settings.add(itemX = new NumberSetting("Item X", 56, 1, 100, 1));
			settings.add(itemY = new NumberSetting("Item Y", 52, 1, 100, 1));
			settings.add(size = new NumberSetting("Size", 40, 20, 100, 1));
		}
		
	}

}
