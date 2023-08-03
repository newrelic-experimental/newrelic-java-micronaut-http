package com.newrelic.instrumentation.micronaut.http.server;

import com.newrelic.api.agent.Token;

public class NRHolder {
	
	private Token token = null;
	
	public NRHolder(Token t) {
		token = t;
	}

	public void link() {
		token.link();
	}
	
	public void linkAndExpire() {
		token.linkAndExpire();
		token = null;
	}
	
	public void expire() {
		token.expire();
		token = null;
	}
	
	public void delete() {
		if(token != null) {
			token.expire();
			token = null;
		}
	}
}
