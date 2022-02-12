package creativeclient.util.config;

import java.io.File;
import java.io.IOException;

import creativeclient.Creative;
import creativeclient.hud.mod.HudMod;
import creativeclient.mod.GuiButtonManager;
import creativeclient.mod.Mod;
import creativeclient.settings.Setting;
import creativeclient.settings.BooleanSetting;
import creativeclient.settings.ModeSetting;
import creativeclient.settings.NumberSetting;

public class Config {
	
	public File configFolder = new File("Creative");
	public File modsFolder = new File("Creative/Mods");
	
	public Configuration config, configToSave = ConfigurationAPI.newConfiguration(new File("Creative/Mods/ModConfiguration.quick"));
	
	public void saveModConfig() {
		 
		if(!configFolder.exists()) {
			configFolder.mkdirs();
		}
		
		if(!modsFolder.exists()) {
			modsFolder.mkdirs();
		}
		
		System.out.println("Saving Mod Config");
		
		for(HudMod m : Creative.INSTANCE.hudManager.hudMods) {
			configToSave.set(m.name.toLowerCase() + " x", m.getX());
			configToSave.set(m.name.toLowerCase() + " y", m.getY());
			configToSave.set(m.name.toLowerCase() + " enabled", m.isEnabled());
		}
		for(Mod m : Creative.INSTANCE.modManager.mods) {
			if(!m.name.equalsIgnoreCase("motionblur")) {
				configToSave.set(m.name.toLowerCase() + " enabled", m.isEnabled());
			} else {
				configToSave.set("motionblur enabled", m.isEnabled());
			}
			
		}
		
		configToSave.set("blockoverlay red value", Creative.INSTANCE.settingsManager.blockOverlayManager.overlayred.getValue());
		configToSave.set("blockoverlay blue value", Creative.INSTANCE.settingsManager.blockOverlayManager.overlayblue.getValue());
		configToSave.set("blockoverlay green value", Creative.INSTANCE.settingsManager.blockOverlayManager.overlaygreen.getValue());
		configToSave.set("blockoverlay alpha value", Creative.INSTANCE.settingsManager.blockOverlayManager.overlayalpha.getValue());
		configToSave.set("blockoverlay chroma enabled", Creative.INSTANCE.settingsManager.blockOverlayManager.chroma.isEnabled());
		configToSave.set("blockoverlay outlinered value", Creative.INSTANCE.settingsManager.blockOverlayManager.outlinered.getValue());
		configToSave.set("blockoverlay outlineblue value", Creative.INSTANCE.settingsManager.blockOverlayManager.outlineblue.getValue());
		configToSave.set("blockoverlay outlinegreen value", Creative.INSTANCE.settingsManager.blockOverlayManager.outlinegreen.getValue());
		configToSave.set("blockoverlay outlinealpha value", Creative.INSTANCE.settingsManager.blockOverlayManager.outlinealpha.getValue());
		configToSave.set("blockoverlay outline enabled", Creative.INSTANCE.settingsManager.blockOverlayManager.outline.isEnabled());
		configToSave.set("motionblur amount value", Creative.INSTANCE.settingsManager.motionBlurManager.blur.getValue());
		configToSave.set("hitbox red value", Creative.INSTANCE.settingsManager.hitboxManager.red.getValue());
		configToSave.set("hitbox blue value", Creative.INSTANCE.settingsManager.hitboxManager.blue.getValue());
		configToSave.set("hitbox green value", Creative.INSTANCE.settingsManager.hitboxManager.green.getValue());
		configToSave.set("itemmodel x value", Creative.INSTANCE.settingsManager.itemModelManager.itemX.getValue());
		configToSave.set("itemmodel y value", Creative.INSTANCE.settingsManager.itemModelManager.itemY.getValue());
		configToSave.set("itemmodel size value", Creative.INSTANCE.settingsManager.itemModelManager.size.getValue());
		
		
		try {
			configToSave.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void loadModConfig() {
		try {
			config = ConfigurationAPI.loadExistingConfiguration(new File("Creative/Mods/ModConfiguration.quick"));
																			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	

}
