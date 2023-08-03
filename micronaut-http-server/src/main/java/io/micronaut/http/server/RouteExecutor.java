package io.micronaut.http.server;

import java.util.concurrent.ExecutorService;

import org.reactivestreams.Publisher;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.micronaut.http.server.NRCompletionRunnable;
import com.newrelic.instrumentation.micronaut.http.server.NRHolder;

import reactor.core.publisher.Flux;

@Weave
public abstract class RouteExecutor {
	
	 private <T> Flux<T> applyExecutorToPublisher(Publisher<T> publisher, ExecutorService executor) { 
		 Flux<T> result = Weaver.callOriginal();
		 Token t = NewRelic.getAgent().getTransaction().getToken();
		 if(t != null && t.isActive()) {
			 NRHolder holder = new NRHolder(t);
			 NRCompletionRunnable doneHandler = new NRCompletionRunnable(holder);
			 
		 } else if(t != null) {
			 t.expire();
			 t = null;
		 }
		 return result;
	 }

}
