package br.com.etyllica.network.examples.action;

import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;
import br.com.etyllica.network.tide.platform.PlatformServerListener;
import br.com.tide.platform.player.PlatformPlayer;

public class TerminalActionServer extends PlatformServerListener {
	
	public TerminalActionServer() {
		super(200);
	}

	@Override
	public void join(int id) {
		System.out.println("New player connected! "+id);
	}

	@Override
	public void left(int id) {
		System.out.println("Player disconnected! "+id);
	}

	@Override
	public void handleState(int id, State state) {
		System.out.println("Receive from "+id);
		System.out.println("x: "+state.x);
		System.out.println("y: "+state.y);
		System.out.println("Act: "+state.action);
	}

	@Override
	public void handleKey(int id, KeyAction action) {
		System.out.println(id+": "+action.state+" "+action.key);
	}

	@Override
	public void handleMessage(int id, Message message) {
		System.out.println(message.sender+": "+message.text);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
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
