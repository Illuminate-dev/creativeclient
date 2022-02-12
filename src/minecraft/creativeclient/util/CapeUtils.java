package creativeclient.util;

import creativeclient.Creative;
import creativeclient.cosmetics.cape.CapeBase;
import creativeclient.cosmetics.cape.impl.CapeSword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;

public class CapeUtils {

	public static CapeBase selectedCape = null;

	public static CapeBase getCape() {
		return selectedCape;
	}

	public static boolean shouldRenderCape(EntityLivingBase entity) {
		if(entity.getName() == Minecraft.getMinecraft().thePlayer.getName()) {
			return getCape() != null;
		} else {
			return false;
		}
		

	}

	public static void setCape(String name) {
		for(CapeBase cape : Creative.INSTANCE.capeManager.capes) {
			if(name.equalsIgnoreCase(cape.name)) {
				selectedCape = cape;
			}
		}
		
	}
	
	public static void setCape(CapeBase cape) {
		selectedCape = cape;
		
	}

	public static void resetCape() {
		selectedCape = null;
	}

}
