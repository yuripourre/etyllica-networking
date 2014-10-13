package examples.action.model;

import br.com.etyllica.network.client.Client;
import br.com.etyllica.network.realtime.model.KeyAction;

public interface ActionClient extends Client {

	public void sendState();
	
	public void sendMessage(String message);
	
	public void sendKeyAction(KeyAction action);
		
}
