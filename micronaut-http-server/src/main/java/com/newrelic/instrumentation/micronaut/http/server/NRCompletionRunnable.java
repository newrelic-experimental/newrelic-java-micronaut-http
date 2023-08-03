package com.newrelic.instrumentation.micronaut.http.server;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Trace;

/**
 * Used to link onComplete and onCancel to original transaction and expire the token
 * 
 * @author dhilpipre
 *
 */
public class NRCompletionRunnable implements Runnable {

	private static boolean isTransformed = false;
	private NRHolder holder = null;
	
	public NRCompletionRunnable(NRHolder h) {
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
		}
		holder = h;
	}
	
	@Override
	@Trace(async = true)
	public void run() {
		if(holder != null) {
			holder.linkAndExpire();
		}
	}

}
