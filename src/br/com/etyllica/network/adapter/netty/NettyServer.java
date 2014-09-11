package br.com.etyllica.network.adapter.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import br.com.etyllica.network.server.ServerImpl;

public class NettyServer extends ServerImpl {

	protected EventLoopGroup workerGroup;

	protected ServerBootstrap bootstrap;

	public NettyServer(int port) {
		super(port);
	}

	public void init() {

		workerGroup = new NioEventLoopGroup();

		bootstrap = new ServerBootstrap()
		.group(/*bossGroup,*/workerGroup)
		.channel(NioServerSocketChannel.class);
	}

	@Override
	public void prepare() {
		
	}

	@Override
	public void bind() throws Exception {
		bootstrap.bind(tcpPort).sync().channel().closeFuture().sync();
	}

	@Override
	public void stop() throws Exception {
		workerGroup.shutdownGracefully();
	}

}
