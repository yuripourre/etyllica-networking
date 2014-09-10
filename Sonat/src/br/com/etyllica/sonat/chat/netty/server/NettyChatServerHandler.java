package br.com.etyllica.sonat.chat.netty.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;

import br.com.etyllica.sonat.chat.ChatServerHandler;

public class NettyChatServerHandler extends SimpleChannelInboundHandler<String> implements ChatServerHandler {

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	private Map<String, String> names;
	
	private final static String DELIMITER = " : ";
	
	private static final String COMMAND_USERS = "/users ";
	
	private static final String COMMAND_NAME = "/name ";
	
	private int count = 0;
	
	public NettyChatServerHandler(Map<String, String> names) {
		super();
		
		this.names = names;
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {

		if(msg==null||"null".equals(msg)) {
			return;
		}
		
		Channel channel = ctx.channel();

		String key = getKey(channel);
		
		String name = names.get(key);
		
		if(msg.startsWith(COMMAND_NAME)) {

			changeName(key, msg);
		
			tellNames();
						
		} else {
			
			tellMessage(name, msg);
									
		}
		
		System.out.println(name + DELIMITER + msg);
		
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		
		count++;

		String key = getKey(incoming);
		
		names.put(key, "visitante"+count);
				
		channels.add(incoming);
				
		tellNames();
		
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel leaving = ctx.channel();

		String key = getKey(leaving);
		
		names.remove(key);
				
		channels.remove(leaving);
		
		tellNames();
		
	}
	
	public void changeName(String key, String msg) {
				
		String[] parts = msg.split(" ");
		
		if(parts.length < 2) {
			return;
		}
		
		String name = msg.split(" ")[1];
		
		System.out.println(names.get(key)+" change the name to "+name);
		
		names.put(key, name);
		
	}
		
	public void tellNames() {
		
		StringBuilder builder = new StringBuilder();
		
		for(String name: names.values()) {
			
			builder.append(name);
			
			builder.append(" ");
			
		}
				
		tellAll(COMMAND_USERS+builder.toString());
		
	}
	
	public void tellMessage(String name, String message) {
		
		tellAll("/msg "+name+" "+message);
		
	}
	
	public void tellAll(String message) {
		
		for(Channel channel : channels) {

			channel.writeAndFlush(message+"\r\n");

		}
		
	}

	@Override
	public String getKey(Object channel) {
		
		Channel incoming = (Channel) channel; 
		
		return incoming.remoteAddress().toString();
	}

}
