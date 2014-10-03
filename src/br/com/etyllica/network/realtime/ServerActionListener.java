package br.com.etyllica.network.realtime;

import br.com.etyllica.network.Sender;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;
import br.com.etyllica.network.server.ServerListener;

public interface ServerActionListener<S> extends ServerListener {

	public void handleState(int id, S state);
	
	public void handleKey(int id, KeyAction action);
	
	public void handleMessage(int id, Message message);
		
	public void setSender(Sender sender);
	
	public S[] getStates();
	
	public Class<?> getStateClass();
	
}
