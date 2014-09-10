package br.com.etyllica.sonat.examples.chat;

public interface ClientChatListener {

	public void updateNames(String[] names);
	
	public void receiveMessage(String name, String message);
	
}
