package creativeclient.mod.impl;

import creativeclient.Creative;
import creativeclient.event.EventTarget;
import creativeclient.event.impl.EventUpdate;
import creativeclient.mod.Category;
import creativeclient.mod.Mod;
import net.minecraft.potion.Potion;

public class ToggleSprint extends Mod {

	public ToggleSprint() {
		super("ToggleSprint", "Toggle your sprinting status!", Category.MISC);
	}

	@EventTarget
	public void onUpdate(EventUpdate event) {
		if (this.isEnabled() && !mc.thePlayer.isBlocking() && !mc.thePlayer.isSneaking()
				&& !mc.thePlayer.isCollidedHorizontally && mc.thePlayer.moveForward > 0
				&& (mc.thePlayer.motionX != 0 && mc.thePlayer.motionY != 0) 
				&& !mc.thePlayer.isPotionActive(Potion.blindness)
				&& !mc.gameSettings.keyBindBack.isKeyDown()) {

			mc.thePlayer.setSprinting(true);
			
		}

	}

	@Override
	public void onDisable() {
		super.onDisable();

		mc.thePlayer.setSprinting(false);
	}

}
