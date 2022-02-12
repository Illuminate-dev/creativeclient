package creativeclient.hud.mod.impl;


import creativeclient.hud.mod.HudMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class ArmorStatus extends HudMod {
  
  public ArmorStatus() {
		super("Armor Status", 180, 180);
		// TODO Auto-generated constructor stub
	}

  @Override
  public int getWidth() {
    return 40;
  }
  
  @Override
  public int getHeight() {
    return 60;
  }
  
  @Override
  public void draw() {
	
	for (int i = 0; i < this.mc.thePlayer.inventory.armorInventory.length; i++) {
	      ItemStack itemStack = this.mc.thePlayer.inventory.armorInventory[i];
	      renderItemStack(i, itemStack);
	     
	    } 
	    super.draw();
	
    
  }
  
  @Override
  public void renderDummy(int mouseX, int mouseY) {
	if(!mc.gameSettings.showDebugInfo) {
	    renderItemStack( 3, new ItemStack((Item)Items.diamond_helmet));
	    renderItemStack( 2, new ItemStack((Item)Items.diamond_chestplate));
	    renderItemStack( 1, new ItemStack((Item)Items.diamond_leggings));
	    renderItemStack( 0, new ItemStack((Item)Items.diamond_boots));
	    super.renderDummy(mouseX, mouseY);
	}
	  
  }
  
  private void renderItemStack(int i, ItemStack itemStack) {
    if (itemStack == null)
      return; 
    GlStateManager.pushMatrix();
    int yAdd = -16 * i + 48;
    if (itemStack.getItem().isDamageable())
      this.fr.drawStringWithShadow(String.valueOf(itemStack.getMaxDamage() - itemStack.getItemDamage()), (this.getX() + 20), (this.getY() + yAdd + 5), -1); 
    RenderHelper.enableGUIStandardItemLighting();
    this.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, this.getX(), this.getY() + yAdd);
    GlStateManager.popMatrix();
  }
  

}