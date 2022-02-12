package creativeclient.settings;

import java.util.ArrayList;

import creativeclient.mod.GuiButtonManager;
import creativeclient.mod.impl.blockoverlay.BlockOverlayManager;
import creativeclient.mod.impl.hitboxes.HitBoxManager;
import creativeclient.mod.impl.itemmodel.ItemModelManager;
import creativeclient.mod.impl.itemphysics.ItemPhysicsManager;
import creativeclient.mod.impl.motionblur.MotionBlurManager;

public class SettingsManager {
	
	public ArrayList<GuiButtonManager> managers;
	
	public BlockOverlayManager blockOverlayManager;
	public MotionBlurManager motionBlurManager;
	public HitBoxManager hitboxManager;
	public ItemModelManager itemModelManager;
	public ItemPhysicsManager itemPhysicsManager;
	
	public SettingsManager() {
		managers = new ArrayList<>();
		
		managers.add(blockOverlayManager = new BlockOverlayManager());
		managers.add(motionBlurManager = new MotionBlurManager());
		managers.add(hitboxManager = new HitBoxManager());
		managers.add(itemModelManager = new ItemModelManager());
		managers.add(itemPhysicsManager = new ItemPhysicsManager());
	}

}
