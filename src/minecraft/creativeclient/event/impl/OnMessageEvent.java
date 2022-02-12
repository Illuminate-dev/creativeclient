package creativeclient.event.impl;

import creativeclient.event.Event;
import net.minecraft.util.IChatComponent;

public class OnMessageEvent extends Event{
	public IChatComponent message;
	
	public IChatComponent getMessage() {
		return message;
	}

	public void setMessage(IChatComponent message) {
		this.message = message;
	}

	public OnMessageEvent(IChatComponent message) {
		this.message = message;
	}
	
}
