package br.com.etyllica.network.examples.action.model.listener;

import br.com.etyllica.network.examples.action.model.KeyAction;
import br.com.etyllica.network.examples.action.model.Message;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.server.ServerListener;

public interface ServerActionListener extends ServerListener {

	public void handleState(int id, State state);
	
	public void handleKey(int id, KeyAction action);
	
	public void handleMessage(int id, Message message);
		
}
