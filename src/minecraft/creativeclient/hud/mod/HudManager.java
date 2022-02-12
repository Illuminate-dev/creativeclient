package creativeclient.hud.mod;

import java.util.ArrayList;

import creativeclient.hud.mod.impl.*;
import net.minecraft.client.Minecraft;


public class HudManager {

	public ArrayList<HudMod> hudMods = new ArrayList<>();
	
	
	public FPSMod fps;
	public TargetHUD targetHud;
	public Keystrokes keystrokes;
	public ArmorStatus armorStatus;
	public Coordinates coords;
	public CPSMod cps;
	public DirectionMod dirMod;
	public TimeMod timeMod;
	public PotionHud potionHud;
	public BPSMod bps;
	public PingMod ping;
	
	
	public HudManager() {
		
		
		hudMods.add(fps = new FPSMod());
		hudMods.add(targetHud = new TargetHUD());
		hudMods.add(keystrokes = new Keystrokes());
		hudMods.add(armorStatus = new ArmorStatus());
		hudMods.add(coords = new Coordinates());
		hudMods.add(cps = new CPSMod());
		hudMods.add(dirMod = new DirectionMod());
		hudMods.add(timeMod = new TimeMod());
		hudMods.add(potionHud = new PotionHud());
		hudMods.add(bps = new BPSMod());
		hudMods.add(ping = new PingMod());
		
	}
	
	public void renderMods() {
		
		for(HudMod m : hudMods) {
			if(m.isEnabled() && !Minecraft.getMinecraft().gameSettings.showDebugInfo) {
				
				m.draw();
			}
			
		}
	}
	
}
