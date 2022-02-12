package creativeclient.ui;

import java.awt.Color;
import java.io.IOException;

import org.apache.commons.compress.archivers.zip.UnicodeCommentExtraField;
import org.lwjgl.opengl.GL11;

import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;

import creativeclient.Creative;
import creativeclient.ui.logingui.LoginMenu;
import creativeclient.util.SessionChanger;
import creativeclient.util.UnicodeFontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class CreativeMainMenu extends GuiScreen {
	
	protected CustomTextField mailInput;
	protected CustomTextField passInput;
	private boolean displayCredError = false;
	private int timer;
	
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		
		GL11.glColor4f(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(new ResourceLocation("creativeclient/wallpaper.jpg"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
		GlStateManager.pushMatrix();
		GlStateManager.translate(sr.getScaledWidth() / 30, sr.getScaledHeight() / 10, 1);
		GlStateManager.scale(mc.displayHeight / 200, mc.displayHeight / 200, 1);
		GlStateManager.translate(-(sr.getScaledWidth() / 30), -(sr.getScaledHeight() / 10), 1);
		
		this.drawString(this.fontRendererObj, "Welcome §6" + mc.session.getUsername() + "!", sr.getScaledWidth() / 30, sr.getScaledHeight() / 10, -1);
		GlStateManager.popMatrix();
		
		
		Gui.drawRect(sr.getScaledWidth() - (sr.getScaledWidth() / 4), 0, sr.getScaledWidth(), sr.getScaledHeight(), new Color(0,0,0, 170).getRGB());
		GlStateManager.pushMatrix();
		GlStateManager.translate(sr.getScaledWidth() - (sr.getScaledWidth() / 4) + (sr.getScaledWidth() / 50), 10, 1);
		GlStateManager.scale(mc.displayHeight / 250, mc.displayHeight / 250, 1);
		
		GlStateManager.translate(-(sr.getScaledWidth() - (sr.getScaledWidth() / 4) + (sr.getScaledWidth() / 50)), -(10), 1);
		
		
		this.drawString(this.fontRendererObj, Creative.INSTANCE.NAME, sr.getScaledWidth() - (sr.getScaledWidth() / 4) + (sr.getScaledWidth() / 50), 10, -1);
		GlStateManager.popMatrix();
		
		mc.fontRendererObj.drawString("Email:", sr.getScaledWidth() / 4 * 3 + 8, sr.getScaledHeight() - 100 + 5, -1);
		mc.fontRendererObj.drawString("Password:", sr.getScaledWidth() / 4 * 3 + 8, sr.getScaledHeight() - 80 + 5, -1);
		mailInput.drawTextBox();
		passInput.drawTextBox();
		if (displayCredError) {
			
			mc.fontRendererObj.drawString("Wrong Email/Password.", sr.getScaledWidth() / 4 * 3 + 8, sr.getScaledHeight() - 120, -1);
			timer -= 2;
			if(timer < 0) {
				displayCredError = false;
			}
		} else {
			timer = 150;
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		ScaledResolution sr = new ScaledResolution(mc);
		GlStateManager.pushMatrix();
		int scaleFactor = new ScaledResolution(this.mc).getScaleFactor();
		double scale = 1.0/scaleFactor;
		GlStateManager.scale(scale, scale, scale);
		
		mailInput = new CustomTextField(101, mc.fontRendererObj, sr.getScaledWidth() / 4 * 3 + 35, sr.getScaledHeight() - 100, sr.getScaledWidth() / 4 - 15 - mc.fontRendererObj.getStringWidth("Email:"), 15);
		mailInput.setMaxStringLength(100);
		passInput = new CustomTextField(101, mc.fontRendererObj, sr.getScaledWidth() / 4 * 3 + 35 + 24, sr.getScaledHeight() - 80, sr.getScaledWidth() / 4 - 39 - mc.fontRendererObj.getStringWidth("Email:"), 15);
		
		this.buttonList.add(new GuiButton(1, 10, height - 25, sr.getScaledWidth() / 4 - 15, 20, "Singleplayer"));
		this.buttonList.add(new GuiButton(2, sr.getScaledWidth() / 4 + 10, height - 25, sr.getScaledWidth() / 4 - 15, 20, "Multiplayer"));
		this.buttonList.add(new GuiButton(3, sr.getScaledWidth() / 4 * 2 + 10, height - 25, sr.getScaledWidth() / 4 - 15, 20, "Settings"));
		this.buttonList.add(new GuiButton(4, sr.getScaledWidth() / 4 * 3 + 10, height - 25, sr.getScaledWidth() / 4 - 15, 20, "Quit"));
		this.buttonList.add(new GuiButton(892, sr.getScaledWidth() / 4 * 3 + 10, height - 50, sr.getScaledWidth() / 4 - 15, 20, "Login"));
		GlStateManager.popMatrix();
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == 1) {
			mc.displayGuiScreen(new GuiSelectWorld(this));
		}
		if(button.id == 2) {
			mc.displayGuiScreen(new GuiMultiplayer(this));
		}
		if(button.id == 3) {
			mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
		}
		if(button.id == 4) {
			mc.shutdown();
		}
		if(button.id == 892) {
			if(!mailInput.getText().isEmpty()) {

				if(passInput.getText().isEmpty()) {
					SessionChanger.getInstance().setUserOffline(mailInput.getText());
				} else {
					try {
						SessionChanger.getInstance().setUser(mailInput.getText(), passInput.getText());	
					} catch (InvalidCredentialsException e) {
						displayCredError = true;
					} catch (AuthenticationException e) {
						
						e.printStackTrace();
					}
				}

			} 
		}
		
		super.actionPerformed(button);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		mailInput.textboxKeyTyped(typedChar, keyCode);
		passInput.textboxKeyTyped(typedChar, keyCode);
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		ScaledResolution sr = new ScaledResolution(mc);
		
		mailInput.mouseClicked(mouseX, mouseY, mouseButton);
		passInput.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public void updateScreen() {
		mailInput.updateCursorCounter();
		passInput.updateCursorCounter();
	}

}
