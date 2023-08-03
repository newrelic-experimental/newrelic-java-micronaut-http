package com.newrelic.instrumentation.micronaut.http.client;

import org.reactivestreams.Subscription;

import com.newrelic.api.agent.HttpParameters;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Transaction;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class ReactorListener implements Action, Consumer<Subscription> {
	
	private Segment segment = null;
	private Transaction txn = null;
	private HttpParameters params = null;
	
	public ReactorListener(Transaction t, HttpParameters p) {
		txn = t;
		params = p;
	}

	@Override
	public void run() {
		if(segment != null) {
			segment.end();
		}
	}

	@Override
	public void accept(Subscription t) {
		if(txn != null && segment == null) {
			if(params != null) {
				String proc = params.getProcedure();
				segment = txn.startSegment("MicronautClient/"+proc);
				segment.reportAsExternal(params);
			}
		}
	}


}
