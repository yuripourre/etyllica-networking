package br.com.etyllica.network.realtime;

import br.com.etyllica.network.realtime.model.Message;

public interface ClientActionListener<S> extends ActionStateListener<S> {

	public void playerJoin(int id);

	public void playerLeft(int id);
	
	public void updateStates(S[] states);
	
	public void receiveMessage(Message message);
	
}
