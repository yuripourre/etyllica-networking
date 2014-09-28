package br.com.etyllica.network.examples.action;

import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.ServerActionListener;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;

public class TerminalActionServer implements ServerActionListener {
	
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
	
}
