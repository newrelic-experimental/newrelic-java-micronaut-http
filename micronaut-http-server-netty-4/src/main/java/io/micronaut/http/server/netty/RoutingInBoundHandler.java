package io.micronaut.http.server.netty;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.server.netty.handler.PipeliningServerHandler;
import io.netty.channel.ChannelHandlerContext;

@Weave
public abstract class RoutingInBoundHandler {

	@Trace(dispatcher = true)
	public void accept(ChannelHandlerContext ctx, HttpRequest request, PipeliningServerHandler.OutboundAccess outboundAccess) {
		Weaver.callOriginal();
	}		
		
}
