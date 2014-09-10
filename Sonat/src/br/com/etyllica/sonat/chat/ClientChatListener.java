package br.com.etyllica.sonat.chat;

public interface ClientChatListener {

	public void updateNames(String[] names);
	
	public void receiveMessage(String name, String message);
	
}
