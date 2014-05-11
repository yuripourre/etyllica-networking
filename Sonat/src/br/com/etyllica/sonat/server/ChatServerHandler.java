package br.com.etyllica.sonat.server;


import java.util.HashMap;
import java.util.Map;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	private Map<Channel, String> names = new HashMap<Channel, String>();

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();

		for(Channel channel : channels) {
			channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has joined\n");
		}

		names.put(incoming, incoming.remoteAddress().toString());

		channels.add(ctx.channel());

	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel leaving = ctx.channel();

		for(Channel channel : channels) {
			channel.writeAndFlush("[SERVER] - " + leaving.remoteAddress() + " has left\n");
		}

		channels.remove(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {

		Channel incoming = ctx.channel();

		if(msg.startsWith("/name")) {

			changeName(incoming, msg);
			
		} else {

			for(Channel channel : channels) {

				channel.writeAndFlush("[" + names.get(incoming) + "]" + msg +"\n");

			}

		}
		
		System.out.println(msg);

	}
	
	private void changeName(Channel incoming, String msg) {
		
		String[] parts = msg.split(" ");
		
		if(parts.length < 2) {
			return;
		}
		
		String name = msg.split(" ")[1];
		
		System.out.println(names.get(incoming)+" change the name to "+name);
		
		names.put(incoming, name);
		
	}

}
