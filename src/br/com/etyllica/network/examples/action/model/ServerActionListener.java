package br.com.etyllica.network.examples.action.model;

import br.com.etyllica.network.server.ServerHandler;

public interface ServerActionListener extends ServerHandler {

	public void tellStates();
	
	public void tellMessage(String name, String message);
		
}
