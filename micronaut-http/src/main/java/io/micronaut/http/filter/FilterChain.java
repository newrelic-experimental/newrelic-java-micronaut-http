package io.micronaut.http.filter;

import org.reactivestreams.Publisher;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;

@Weave(type = MatchType.Interface)
public abstract class FilterChain {

	@Trace
	public Publisher<? extends HttpResponse<?>> proceed(HttpRequest<?> request) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Micronaut","FilterChain",getClass().getSimpleName(),"proceed");
		return Weaver.callOriginal();
	}
}
