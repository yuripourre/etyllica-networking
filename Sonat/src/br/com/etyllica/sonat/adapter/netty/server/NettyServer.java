package br.com.etyllica.sonat.adapter.netty.server;


import java.util.HashMap;

import br.com.etyllica.sonat.server.Server;
import br.com.etyllica.sonat.server.ServerImpl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer extends ServerImpl implements Server {
	
	public NettyServer(int port) {
		super(port);
	}
	
	public void init() throws Exception {
		
		System.out.println("Starting Server..."+port);
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			
			ServerBootstrap bootstrap = new ServerBootstrap()
			.group(/*bossGroup,*/workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.childHandler(new NettyChatServerInitializer(new HashMap<String, String>()));
			
			bootstrap.bind(port).sync().channel().closeFuture().sync();
		
		} finally {
			
			bossGroup.shutdownGracefully();
			
			workerGroup.shutdownGracefully();
			
		}
		
	}
	
}
