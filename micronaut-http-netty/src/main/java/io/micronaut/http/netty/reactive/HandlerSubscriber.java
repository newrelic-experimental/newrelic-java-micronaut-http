package io.micronaut.http.netty.reactive;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class HandlerSubscriber<T> {

	@Trace(dispatcher = true)
	public void onComplete() {
		Weaver.callOriginal();
	}
	
	public void onError(final Throwable error) {
		NewRelic.noticeError(error);
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	 public void onNext(T t) {
		 Weaver.callOriginal();
	 }
}
