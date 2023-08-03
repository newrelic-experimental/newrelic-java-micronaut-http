package io.micronaut.http.filter;

import org.reactivestreams.Publisher;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;

@Weave(type = MatchType.Interface)
public abstract class HttpServerFilter {

	@Trace(dispatcher = true)
	public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Micronaut","HttpServerFilter",getClass().getSimpleName(),"doFilter");
		Transaction transaction = NewRelic.getAgent().getTransaction();
		if(!transaction.isWebTransaction()) {
			transaction.convertToWebTransaction();
		}
		return Weaver.callOriginal();
	}
	
}
