package br.com.etyllica.network.realtime;

import br.com.etyllica.network.examples.action.Sender;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;
import br.com.etyllica.network.server.ServerListener;

public interface ServerActionListener<S> extends ServerListener {

	public void handleState(int id, State state);
	
	public void handleKey(int id, KeyAction action);
	
	public void handleMessage(int id, Message message);
	
	public S[] getStates();
	
	public void setSender(Sender sender);
		
}
