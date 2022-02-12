package creativeclient.mod.impl;

import creativeclient.event.EventTarget;
import creativeclient.event.impl.EventUpdate;
import creativeclient.mod.Category;
import creativeclient.mod.Mod;

public class Fullbright extends Mod {
	
	public float prevGammaSetting;

	public Fullbright() {
		super("Fullbright", "Changes the gamma level to 100", Category.MISC);
		
		this.prevGammaSetting = mc.gameSettings.gammaSetting;
		
		
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		if(mc.gameSettings.gammaSetting != 100) {
			this.setEnabled(false);
		}

	}
	
	@Override
	public void onDisable() {
		mc.gameSettings.gammaSetting = this.prevGammaSetting;
		super.onDisable();
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		mc.gameSettings.gammaSetting = 100;
		

	}
	

}
