package io.micronaut.http.server.netty;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;

import io.netty.buffer.ByteBufHolder;

@Weave(type=MatchType.BaseClass)
public abstract class AbstractHttpContentProcessor<T> {

	@Trace(dispatcher = true)
	protected abstract void onData(ByteBufHolder message);
}
