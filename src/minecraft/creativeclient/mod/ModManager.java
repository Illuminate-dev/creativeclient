package creativeclient.mod;

import java.util.ArrayList;

import creativeclient.mod.impl.*;
import creativeclient.mod.impl.animationsmod.AnimationsMod;
import creativeclient.mod.impl.autogg.AutoGG;
import creativeclient.mod.impl.blockoverlay.BlockOverlay;
import creativeclient.mod.impl.hitboxes.HitBoxMod;
import creativeclient.mod.impl.itemmodel.ItemModel;
import creativeclient.mod.impl.itemphysics.ItemPhysics;
import creativeclient.mod.impl.motionblur.MotionBlur;
import creativeclient.mod.impl.perspective.PerspectiveMod;
import creativeclient.mod.impl.seenames.DeobfNames;

public class ModManager {
	
	public TestMod testMod;
	public ToggleSprint toggleSprint;
	public AnimationsMod animationsMod;
	public Fullbright fullbright;
	public TNTTimer tntTimer;
	public ScrollZoom zoom;
	public BlockOverlay overlay;
	public ChatBackground chat;
	public MotionBlur motionBlur;
	public HitBoxMod hitbox;
	public ItemModel itemModel;
	public AutoGG autoGG;
	public DeobfNames deobfnames;
	public ItemPhysics itemPhysics;
	public PerspectiveMod perspective;
	
	
	public ArrayList<Mod> mods;
	
	public ModManager() {
		mods = new ArrayList<>();
		
		//Misc
		mods.add(testMod = new TestMod());
		mods.add(toggleSprint = new ToggleSprint());
		mods.add(animationsMod = new AnimationsMod());
		mods.add(fullbright = new Fullbright());
		mods.add(tntTimer = new TNTTimer());
		mods.add(zoom = new ScrollZoom());
		mods.add(overlay = new BlockOverlay());
		mods.add(chat = new ChatBackground());
		mods.add(motionBlur = new MotionBlur());
		mods.add(hitbox = new HitBoxMod());
		mods.add(itemModel = new ItemModel());
		mods.add(autoGG = new AutoGG());
		mods.add(deobfnames = new DeobfNames());
		mods.add(itemPhysics = new ItemPhysics());
		mods.add(perspective = new PerspectiveMod());
		
	}

}
