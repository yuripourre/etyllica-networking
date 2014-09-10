package br.com.etyllica.sonat.examples.action.model;

import br.com.etyllica.sonat.server.ServerHandler;

public interface ServerActionListener extends ServerHandler {

	public void tellStates();
	
	public void tellMessage(String name, String message);
		
}
