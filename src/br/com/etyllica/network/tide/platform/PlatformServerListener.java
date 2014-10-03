package br.com.etyllica.network.tide.platform;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.network.examples.action.ActionServerListener;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.tide.input.controller.Controller;
import br.com.tide.platform.player.PlatformPlayer;
import br.com.tide.platform.player.PlatformPlayerListener;

public class PlatformServerListener extends ActionServerListener<PlatformPlayer, State> implements PlatformPlayerListener {

	protected Map<Integer, Controller> controllers = new HashMap<Integer, Controller>();
	
	public PlatformServerListener(int interval) {
		super(interval);
	}

	@Override
	public PlatformPlayer createPlayer(int id, State state) {
		
		PlatformPlayer listener = new PlatformPlayer(this);
		listener.setX(state.x);
		listener.setY(state.y);

		controllers.put(id, new Controller(listener));
		
		return listener;
	}
	
	@Override
	public void updatePlayer(PlatformPlayer player) {
		player.update(0);
	}
	
	@Override
	public State createState(int id) {
		
		State state = new State();
		state.id = id;
		state.y = 60;
		state.x = 20+60*id;
		
		return state;
	}
	
	@Override
	public void updateState(State state, PlatformPlayer player) {
		//update state
		state.x = player.getX();
		state.y = player.getY();
	}
	
	public Class<?> getStateClass() {
		return State.class;
	}
	
	@Override
	public void handleKey(int id, KeyAction action) {

		Controller controller = controllers.get(id);
		
		KeyEvent event = new KeyEvent(action.key, action.state);
		controller.handleEvent(event);		
	}

	@Override
	public void onTurnLeft(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTurnRight(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWalkLeft(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWalkRight(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLookUp(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStandDown(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onJump(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFall(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRun(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopWalkLeft(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopWalkRight(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopLookUp(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopStandDown(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopJump(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopRun(PlatformPlayer player) {
		// TODO Auto-generated method stub
		
	}

}
