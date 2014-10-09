package br.com.etyllica.network.realtime;

import br.com.etyllica.network.realtime.model.Message;

public interface ClientActionListener<S> {

	public void updateStates(S[] states);
	
	public void receiveMessage(Message message);
	
}
