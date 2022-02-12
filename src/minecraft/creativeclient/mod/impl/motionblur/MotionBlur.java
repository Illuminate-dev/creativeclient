package creativeclient.mod.impl.motionblur;

import java.lang.reflect.Field;
import java.util.Map;


import net.minecraft.client.*;
import java.util.*;
import java.lang.reflect.*;
import java.io.*;
import net.minecraft.client.resources.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.shader.ShaderGroup;
import creativeclient.Creative;
import creativeclient.event.EventTarget;
import creativeclient.event.impl.ClientTickEvent;
import creativeclient.mod.Category;
import creativeclient.mod.impl.motionblur.resource.MotionBlurResourceManager;
import creativeclient.ui.clickgui.ClickGui;
import creativeclient.util.config.Config;
import org.lwjgl.input.*;

import com.google.gson.JsonSyntaxException;

import net.minecraft.util.*;
import net.minecraft.client.resources.SimpleReloadableResourceManager;


public class MotionBlur extends creativeclient.mod.Mod {
	
	private final Minecraft mc;
    private final Map<String, FallbackResourceManager> domainResourceManagers;
    private Field cachedFastRender;
    private int ticks;
    public static MotionBlur instance;
    private File configFile;
	private ShaderGroup blurShader;
	
	public MotionBlur() {
		super("Motionblur", "Blurs the screen", Category.MISC);
		
        this.mc = Minecraft.getMinecraft();
        this.domainResourceManagers = (Map<String, FallbackResourceManager>)((SimpleReloadableResourceManager)this.mc.getResourceManager()).domainResourceManagers;
        try {
            this.cachedFastRender = GameSettings.class.getDeclaredField("ofFastRender");
        }
        catch (Exception ex) {}
        
        this.config = new Config();
		config.loadModConfig();
		try {
			this.enabled = (boolean) config.config.get("motionblur enabled");
		} catch (NullPointerException e){
			this.enabled = false;
			
		}
		
        
	}
	
	@Override
	public void onEnable() {
		
		
        if (this.mc.thePlayer != null ) {
        	if(isFastRenderEnabled()) {
        		 Creative.INSTANCE.modManager.motionBlur.setEnabled(false);
        		 Creative.addChatMessage(EnumChatFormatting.RED + "[MotionBlur]" + EnumChatFormatting.WHITE + " Motion Blur is not compatible with OptiFine's Fast Render.");
        		 return;
        	}
        	
        	switch ((int) Creative.INSTANCE.settingsManager.motionBlurManager.blur.getValue()) {
				case 1:
					this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/phosphor1.json"));
		            this.mc.entityRenderer.getShaderGroup().createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
					break;
	
				case 2 :
					this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/phosphor2.json"));
		            this.mc.entityRenderer.getShaderGroup().createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
					break;
					
				case 3:
					this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/phosphor3.json"));
		            this.mc.entityRenderer.getShaderGroup().createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
					break;
	
				case 4 :
					this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/phosphor4.json"));
		            this.mc.entityRenderer.getShaderGroup().createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
					break;
					
				case 5:
					this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/phosphor5.json"));
		            this.mc.entityRenderer.getShaderGroup().createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
					break;
	
				case 6 :
					this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/phosphor6.json"));
		            this.mc.entityRenderer.getShaderGroup().createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
					break;
					
				case 7:
					this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/phosphor7.json"));
		            this.mc.entityRenderer.getShaderGroup().createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
					break;
			}
            
            
 
        }
    }
	
	@EventTarget
	public void onTick(ClientTickEvent e) {
		if (this.domainResourceManagers != null && !this.domainResourceManagers.containsKey("motionblur")) {
            this.domainResourceManagers.put("motionblur", new MotionBlurResourceManager(this.mc.metadataSerializer_));
        }
        ++this.ticks;
        if (this.ticks % 5000 == 0 && this.isFastRenderEnabled() && this.mc.thePlayer != null && this.mc.theWorld != null) {
            Creative.addChatMessage(EnumChatFormatting.RED + "[MotionBlur]" + " Motion Blur is not compatible with OptiFine's Fast Render.");
        }

	}
	public boolean isFastRenderEnabled() {
		try {
		    return this.cachedFastRender.getBoolean(this.mc.gameSettings);
		}
		catch (Exception ignored) {
		    return false;
		}
	}

}
