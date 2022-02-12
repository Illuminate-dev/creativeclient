package creativeclient.ui.capegui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import creativeclient.Creative;
import creativeclient.ui.capegui.comp.CapeButton;
import creativeclient.ui.clickgui.comp.ModButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class CapeGui extends GuiScreen{
	
	public ArrayList<CapeButton> capeButtons = new ArrayList<>();
	
	@Override
    public void onGuiClosed() {
        //Disable Minecrafts blur shader
        Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
        super.onGuiClosed();
	}
	
	@Override
	public void initGui() {
		Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
		super.initGui();
		this.capeButtons.add(new CapeButton(205, 80, 62, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.capeManager.capeSword));
		this.capeButtons.add(new CapeButton(269, 80, 52, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.capeManager.capeFox));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		Gui.drawRect(200, 70, sr.getScaledWidth() - 200, sr.getScaledHeight() - 70, new Color(0, 0, 0, 170).getRGB());
		
		
		for(CapeButton c : capeButtons) {
			c.draw();
		}
		
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

		for(CapeButton m : capeButtons) {
			m.onClick(mouseX, mouseY, mouseButton);
		}
		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	
}
