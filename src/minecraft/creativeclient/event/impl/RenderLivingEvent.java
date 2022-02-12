package creativeclient.event.impl;

import creativeclient.event.Event;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public class RenderLivingEvent extends Event{
	
	public final EntityLivingBase entity;
    public final RendererLivingEntity renderer;
    public final double x;
    public final double y;
    public final double z;

    public RenderLivingEvent(EntityLivingBase entity, RendererLivingEntity renderer, double x, double y, double z)
    {
        this.entity = entity;
        this.renderer = renderer;
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
