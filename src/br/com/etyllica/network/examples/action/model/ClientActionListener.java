package br.com.etyllica.network.examples.action.model;

public interface ClientActionListener {

	public void updateStates(State[] states);
	
	public void receiveMessage(String name, String message);
	
}
