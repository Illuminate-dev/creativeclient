package creativeclient.mod.impl.hitboxes;

import java.util.ArrayList;

import creativeclient.mod.GuiButtonManager;
import creativeclient.settings.BooleanSetting;
import creativeclient.settings.NumberSetting;
import creativeclient.settings.Setting;
import creativeclient.util.config.Config;

public class HitBoxManager extends GuiButtonManager {
	
	public ArrayList<Setting> settings;

	public NumberSetting red;
	public NumberSetting blue;
	public NumberSetting green;
	public NumberSetting alpha;
	public BooleanSetting eyeline;
	public BooleanSetting chroma;

	private Config config;
	
	public HitBoxManager() {
		settings = new ArrayList<Setting>();
		this.config = new Config();
		config.loadModConfig();
		
		try {
			settings.add(red = new NumberSetting("Red", (int) config.config.get("hitbox red value"), 0, 255, 1));
			settings.add(blue = new NumberSetting("Blue", (int) config.config.get("hitbox blue value"), 0, 255, 1));
			settings.add(green = new NumberSetting("Green", (int) config.config.get("hitbox green value"), 0, 255, 1));
			settings.add(eyeline = new BooleanSetting("Eye Line", (boolean) config.config.get("hitbox eyeline enabled")));
			settings.add(chroma = new BooleanSetting("Chroma", (boolean) config.config.get("hitbox chroma enabled")));
			
		} catch (NullPointerException e) {
		
			settings.add(red = new NumberSetting("Red", 0, 0, 255, 1));
			settings.add(blue = new NumberSetting("Blue", 0, 0, 255, 1));
			settings.add(green = new NumberSetting("Green", 0, 0, 255, 1));
			settings.add(eyeline = new BooleanSetting("Eye Line", false));
			settings.add(chroma = new BooleanSetting("Chroma", false));
			
		}
	}

}
