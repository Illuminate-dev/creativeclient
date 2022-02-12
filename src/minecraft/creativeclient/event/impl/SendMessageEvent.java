package creativeclient.event.impl;

import creativeclient.event.Event;

public class SendMessageEvent extends Event {
	
	public String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SendMessageEvent(String message) {
		this.message = message;
	}
	
	

}
