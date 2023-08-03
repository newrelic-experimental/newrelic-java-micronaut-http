package io.micronaut.web.router;

import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface, originalName = "io.micronaut.web.router.RouteMatch")
public abstract class RouteMatch_instrumentation<R> {

	@Trace
	public R execute(Map<String, Object> argumentValues) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Micronaut","Netty","RouteMatch",getClass().getSimpleName(),"execute");
		return Weaver.callOriginal();
	}
}
