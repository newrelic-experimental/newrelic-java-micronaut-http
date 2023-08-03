package io.micronaut.http.netty.reactive;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.netty.channel.ChannelHandlerContext;

@Weave
public abstract class HandlerPublisher<T> {

	@Trace(dispatcher = true)
	public void channelRead(ChannelHandlerContext ctx, Object message) {
		Weaver.callOriginal();
	}
}
