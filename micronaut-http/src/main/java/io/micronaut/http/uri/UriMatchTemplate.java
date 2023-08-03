package io.micronaut.http.uri;

import java.util.Optional;
import java.util.regex.Pattern;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class UriMatchTemplate {

	
	private final Pattern matchPattern = Weaver.callOriginal();
	
	public Optional<UriMatchInfo> match(String uri) {
		
		Optional<UriMatchInfo> result = Weaver.callOriginal();
		if(result.isPresent() && matchPattern != null) {
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, false, "Routing", matchPattern.pattern());
		}
		return result;
	}
}
