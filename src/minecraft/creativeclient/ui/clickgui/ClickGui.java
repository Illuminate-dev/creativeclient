package creativeclient.ui.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import creativeclient.Creative;
import creativeclient.ui.clickgui.comp.ModButton;
import creativeclient.util.AnimatedResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class ClickGui extends GuiScreen{
	
	ArrayList<ModButton> modButtons = new ArrayList<>();
	
	@Override
    public void onGuiClosed() {
        //Disable Minecrafts blur shader
		Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
		super.onGuiClosed();
		if(Creative.INSTANCE.modManager.motionBlur.isEnabled()) {
			Creative.INSTANCE.modManager.motionBlur.setEnabled(false);
			Creative.INSTANCE.modManager.motionBlur.setEnabled(true);
		}
        
	}
	
	
	@Override
	public void initGui() {
		ScaledResolution sr = new ScaledResolution(mc);
		super.initGui();
		Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
		
		this.modButtons.add(new ModButton(sr.getScaledWidth() / 6 + (sr.getScaledWidth() / 45), sr.getScaledHeight() / 8 + (sr.getScaledHeight() / 25), 55, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.targetHud));
		int xplus = mc.fontRendererObj.getStringWidth("TargetHUD");
		int yplus = 0;
		
		System.out.println(sr.getScaledWidth());
		xplus += mc.fontRendererObj.getStringWidth("FPS") + sr.getScaledWidth() / 26;
		this.modButtons.add(new ModButton((int) (sr.getScaledWidth() / 6 + xplus), sr.getScaledHeight() / 8 + (sr.getScaledHeight() / 25) + yplus, 21, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.fps));
		xplus += mc.fontRendererObj.getStringWidth("1.7 Animations") - 23;
		this.modButtons.add(new ModButton((int) (sr.getScaledWidth() / 6 + xplus), sr.getScaledHeight() / 8 + (sr.getScaledHeight() / 25)+ yplus, 71, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.animationsMod));
		xplus += mc.fontRendererObj.getStringWidth("Keystrokes") + sr.getScaledWidth() / 17;
		if(xplus + mc.fontRendererObj.getStringWidth("Keystrokes") + 200 > sr.getScaledWidth() - (sr.getScaledWidth() / 6)) {
			yplus += 20;
		}
			
		this.modButtons.add(new ModButton((int) (sr.getScaledWidth() / 6 + xplus), sr.getScaledHeight() / 8 + (sr.getScaledHeight() / 25)+ yplus, 59, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.keystrokes));
		if(xplus + mc.fontRendererObj.getStringWidth("Fullbright") + 200 > sr.getScaledWidth() - (sr.getScaledWidth() / 6)) {
			yplus += 20;
		}
		this.modButtons.add(new ModButton(441, sr.getScaledHeight() / 8 + (sr.getScaledHeight() / 25) + yplus, 52, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.fullbright));
		this.modButtons.add(new ModButton(sr.getScaledWidth() / 6 + (sr.getScaledWidth() / 45), 80, 51, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.tntTimer));
		this.modButtons.add(new ModButton(211, 80, 62, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.zoom));
		this.modButtons.add(new ModButton(298, 80, 69, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.armorStatus));
		this.modButtons.add(new ModButton(392, 80, 64, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.coords));
		this.modButtons.add(new ModButton(481, 80, 23, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.cps));
		this.modButtons.add(new ModButton(sr.getScaledWidth() / 6 + (sr.getScaledWidth() / 45), 100, 73, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.overlay));
		this.modButtons.add(new ModButton(233, 100, 60, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.dirMod));
		this.modButtons.add(new ModButton(318, 100, 24, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.timeMod));
		this.modButtons.add(new ModButton(366, 100, 100, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.chat));
		this.modButtons.add(new ModButton(sr.getScaledWidth() / 6 + (sr.getScaledWidth() / 45), 120, 53, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.motionBlur));
		this.modButtons.add(new ModButton(212, 120, 50, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.hitbox));
		this.modButtons.add(new ModButton(286, 120, 53, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.itemModel));
		this.modButtons.add(new ModButton(363, 120, 50, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.potionHud));
		this.modButtons.add(new ModButton(437, 120, 20, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.bps));
		this.modButtons.add(new ModButton(sr.getScaledWidth() / 6 + (sr.getScaledWidth() / 45), 140, 23, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.hudManager.ping));
		this.modButtons.add(new ModButton(183, 140, 39, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.autoGG));
		this.modButtons.add(new ModButton(230, 140 , 60, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.deobfnames));
		this.modButtons.add(new ModButton(280, 140, 60, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.itemPhysics));
		this.modButtons.add(new ModButton(400, 140 , 60, mc.fontRendererObj.FONT_HEIGHT + 5, Creative.INSTANCE.modManager.perspective));
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(mc);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		
		
		
		Gui.drawRect(sr.getScaledWidth() / 6, sr.getScaledHeight() / 8, sr.getScaledWidth() - (sr.getScaledWidth() / 6), sr.getScaledHeight() - (sr.getScaledHeight() / 8), new Color(0, 0, 0, 170).getRGB());
		
		for(ModButton m : modButtons) {
			m.draw();
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

		for(ModButton m : modButtons) {
			m.onClick(mouseX, mouseY, mouseButton);
		}
		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}
