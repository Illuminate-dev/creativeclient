package creativeclient.hud.mod;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import creativeclient.Creative;
import creativeclient.hud.DraggableComponent;
import creativeclient.hud.ScreenPosition;
import creativeclient.mod.BasicMod;
import creativeclient.settings.Setting;
import creativeclient.util.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class HudMod extends BasicMod {
	
	public ScreenPosition pos;
	
	public Minecraft mc = Minecraft.getMinecraft();
	public FontRenderer fr = mc.fontRendererObj;
	
	public ArrayList<Setting> settings;
	
	public Config config;
	public String name;
	public boolean enabled;
	public DraggableComponent drag;
	
	//§
	
	public int x,y;
	
	public HudMod(String name, int x, int y) {
		this.name = name;
		this.config = new Config();
		this.config.loadModConfig();
		
		
		try {
			this.x = (int) config.config.get(name.toLowerCase() + " x");
			this.y = (int) config.config.get(name.toLowerCase() + " y");
			this.setEnabled((boolean) config.config.get(name.toLowerCase() + " enabled"));
			this.pos = new ScreenPosition(x, y);
				
		} catch (NullPointerException e) {
			this.x = x;
			this.y = y;
			this.enabled = false;
			this.pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
			
		}
		
		
		settings = new ArrayList<Setting>();
		drag = new DraggableComponent(this.x, this.y, getWidth(), getHeight(), new Color(0, 0, 0, 0).getRGB());
	}
	
	
	public ScreenPosition load() {
		
		return pos;
	}
	
	
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}
	
	public void addSettings(Setting...sets) {
		this.settings.add((Setting) Arrays.asList(sets));			

	}
	
	public int getWidth() {
		return 50;

	}
	
	public int getHeight() {
		return 50;
	}
	
	public void draw() {
		
	}
	
	
	public void renderDummy(int mouseX, int mouseY) {
		
		drag.draw(mouseX, mouseY, this.name);
			
		

	}
	
	public void renderDummy(int mouseX, int mouseY,	ScreenPosition pos) {
		drag.draw(mouseX, mouseY, this.name);

	}
	
	public int getX() {
		return drag.getxPosition();
	}
	
	public int getY() {
		return drag.getyPosition();
	}
	
	public void setEnabled(boolean enabled) {
		
		this.enabled = enabled;
		
	}
	
	public void toggle() {
		this.setEnabled(!enabled);

	}
	public boolean isEnabled() {
		return enabled;
	}


	@Override
	public GuiScreen guiScreen() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
