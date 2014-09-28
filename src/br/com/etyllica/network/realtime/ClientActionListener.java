package br.com.etyllica.network.realtime;

import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.model.Message;

public interface ClientActionListener {

	public void updateStates(State[] states);
	
	public void receiveMessage(Message message);
	
}
