package io.micronaut.http.netty.stream;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpMessage;

@Weave(type = MatchType.BaseClass)
abstract class HttpStreamsHandler<In extends HttpMessage, Out extends HttpMessage> {

	@Trace(dispatcher = true)
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","HttpStreamsHandler",getClass().getSimpleName(),"channelRead");
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	protected void consumedInMessage(ChannelHandlerContext ctx) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","HttpStreamsHandler",getClass().getSimpleName(),"consumedInMessage");
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	protected void receivedInMessage(ChannelHandlerContext ctx) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","HttpStreamsHandler",getClass().getSimpleName(),"receivedInMessage");
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	protected void receivedOutMessage(ChannelHandlerContext ctx) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","HttpStreamsHandler",getClass().getSimpleName(),"receivedOutMessage");
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public void write(final ChannelHandlerContext ctx, Object msg, final ChannelPromise promise) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","HttpStreamsHandler",getClass().getSimpleName(),"write");
		Weaver.callOriginal();
	}
}
