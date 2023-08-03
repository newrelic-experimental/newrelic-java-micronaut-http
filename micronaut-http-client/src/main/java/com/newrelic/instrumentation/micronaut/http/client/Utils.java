package com.newrelic.instrumentation.micronaut.http.client;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;

import com.newrelic.agent.deps.org.apache.http.client.utils.URIBuilder;
import com.newrelic.api.agent.NewRelic;

import io.micronaut.http.HttpRequest;

public class Utils {

	
	public static URI getRequestURI(HttpRequest<?> request) {
		URIBuilder builder = new URIBuilder();
		
		
		InetSocketAddress serverAddress = request.getServerAddress();

		URI reqURI = request.getUri();

		
		String schema = reqURI != null ? reqURI.getScheme() : null;
		if(schema == null) schema = "http"; 
		
		
		URI uri = null;
		
		try {
			uri = builder.setHost(serverAddress.getHostName())
					.setScheme(schema)
					.setPort(serverAddress.getPort())
					.setPath(request.getPath()).build();
		} catch (URISyntaxException e) {
			NewRelic.getAgent().getLogger().log(Level.FINEST, e, "Error getting URI");
		}
		
		if(uri == null) uri = URI.create(schema+"://UnknownHost");
		
		
		return uri;
	}
}
