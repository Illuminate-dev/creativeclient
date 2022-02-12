package creativeclient.hud.mod.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import creativeclient.hud.ScreenPosition;
import creativeclient.hud.mod.HudMod;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class CPSMod extends HudMod {
	
	private List<Long> clicks = new ArrayList<Long>();
	private List<Long> rclicks = new ArrayList<Long>();
	private boolean wasPressed;
	private long lastPressed;
	private long lastPressed2;
    private boolean wasPressed2;

	public CPSMod() {
		super("CPS", 290, 290);
		
	}
	
	@Override
	public void draw() {
		
		ScaledResolution sr = new ScaledResolution(mc);
		final boolean lpressed = Mouse.isButtonDown(0);
		final boolean rpressed = Mouse.isButtonDown(1);
		
		if(lpressed != this.wasPressed) {
			this.lastPressed = System.currentTimeMillis();
			this.wasPressed = lpressed;
			if(lpressed) {
				this.clicks.add(this.lastPressed);
			}
		}
		
		if(rpressed != this.wasPressed2)
	    {
	        this.lastPressed2 = System.currentTimeMillis();
	        this.wasPressed2 = rpressed;
	        if(rpressed)
	        {
	            this.rclicks.add(this.lastPressed2);
	        }
	    }
		
		fr.drawStringWithShadow("§6" + getLCPS() + " §f: §6" + getRCPS(), this.getX() + 2, this.getY(), -1);
		
		super.draw();
		
	}
	
	private int getRCPS()
    {
        final long time2 = System.currentTimeMillis();
        this.rclicks.removeIf(aLong2 -> aLong2 + 1000 < time2);
        return this.rclicks.size();
    }

	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		
		fr.drawStringWithShadow("§6" + getLCPS() + " §f: " + "§6" + getRCPS(), this.getX() +2, this.getY(), -1);
		
		super.renderDummy(mouseX, mouseY);
		
	}
	
	@Override
	public int getWidth() {
		try {
			return fr.getStringWidth(getLCPS() + " : " + getRCPS());
		} catch (NullPointerException e) {
			return fr.getStringWidth("00 : 00");
		}
		
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}
	
	private int getLCPS() {
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		return this.clicks.size();
	}

}
