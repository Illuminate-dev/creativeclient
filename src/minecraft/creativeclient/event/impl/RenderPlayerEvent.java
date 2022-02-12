package creativeclient.event.impl;


import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;


public class RenderPlayerEvent extends PlayerEvent
{
    public final RenderPlayer renderer;
    public final float partialRenderTick;
    public final double x;
    public final double y;
    public final double z;

    public RenderPlayerEvent(EntityPlayer player, RenderPlayer renderer, float partialRenderTick, double x, double y, double z)
    {
        super(player);
        this.renderer = renderer;
        this.partialRenderTick = partialRenderTick;
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
    
