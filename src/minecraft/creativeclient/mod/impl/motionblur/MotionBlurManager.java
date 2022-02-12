package creativeclient.mod.impl.motionblur;

import java.util.ArrayList;

import creativeclient.event.EventTarget;
import creativeclient.event.impl.ClientTickEvent;
import creativeclient.mod.GuiButtonManager;
import creativeclient.settings.BooleanSetting;
import creativeclient.settings.NumberSetting;
import creativeclient.settings.Setting;
import creativeclient.ui.modgui.NumberButton;
import creativeclient.util.config.Config;

public class MotionBlurManager extends GuiButtonManager {
	
	public ArrayList<Setting> settings;

	public NumberSetting blur;
	

	private Config config;
	
	public MotionBlurManager() {
		settings = new ArrayList<Setting>();
		this.config = new Config();
		config.loadModConfig();
		
		try {
			settings.add(blur = new NumberSetting("blur amount", (int) config.config.get("motionblur amount value"), 1, 7, 1));
		} catch (NullPointerException e) {
		
			settings.add(blur = new NumberSetting("blur amount", 1, 1, 7, 1));
		}
	}
	
	
}
