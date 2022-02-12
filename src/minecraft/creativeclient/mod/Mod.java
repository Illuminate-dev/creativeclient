package creativeclient.mod;

import creativeclient.Creative;
import creativeclient.util.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class Mod extends BasicMod {
	
	public Minecraft mc = Minecraft.getMinecraft();
	
	public String name, description;
	public boolean enabled;
	public Category category;
	public Config config;
	
	public Mod(String name, String description, Category category) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.config = new Config();
		config.loadModConfig();
		
		try {
			
			this.setEnabled((boolean) config.config.get(name.toLowerCase() + " enabled"));
			System.out.println(this.name + " : " + this.isEnabled());
				
		} catch (NullPointerException e) {
			this.enabled = false;
			
		}
	}
	
	public void onEnable() {
		Creative.INSTANCE.eventManager.register(this);

	}
	
	public void onDisable() {
		Creative.INSTANCE.eventManager.unregister(this);
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if(enabled) {
			onEnable();
		} else {
			onDisable();
		}
	}
	
	public void toggle() {
		setEnabled(!this.enabled);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Category getCategory() {
		return category;
	}

	@Override
	public GuiScreen guiScreen() {
		// TODO Auto-generated method stub
		return null;
	}

}
