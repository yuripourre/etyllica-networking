package br.com.etyllica.sonat.server;

public interface Server {

	public void init();
	
	public void prepare();
	
	public void bind() throws Exception;
	
	public void stop() throws Exception;
}
