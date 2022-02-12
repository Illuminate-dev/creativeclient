package creativeclient.event.impl;

import creativeclient.event.Event;
import net.minecraft.util.IChatComponent;

public class KeyEvent extends Event{
	public int key;
	
	public int getKey() {
		return key;
	}

	public KeyEvent(int key) {
		this.key = key;
	}
	
}
