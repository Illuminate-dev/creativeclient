package creativeclient.cosmetics.cape;

import creativeclient.util.CapeUtils;
import net.minecraft.util.ResourceLocation;

public class CapeBase {

	public String name;
	public ResourceLocation loc;

	public CapeBase(String name, ResourceLocation loc) {
		
		this.name = name;
		this.loc = loc;
	}

	public String getName() {
		return name;
	}

	public ResourceLocation getLoc() {
		return loc;
	}
	
	public boolean isEnabled() {
		return false;
	}

}
