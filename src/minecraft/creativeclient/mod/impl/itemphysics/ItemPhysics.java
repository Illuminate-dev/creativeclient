package creativeclient.mod.impl.itemphysics;

import creativeclient.mod.Category;
import creativeclient.mod.Mod;
import net.minecraft.client.gui.GuiScreen;

public class ItemPhysics extends Mod {

	public ItemPhysics() {
		super("Item Physics", "Realistic Item Physics", Category.MISC);
	
	}
	
	@Override
	public GuiScreen guiScreen() {
		return new ItemPhysicsGui();
	}

}
