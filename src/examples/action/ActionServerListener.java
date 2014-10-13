package examples.action;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.etyllica.network.Sender;
import br.com.etyllica.network.realtime.ServerActionListener;
import br.com.etyllica.network.realtime.model.Message;

public abstract class ActionServerListener<T, S> implements ServerActionListener<S>, Runnable {
	
	private int count = 0;

	protected Map<Integer, T> players = new HashMap<Integer, T>();
	
	protected Map<Integer, S> states = new LinkedHashMap<Integer, S>();
	
	protected Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
	
	protected Map<Integer, String> names = new HashMap<Integer, String>();
		
	private int interval = 500;
	
	private Sender sender;
	
	public ActionServerListener(int interval) {
		super();
		
		this.interval = interval;
	}
	
	public void run() {
		
		while(true) {
		    
			process();
		    
		    try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void process() {
		
		execute();
		
		for(Entry<Integer, T> entry: players.entrySet()) {
			
			int id = entry.getKey();
						
			T player = entry.getValue();
			updatePlayer(player);
			
			S state = states.get(id);
			updateState(state, player);
			
		}
		
		sender.sendToAllUDP(getStates());
	}
	
	public abstract void execute();

	public abstract S createState(int id);
	
	public abstract T createPlayer(int id, S state);
	
	public abstract void updatePlayer(T player);
	
	public abstract void updateState(S state, T player);
	
	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public void join(int id) {

		ids.put(id, count);
		names.put(id, "Player "+count+1);
		
		S state = createState(id);
		
		//Set Listener
		T listener = createPlayer(id, state);
				
		players.put(id, listener);
				
		states.put(id, state);
		
		count++;
	}
	
	@Override
	public void left(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleState(int id, S state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMessage(int id, Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public S[] getStates() {
		
		Class<?> clazz = getStateClass();
		
		int capacity = states.size();
		
		@SuppressWarnings("unchecked")
		S[] array = (S[])Array.newInstance(clazz,capacity);
	    
		array = states.values().toArray(array);
		
		return array;
	}
	
	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}	

}
