package creativeclient.mod.impl.motionblur.resource;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.*;
import net.minecraft.util.*;
import java.io.*;
import java.util.*;
import java.util.Locale;
import java.nio.charset.*;
import org.apache.commons.io.*;

import creativeclient.Creative;
import net.minecraft.client.resources.data.*;

public class MotionBlurResource implements IResource
{
    private static final String JSON = "{\"targets\":[\"swap\",\"previous\"],\"passes\":[{\"name\":\"phosphor\",\"intarget\":\"minecraft:main\",\"outtarget\":\"swap\",\"auxtargets\":[{\"name\":\"PrevSampler\",\"id\":\"previous\"}],\"uniforms\":[{\"name\":\"Phosphor\",\"values\":[%.2f, %.2f, %.2f]}]},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"minecraft:main\"}]}";
    
    public ResourceLocation func_177241_a() {
        return null;
    }
    
    public InputStream func_110527_b() {
        final double amount = 0.7 + Creative.INSTANCE.settingsManager.motionBlurManager.blur.getValue() / 100.0 * 3.0 - 0.01;
        System.out.println(amount);
        return IOUtils.toInputStream(String.format(Locale.ENGLISH, "{\"targets\":[\"swap\",\"previous\"],\"passes\":[{\"name\":\"phosphor\",\"intarget\":\"minecraft:main\",\"outtarget\":\"swap\",\"auxtargets\":[{\"name\":\"PrevSampler\",\"id\":\"previous\"}],\"uniforms\":[{\"name\":\"Phosphor\",\"values\":[%.2f, %.2f, %.2f]}]},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"minecraft:main\"}]}", amount, amount, amount), Charset.defaultCharset());
    }
    
    public boolean func_110528_c() {
        return false;
    }
    
    public <T extends IMetadataSection> T func_110526_a(final String p_110526_1_) {
        return null;
    }
    
    public String func_177240_d() {
        return null;
    }

	@Override
	public ResourceLocation getResourceLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasMetadata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends IMetadataSection> T getMetadata(String p_110526_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResourcePackName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean createFile() {
		
		 try {
			File motionblur = new File(Minecraft.getMinecraft().mcDataDir + "motionblur.json");
			if(motionblur.createNewFile()) {
				 return true;
			 } else {
				 return false;
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return false;
	}
}
