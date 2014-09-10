package br.com.etyllica.sonat.adapter.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import br.com.etyllica.sonat.server.Server;
import br.com.etyllica.sonat.server.ServerImpl;

public class NettyServer extends ServerImpl implements Server {

	protected EventLoopGroup bossGroup;

	protected EventLoopGroup workerGroup;

	protected ServerBootstrap bootstrap;

	public NettyServer(int port) {
		super(port);
	}

	public void init() {

		bossGroup = new NioEventLoopGroup();

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
		bossGroup.shutdownGracefully();

		workerGroup.shutdownGracefully();
	}

}
