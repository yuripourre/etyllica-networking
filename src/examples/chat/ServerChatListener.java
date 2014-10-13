package examples.chat;

import examples.chat.listener.ServerHandler;

public interface ServerChatListener extends ServerHandler {

	public void tellNames();
	
	public void changeName(String key, String msg);
	
	public void tellMessage(String name, String message);
		
}
