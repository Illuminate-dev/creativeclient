package creativeclient.mod.impl.perspective;

import org.lwjgl.input.Keyboard;

import org.lwjgl.opengl.Display;

import creativeclient.Creative;
import creativeclient.event.EventTarget;
import creativeclient.event.impl.KeyEvent;
import creativeclient.mod.Category;
import creativeclient.mod.Mod;

public class PerspectiveMod extends Mod {

	public PerspectiveMod() {
		super("FreeLook", "Perspective Mod", Category.MISC);
		// TODO Auto-generated constructor stub
	}


	private boolean returnOnRelease = true; //hold down the key
	private boolean perspectiveToggled = false;
	
	private float cameraYaw = 0f;
	private float cameraPitch = 0f;
	private int prevPerspective = 0; //prev f5 state
	
	
	
	
	public void keyboardEvent(KeyEvent e) {
		
		if(e.getKey() == mc.gameSettings.PERSPECTIVE_MOD.getKeyCode()) {
			
			if(Keyboard.getEventKeyState()) {
				perspectiveToggled = !perspectiveToggled;
				
				cameraYaw = mc.thePlayer.rotationYaw;
				cameraPitch = mc.thePlayer.rotationPitch;
				
				if(perspectiveToggled) {
					prevPerspective = mc.gameSettings.thirdPersonView;
					mc.gameSettings.thirdPersonView = 1;
				} else {
					mc.gameSettings.thirdPersonView = prevPerspective;
				}
				
			} else if (returnOnRelease) {
				perspectiveToggled = false;
				mc.gameSettings.thirdPersonView = prevPerspective;
			}
			
		}
		
		if(Keyboard.getEventKey()== mc.gameSettings.keyBindTogglePerspective.getKeyCode()) {
			perspectiveToggled = false;
		}
						
	}
	
	public float getCameraYaw() {
		return perspectiveToggled ? cameraYaw : mc.thePlayer.rotationYaw;
	}
	
	public float getCameraPitch() {
		return perspectiveToggled ? cameraPitch : mc.thePlayer.rotationPitch;
	}
	
	public boolean overrideMouse() {
		
		if(mc.inGameHasFocus && Display.isActive()) {
			
			if(!perspectiveToggled) {
				return true;
			}
			
			// CODE
			mc.mouseHelper.mouseXYChange();
			float f1 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float f2 = f1 * f1 * f1 * 8.0F;
			float f3 = (float) mc.mouseHelper.deltaX * f2;
			float f4 = (float) mc.mouseHelper.deltaY * f2;
		
			cameraYaw += f3 * 0.15F;
			cameraPitch -= f4 * 0.15F;
		
			if (cameraPitch > 90) cameraPitch = 90;
			if (cameraPitch < -90) cameraPitch = -90;
			
			
		}
		
		return false;
		
	}
}
