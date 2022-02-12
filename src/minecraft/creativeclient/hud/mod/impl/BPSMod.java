package creativeclient.hud.mod.impl;

import java.awt.Color;


import creativeclient.hud.mod.HudMod;
import net.minecraft.client.Minecraft;

public class BPSMod extends HudMod {

	public BPSMod() {
		super("BPS", 270, 270);
		// TODO Auto-generated constructor stub
	}

	float bps;


	@Override
    public void draw() {
		float rat = Minecraft.getMinecraft().timer.ticksPerSecond * Minecraft.getMinecraft().timer.timerSpeed;
        this.bps = (float) (mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.posY, mc.thePlayer.lastTickPosZ) * rat);
        this.bps = Math.round(bps * 100);
        this.bps /= 100;
        fr.drawStringWithShadow(bps + " BPS", getX(), getY(), -1);
        super.draw();
    }
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		float rat = Minecraft.getMinecraft().timer.ticksPerSecond * Minecraft.getMinecraft().timer.timerSpeed;
        this.bps = (float) (mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.posY, mc.thePlayer.lastTickPosZ) * rat);
        this.bps = Math.round(bps * 100);
        this.bps /= 100;
        fr.drawStringWithShadow(bps + " BPS", getX(), getY(), -1);
		super.renderDummy(mouseX, mouseY);
	}
	
    @Override
    public int getWidth() {
    	try {
    		return fr.getStringWidth(String.valueOf(bps) + " BPS");
    	} catch (NullPointerException e) {
    		
    		return 50;
    	}
        
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }
	

}
