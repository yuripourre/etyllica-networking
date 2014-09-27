package br.com.etyllica.network.examples.action.model.listener;

import br.com.etyllica.network.examples.action.model.State;

public interface ClientActionListener {

	public void updateStates(State[] states);
	
	public void receiveMessage(String name, String message);
	
}
