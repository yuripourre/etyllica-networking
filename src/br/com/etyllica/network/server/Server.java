package br.com.etyllica.network.server;

public interface Server {

	public void init();
	
	public void prepare();
	
	public void bind() throws Exception;
	
	public void stop() throws Exception;
}
