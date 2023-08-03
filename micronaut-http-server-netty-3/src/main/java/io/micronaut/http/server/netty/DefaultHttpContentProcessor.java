package io.micronaut.http.server.netty;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.netty.buffer.ByteBufHolder;

@Weave
public abstract class DefaultHttpContentProcessor {

	@Trace(dispatcher = true)
	protected void onUpstreamMessage(ByteBufHolder message) {
			Weaver.callOriginal();
	}
}
