package creativeclient.ui.modgui;

import creativeclient.Creative;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class ModGui extends GuiScreen{
	
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
		Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
		super.initGui();
	}

}
