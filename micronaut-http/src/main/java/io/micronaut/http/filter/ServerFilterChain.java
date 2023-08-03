package io.micronaut.http.filter;

import org.reactivestreams.Publisher;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;

@Weave(type = MatchType.Interface)
public abstract class ServerFilterChain {

	@Trace
	public Publisher<MutableHttpResponse<?>> proceed(HttpRequest<?> request) {
		return Weaver.callOriginal();
	}
}
