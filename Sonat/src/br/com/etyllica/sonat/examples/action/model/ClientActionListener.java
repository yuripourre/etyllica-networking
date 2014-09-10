package br.com.etyllica.sonat.examples.action.model;

public interface ClientActionListener {

	public void updateStates(State[] states);
	
	public void receiveMessage(String name, String message);
	
}
