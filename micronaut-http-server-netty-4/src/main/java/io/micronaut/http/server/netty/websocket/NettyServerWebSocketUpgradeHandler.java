package io.micronaut.http.server.netty.websocket;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.micronaut.http.server.netty.NettyHttpRequest;
import io.netty.channel.ChannelHandlerContext;

@Weave
public abstract class NettyServerWebSocketUpgradeHandler {

	@Trace(dispatcher = true)
	protected void channelRead0(ChannelHandlerContext ctx, NettyHttpRequest<?> msg) {

		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public boolean acceptInboundMessage(Object msg) {
		return Weaver.callOriginal();
	}
}
