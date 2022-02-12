package creativeclient.mod.impl.motionblur.resource;

import net.minecraft.client.resources.data.*;
import net.minecraft.util.*;
import net.minecraft.client.resources.*;
import java.util.*;

public class MotionBlurResourceManager extends FallbackResourceManager implements IResourceManager
{
    public MotionBlurResourceManager(final IMetadataSerializer frmMetadataSerializerIn) {
        super(frmMetadataSerializerIn);
    }
    
    public Set<String> func_135055_a() {
        return null;
    }
    
    public IResource func_110536_a(final ResourceLocation location) {
        return (IResource)new MotionBlurResource();
    }
    
    public List<IResource> func_135056_b(final ResourceLocation location) {
        return null;
    }
}