package creativeclient.hud.mod.impl;

import java.awt.Color;


import creativeclient.hud.mod.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class PingMod extends HudMod {

	public PingMod() {
		super("Ping", 270, 270);
		// TODO Auto-generated constructor stub
	}


	@Override
    public void draw() {
		
        if(!Minecraft.getMinecraft().isSingleplayer()) {
            fr.drawStringWithShadow("[" + mc.getCurrentServerData().pingToServer + " ms]", getX(), getY(), -1);
        } else {
            fr.drawStringWithShadow("[0 ms]", getX(), getY(), -1);
        }
        super.draw();
    }
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		if(!MinecraftServer.getServer().isSinglePlayer()) {
            fr.drawStringWithShadow("[" + mc.getCurrentServerData().pingToServer + " ms]", getX(), getY(), -1);
        } else {
            fr.drawStringWithShadow("[0 ms]", getX(), getY(), -1);
        }
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
    public int getWidth() {
        return fr.getStringWidth("[00 ms]");
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }
	

}
