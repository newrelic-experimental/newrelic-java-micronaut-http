package io.micronaut.http.server.netty;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.micronaut.netty.Utils;

import io.micronaut.web.router.RouteMatch;
import io.netty.channel.ChannelHandlerContext;

@Weave
abstract class RoutingInBoundHandler {

	@Trace
	protected void channelRead0(ChannelHandlerContext ctx, io.micronaut.http.HttpRequest<?> request) {
		Weaver.callOriginal();
	}		
		

	
	@SuppressWarnings("unused")
	private void handleRouteMatch(RouteMatch<?> route, NettyHttpRequest<?> request,ChannelHandlerContext context, boolean skipOncePerRequest) {
		Utils.decorateWithRoute(route);
		Weaver.callOriginal();
	}
		
}
