package examples.chat.listener;

public interface ServerHandler {

	public String getKey(Object channel);
	
	public void tellAll(String message);
	
}
