package br.com.etyllica.network.realtime.model;

import br.com.etyllica.core.event.KeyState;

public class KeyAction {

	public KeyAction() {
		
	}
	
	public KeyAction(int key, KeyState state) {
		this.key = key;
		this.state = state;
	}
	
	public int key;
	
	public KeyState state; 
	
}
