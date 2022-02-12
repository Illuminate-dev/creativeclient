package creativeclient.cosmetics.cape.impl;

import creativeclient.cosmetics.cape.CapeBase;
import creativeclient.util.CapeUtils;
import net.minecraft.util.ResourceLocation;

public class CapeSword extends CapeBase {

	public CapeSword() {
		super("Sword Cape", new ResourceLocation("creativeclient/capes/cape_sword.png"));
	}
	
	@Override
	public boolean isEnabled() {
		if(CapeUtils.getCape() instanceof CapeSword) {
			return true;
		}
		return false;
	}

}
