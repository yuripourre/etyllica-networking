package br.com.etyllica.network.server;

public interface ServerHandler {

	public String getKey(Object channel);
	
	public void tellAll(String message);
	
}
