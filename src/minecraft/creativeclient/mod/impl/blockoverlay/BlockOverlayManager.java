package creativeclient.mod.impl.blockoverlay;

import java.util.ArrayList;

import creativeclient.event.EventTarget;
import creativeclient.event.impl.ClientTickEvent;
import creativeclient.mod.GuiButtonManager;
import creativeclient.settings.BooleanSetting;
import creativeclient.settings.NumberSetting;
import creativeclient.settings.Setting;
import creativeclient.ui.modgui.NumberButton;
import creativeclient.util.config.Config;

public class BlockOverlayManager extends GuiButtonManager {
	
	public ArrayList<Setting> settings;

	public NumberSetting overlayred;
	public NumberSetting overlayblue;
	public NumberSetting overlaygreen;
	public NumberSetting overlayalpha;
	public BooleanSetting chroma;
	
	public NumberSetting outlinered;
	public NumberSetting outlineblue;
	public NumberSetting outlinegreen;
	public NumberSetting outlinealpha;
	public BooleanSetting outline;

	private Config config;
	
	public BlockOverlayManager() {
		settings = new ArrayList<Setting>();
		this.config = new Config();
		config.loadModConfig();
		
		try {
			settings.add(overlayred = new NumberSetting("Overlay Red", (int) config.config.get("blockoverlay red value"), 0, 255, 1));
			settings.add(overlayblue = new NumberSetting("Overlay Blue", (int) config.config.get("blockoverlay blue value"), 0, 255, 1));
			settings.add(overlaygreen = new NumberSetting("Overlay Green", (int) config.config.get("blockoverlay green value"), 0, 255, 1));
			settings.add(overlayalpha = new NumberSetting("Overlay Alpha", (int) config.config.get("blockoverlay alpha value"), 0, 255, 1));
			settings.add(chroma = new BooleanSetting("Chroma", (boolean) config.config.get("blockoverlay chroma enabled")));
			settings.add(outlinered = new NumberSetting("Outline Red", (int) config.config.get("blockoverlay red value"), 0, 255, 1));
			settings.add(outlineblue = new NumberSetting("Outline Blue", (int) config.config.get("blockoverlay outlineblue value"), 0, 255, 1));
			settings.add(outlinegreen = new NumberSetting("Outline Green", (int) config.config.get("blockoverlay outlinegreen value"), 0, 255, 1));
			settings.add(outlinealpha = new NumberSetting("Outline Alpha", (int) config.config.get("blockoverlay outlinealpha value"), 0, 255, 1));
			settings.add(outline = new BooleanSetting("Outline Enabled", (boolean) config.config.get("blockoverlay outline enabled")));
		} catch (NullPointerException e) {
		
			settings.add(overlayred = new NumberSetting("Overlay Red", 0, 0, 255, 1));
			settings.add(overlayblue = new NumberSetting("Overlay Blue", 0, 0, 255, 1));
			settings.add(overlaygreen = new NumberSetting("OverlayGreen", 0, 0, 255, 1));
			settings.add(overlayalpha = new NumberSetting("OverlayAlpha", 130, 0, 255, 1));
			settings.add(chroma = new BooleanSetting("Chroma", false));
			settings.add(outlinered = new NumberSetting("Outline Red", 0, 0, 255, 1));
			settings.add(outlineblue = new NumberSetting("Outline Blue", 0, 0, 255, 1));
			settings.add(outlinegreen = new NumberSetting("Outline Green", 0, 0, 255, 1));
			settings.add(outlinealpha = new NumberSetting("Outline Alpha", 0, 0, 255, 1));
			settings.add(outline = new BooleanSetting("Outline Enabled", false));
		}
	}
	
	
}
