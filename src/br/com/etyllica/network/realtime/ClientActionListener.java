package br.com.etyllica.network.realtime;

import br.com.etyllica.network.realtime.model.Message;

public interface ClientActionListener<S> {

	public void playerJoin(int id, S state);

	public void playerLeft(int id, S state);
	
	public void updateStates(S[] states);
	
	public void receiveMessage(Message message);
	
}
