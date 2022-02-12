package creativeclient.hud;

import java.io.IOException;

import creativeclient.Creative;
import creativeclient.hud.mod.HudMod;
import creativeclient.ui.capegui.CapeGui;
import creativeclient.ui.clickgui.ClickGui;
import creativeclient.ui.clickgui.GuiTestList;
import creativeclient.ui.clickgui.ScrollListTest;
import creativeclient.util.AnimatedButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class HudConfigScreen extends GuiScreen{
	
	@Override
    public void onGuiClosed() {
        //Disable Minecrafts blur shader
		Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
		super.onGuiClosed();
		if(Creative.INSTANCE.modManager.motionBlur.isEnabled()) {
			Creative.INSTANCE.modManager.motionBlur.setEnabled(false);
			Creative.INSTANCE.modManager.motionBlur.setEnabled(true);
		}
        super.onGuiClosed();
	}
	
	
	@Override
	public void initGui() {
		
		super.initGui();
		Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
		ScaledResolution sr = new ScaledResolution(mc);
		this.buttonList.add(new GuiButton(6340, sr.getScaledWidth() / 2 - 50, sr.getScaledHeight() /2 - 10, 100, 20, "ClickGUI"));
		this.buttonList.add(new GuiButton(60, sr.getScaledWidth() / 2 - 50, sr.getScaledHeight() /2 + 15, 100, 20, "Cosmetics"));
		this.buttonList.add(new GuiButton(1570, sr.getScaledWidth() / 2 - 50, sr.getScaledHeight() / 2 + 40, 100, 20, "ClickGUI2"));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		this.drawDefaultBackground();
		
		for(HudMod m : Creative.INSTANCE.hudManager.hudMods) {
			if(m.isEnabled()) {
				m.renderDummy(mouseX, mouseY);
			}
			
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		
		super.actionPerformed(button);
		switch(button.id) {
			case 6340:
				mc.displayGuiScreen(new ClickGui());
				break;
				
			case 60:
				mc.displayGuiScreen(new CapeGui());
				break;
			case 1570:
				mc.displayGuiScreen(new GuiTestList());
				break;
		}
	}

}
