package br.com.etyllica.sonat.examples.chat;

import br.com.etyllica.sonat.server.ServerHandler;

public interface ServerChatListener extends ServerHandler {

	public void tellNames();
	
	public void changeName(String key, String msg);
	
	public void tellMessage(String name, String message);
		
}
