package br.com.etyllica.network.server;

// Common server methods

public interface ServerListener {

	public void join(int id);
	
	public void left(int id);
	
}
