package creativeclient.ui.clickgui;

import java.util.ArrayList;
import java.util.List;

import creativeclient.Creative;
import creativeclient.hud.mod.HudMod;
import creativeclient.mod.BasicMod;
import creativeclient.mod.Mod;
import creativeclient.util.RoundedGuiButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;

public class ScrollListTest extends GuiListExtended {

	private final List<GuiListExtended.IGuiListEntry> entrys = new ArrayList<GuiListExtended.IGuiListEntry>();
	
	public ScrollListTest(Minecraft mcIn, GuiTestList inGui) {
		super(mcIn, inGui.width, inGui.height, 63, inGui.height - 32, 20);
		int i = 0;
		for(HudMod mod : Creative.INSTANCE.hudManager.hudMods) {
			entrys.add(new BtnEntry(mod.name, mod, i * 5));
			i++;
		}
		for(Mod mod : Creative.INSTANCE.modManager.mods) {
			entrys.add(new BtnEntry(mod.name, mod, i * 5));
			i++;
		}
	}

	@Override
	public IGuiListEntry getListEntry(int index) {
		return entrys.get(index);
	}

	@Override
	protected int getSize() {
		return entrys.size();
	}

	public class BtnEntry implements GuiListExtended.IGuiListEntry
	{
		private final GuiButton btn;
		private final GuiButton btn2;
		private final BasicMod mod;
		private final int yadd;
		
		private BtnEntry(String name, BasicMod mod, int yadd)
		{
			this.btn = new GuiButton(0, 0, 0, 75, 20, name);
			this.btn2 = new GuiButton(0, 0, 0, 75, 20, name + " Settings");
			this.mod = mod;
			this.yadd = yadd;
		}
		

		@Override
		public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected)
		{
			this.btn.xPosition = x;
			this.btn.yPosition = y + yadd;
			this.btn.drawButton(ScrollListTest.this.mc, mouseX, mouseY);
			
			this.btn2.xPosition = x + 100;
			this.btn2.yPosition = y + yadd;
			this.btn2.drawButton(ScrollListTest.this.mc, mouseX, mouseY);
			
		}

		@Override
		public boolean mousePressed(int slotIndex, int x, int y, int p_148278_4_, int p_148278_5_, int p_148278_6_)
		{
			if(this.btn.mousePressed(mc, x, y)) {
				this.mod.toggle();
			} else if(this.btn2.mousePressed(mc, x, y)) {
				try {
					mc.displayGuiScreen(this.mod.guiScreen());
				} catch(NullPointerException e) {
					
				}
			}
			return this.btn.mousePressed(mc, x, y);
			
		}

		@Override
		public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
		{
			this.btn.mouseReleased(x, y);
		}

		@Override
		public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_)
		{
		}
	}

}
