package br.com.etyllica.network;

public interface Sender {

	public void sendToAllTCP(Object object);
		
	public void sendToAllUDP(Object object);
	
}
