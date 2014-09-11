package br.com.etyllica.network.examples.chat;

import br.com.etyllica.network.server.ServerHandler;

public interface ServerChatListener extends ServerHandler {

	public void tellNames();
	
	public void changeName(String key, String msg);
	
	public void tellMessage(String name, String message);
		
}
