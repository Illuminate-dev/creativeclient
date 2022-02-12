package creativeclient.cosmetics.cape.impl;

import creativeclient.cosmetics.cape.CapeBase;
import creativeclient.util.CapeUtils;
import net.minecraft.util.ResourceLocation;

public class CapeFox extends CapeBase {

	public CapeFox() {
		super("Fox Cape", new ResourceLocation("creativeclient/capes/cape_fox.png"));
	}
	
	@Override
	public boolean isEnabled() {
		if(CapeUtils.getCape() instanceof CapeFox) {
			return true;
		}
		return false;
	}

}
