package io.micronaut.http.netty.websocket;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.micronaut.core.bind.BoundExecutable;
import io.micronaut.inject.MethodExecutionHandle;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

@Weave(type = MatchType.BaseClass)
public abstract class AbstractNettyWebSocketHandler {

	@Trace(dispatcher = true)
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","NettyWebSocketHandler",getClass().getSimpleName(),"channelRead0");
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	protected void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame msg) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","NettyWebSocketHandler",getClass().getSimpleName(),"handleWebSocketFrame");
		Weaver.callOriginal();
	}
	
	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	protected Object invokeExecutable(BoundExecutable boundExecutable, MethodExecutionHandle<?, ?> messageHandler) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","NettyWebSocketHandler",getClass().getSimpleName(),"invokeExecutable");
		return Weaver.callOriginal();
	}
}
