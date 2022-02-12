package creativeclient.mod.impl.hitboxes;

import creativeclient.mod.Category;
import creativeclient.mod.Mod;
import net.minecraft.client.Minecraft;

public class HitBoxMod extends Mod {

	public HitBoxMod() {
		super("Hitboxes", "Allows you to change hitbox colors", Category.MISC);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEnable() {
		Minecraft.getMinecraft().getRenderManager().setDebugBoundingBox(true);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		Minecraft.getMinecraft().getRenderManager().setDebugBoundingBox(false);
		super.onDisable();
	}

}
