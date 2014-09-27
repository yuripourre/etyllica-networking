package br.com.etyllica.network.examples.action.model;

import br.com.etyllica.network.client.Client;

public interface ActionClient extends Client {

	public void sendState();
	
	public void sendMessage(String message);
	
}
