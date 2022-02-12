package creativeclient.mod.impl.blockoverlay;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JColorChooser;

import creativeclient.Creative;
import creativeclient.settings.BooleanSetting;
import creativeclient.ui.capegui.CapeGui;
import creativeclient.ui.clickgui.ClickGui;
import creativeclient.ui.clickgui.comp.ModButton;
import creativeclient.ui.modgui.ModGui;
import creativeclient.ui.modgui.NumberButton;
import creativeclient.ui.modgui.ToggleButton;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class BlockOverlayGui extends ModGui {

	ArrayList<ToggleButton> toggleButtons1 = new ArrayList<>();
	ArrayList<NumberButton> numberButtons1 = new ArrayList<>();
	

	@Override
	public void initGui() {
		super.initGui();

		numberButtons1.add(
				new NumberButton(900, 135, 100, 100, 20, Creative.INSTANCE.settingsManager.blockOverlayManager.overlayred));
		numberButtons1.add(
				new NumberButton(900, 135, 130, 100, 20, Creative.INSTANCE.settingsManager.blockOverlayManager.overlayblue));
		numberButtons1.add(
				new NumberButton(900, 135, 160, 100, 20, Creative.INSTANCE.settingsManager.blockOverlayManager.overlaygreen));
		numberButtons1.add(
				new NumberButton(900, 135, 190, 100, 20, Creative.INSTANCE.settingsManager.blockOverlayManager.overlayalpha));
		numberButtons1.add(
				new NumberButton(900, 135, 220, 100, 20, Creative.INSTANCE.settingsManager.blockOverlayManager.outlinered));
		numberButtons1.add(
				new NumberButton(900, 135, 250, 100, 20, Creative.INSTANCE.settingsManager.blockOverlayManager.outlineblue));
		numberButtons1.add(
				new NumberButton(900, 135, 280, 100, 20, Creative.INSTANCE.settingsManager.blockOverlayManager.outlinegreen));
		numberButtons1.add(
				new NumberButton(900, 135, 310, 100, 20, Creative.INSTANCE.settingsManager.blockOverlayManager.outlinealpha));

		toggleButtons1.add(new ToggleButton(300, 104, 40, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 3,
				Creative.INSTANCE.settingsManager.blockOverlayManager.chroma));
		toggleButtons1.add(new ToggleButton(300, 150, 40, Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 3,
				Creative.INSTANCE.settingsManager.blockOverlayManager.outline));

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		ScaledResolution sr = new ScaledResolution(mc);
		super.drawScreen(mouseX, mouseY, partialTicks);

		Gui.drawRect(130, 50, sr.getScaledWidth() - 130, sr.getScaledHeight() - 50, new Color(0, 0, 0, 170).getRGB());
		this.drawHorizontalLine(135, sr.getScaledWidth() - 135, 80, -1);
		GlStateManager.pushMatrix();
		GlStateManager.scale(1.3, 1.3, 1);
		this.drawString(fontRendererObj, "Block Overlay", (int) (Math.round(135 * 0.76923076923)),
				(int) (Math.round(60 * 0.76923076923)), -1);
		GlStateManager.popMatrix();

		for (ToggleButton m : toggleButtons1) {
			m.draw();
		}

		for (NumberButton n : numberButtons1) {
			n.draw(mouseX, mouseY);
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

		for (ToggleButton m : toggleButtons1) {
			m.onClick(mouseX, mouseY, mouseButton);
		}

		for (NumberButton n : numberButtons1) {
			n.onClick(mouseX, mouseY, mouseButton);
		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {

		for (NumberButton n : numberButtons1) {
			n.getSlider().mouseReleased(mouseX, mouseY);
		}

		super.mouseReleased(mouseX, mouseY, state);
	}

}
