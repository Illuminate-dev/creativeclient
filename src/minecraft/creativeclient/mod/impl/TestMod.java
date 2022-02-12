package creativeclient.mod.impl;

import creativeclient.mod.Category;
import creativeclient.mod.Mod;

public class TestMod extends Mod {

	public TestMod() {
		super("TestMod", "Test Mod", Category.MISC);
	}
	
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		System.out.println("THIS MOD IS ONNNNNN");

	}
	

}
