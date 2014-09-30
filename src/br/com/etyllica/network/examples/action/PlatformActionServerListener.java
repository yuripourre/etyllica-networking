package br.com.etyllica.network.examples.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.ServerActionListener;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;
import br.com.tide.input.controller.Controller;
import br.com.tide.platform.player.PlatformPlayer;
import br.com.tide.platform.player.PlatformPlayerListener;

public abstract class PlatformActionServerListener implements ServerActionListener, Runnable, PlatformPlayerListener {
	
	private int count = 0;
	
	protected Map<Integer, Controller> controllers = new HashMap<Integer, Controller>();
	
	protected Map<Integer, PlatformPlayer> players = new HashMap<Integer, PlatformPlayer>();
	
	protected Map<Integer, State> states = new LinkedHashMap<Integer, State>();
	
	protected Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
	
	protected Map<Integer, String> names = new HashMap<Integer, String>();
		
	private int interval = 500;
	
	private Sender sender;
	
	public PlatformActionServerListener(int interval) {
		super();
		
		this.interval = interval;
	}
	
	public void run() {
		
		while(true) {
		    
			execute();
		    
		    try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void execute() {
		
		for(Entry<Integer, PlatformPlayer> entry: players.entrySet()) {
			
			int id = entry.getKey();
						
			PlatformPlayer player = entry.getValue();
			player.update(0);
			
			//update state						
			State state = states.get(id);
			state.x = player.getX();
			state.y = player.getY();
		}
		
		sender.sendToAllUDP(getStates());
	}

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
				
		State state = new State();
		state.id = id;
		state.y = 60;
		state.x = 20+60*count;
		
		//Set Listener
		PlatformPlayer listener = new PlatformPlayer(this);
		listener.setX(state.x);
		listener.setY(state.y);
				
		players.put(id, listener);
		controllers.put(id, new Controller(listener));
		
		if(count >= 1) {
			state.action = "WAITING";
		}
		
		states.put(id, state);
		
		count++;
	}

	@Override
	public void left(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleState(int id, State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKey(int id, KeyAction action) {

		Controller controller = controllers.get(id);
		
		KeyEvent event = new KeyEvent(action.key, action.state);
		controller.handleEvent(event);		
	}

	@Override
	public void handleMessage(int id, Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public State[] getStates() {
		State[] array = states.values().toArray(new State[states.size()]);		
		return array;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}	

}
