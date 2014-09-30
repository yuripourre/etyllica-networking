package br.com.etyllica.network.examples.action;

public interface Sender {

	public void sendToAllTCP(Object object);
		
	public void sendToAllUDP(Object object);
	
}
