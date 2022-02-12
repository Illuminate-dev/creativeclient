package creativeclient.mod.impl.itemphysics;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import creativeclient.Creative;
import creativeclient.ui.modgui.ModGui;
import creativeclient.ui.modgui.NumberButton;
import creativeclient.ui.modgui.ToggleButton;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class ItemPhysicsGui extends ModGui {
	
	ArrayList<ToggleButton> toggleButtons = new ArrayList<>();
	ArrayList<NumberButton> numberButtons = new ArrayList<>();
	
	@Override
	public void initGui() {
		super.initGui();
		
		toggleButtons.add(new ToggleButton(135, 100, 100, 20, Creative.INSTANCE.settingsManager.itemPhysicsManager.old));
		
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		ScaledResolution sr = new ScaledResolution(mc);

		Gui.drawRect(130, 50, sr.getScaledWidth() - 130, sr.getScaledHeight() - 50, new Color(0, 0, 0, 170).getRGB());
		this.drawHorizontalLine(135, sr.getScaledWidth() - 135, 80, -1);
		GlStateManager.pushMatrix();
		GlStateManager.scale(1.3, 1.3, 1);
		this.drawString(fontRendererObj, "Item Physics", (int) (Math.round(135 * 0.76923076923)),
				(int) (Math.round(60 * 0.76923076923)), -1);
		GlStateManager.popMatrix();

		for (ToggleButton m : toggleButtons) {
			m.draw();
		}

		for (NumberButton n : numberButtons) {
			n.draw(mouseX, mouseY);
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		for (ToggleButton m : toggleButtons) {
			m.onClick(mouseX, mouseY, mouseButton);
		}

		for (NumberButton n : numberButtons) {
			n.onClick(mouseX, mouseY, mouseButton);
		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		for (NumberButton n : numberButtons) {
			n.getSlider().mouseReleased(mouseX, mouseY);
		}

		super.mouseReleased(mouseX, mouseY, state);
	}

}
