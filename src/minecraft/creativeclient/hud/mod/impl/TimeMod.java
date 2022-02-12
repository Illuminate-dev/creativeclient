package creativeclient.hud.mod.impl;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import creativeclient.hud.mod.HudMod;
import net.minecraft.client.gui.Gui;

public class TimeMod extends HudMod {
	
	public TimeMod() {
		super("Time", 100, 100);
		
	}
		
	@Override
	public void draw() {
		
		String pattern = "hh:mm:ss a z";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String time = simpleDateFormat.format(new Date());
		fr.drawString("Time: " + time, this.getX() + 1, this.getY() + 1, -1);
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		String pattern = "hh:mm:ss a z";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String time = simpleDateFormat.format(new Date());
		fr.drawString("Time: " + time, this.getX() + 1, this.getY() + 1, -1);
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("Time: AA:AA:AA AA AAA");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}

}
