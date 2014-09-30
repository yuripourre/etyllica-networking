package br.com.etyllica.network.client;



public interface Client {

	public void init();
	
	public void prepare();
	
	public void connect() throws Exception;
		
	public void finish();
	
	public void sendTCP(String message);
		
}
