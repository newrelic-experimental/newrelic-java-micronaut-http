package io.micronaut.http.client.netty;

import org.reactivestreams.Publisher;

import com.newrelic.api.agent.HttpParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.micronaut.http.client3.MicronautHeaders;
import com.newrelic.instrumentation.micronaut.http.client3.MicronautHttpOutbound;
import com.newrelic.instrumentation.micronaut.http.client3.ReactorListener;
import com.newrelic.instrumentation.micronaut.http.client3.ResponseConsumer;
import com.newrelic.instrumentation.micronaut.http.client3.Utils;

import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.core.type.Argument;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.sse.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Weave
public abstract class DefaultHttpClient {

	@Trace(dispatcher = true)
	private <I> Publisher<Event<ByteBuffer<?>>> eventStreamOrError(io.micronaut.http.HttpRequest<I> request, Argument<?> errorType) {
		return Weaver.callOriginal();
	}
	
	
	public <I, O, E> Publisher<io.micronaut.http.HttpResponse<O>> exchange(io.micronaut.http.HttpRequest<I> request, Argument<O> bodyType, Argument<E> errorType) {
		MicronautHttpOutbound<I> wrapper = new MicronautHttpOutbound<I>(request);
		NewRelic.getAgent().getTracedMethod().addOutboundRequestHeaders(wrapper);
		
		Publisher<io.micronaut.http.HttpResponse<O>> result =  Weaver.callOriginal();
		boolean isFlux = Utils.isFlux(result);
		boolean isMono = Utils.isMono(result);
		if(isFlux || isMono) {
			HttpParameters params = HttpParameters.library("Micronaut").uri(Utils.getRequestURI(request)).procedure(request.getMethodName()).noInboundHeaders().build();
			Transaction txn = NewRelic.getAgent().getTransaction();
			ReactorListener listener = new ReactorListener(txn, params);
			ResponseConsumer respConsumer = new ResponseConsumer(txn);
			if(result instanceof Mono) {
				Mono<io.micronaut.http.HttpResponse<O>> mono = (Mono<io.micronaut.http.HttpResponse<O>>)result;
				result = mono.doOnSubscribe(listener).doOnCancel(listener).doOnSuccess(respConsumer).doOnTerminate(listener);
			} else if(result instanceof Flux) {
				Flux<io.micronaut.http.HttpResponse<O>> flux = (Flux<io.micronaut.http.HttpResponse<O>>)result;
				result = flux.doOnSubscribe(listener).doOnCancel(listener).doOnNext(respConsumer).doOnTerminate(listener);
			}
			
		}
		return result;
	}
	
	public <I> Publisher<io.micronaut.http.HttpResponse<ByteBuffer<?>>> exchangeStream(io.micronaut.http.HttpRequest<I> request, Argument<?> errorType) {
		MicronautHttpOutbound<I> wrapper = new MicronautHttpOutbound<I>(request);
		NewRelic.getAgent().getTracedMethod().addOutboundRequestHeaders(wrapper);
		
		Publisher<io.micronaut.http.HttpResponse<ByteBuffer<?>>> result =  Weaver.callOriginal();
		boolean isFlux = Utils.isFlux(result);
		boolean isMono = Utils.isMono(result);
		if(isFlux || isMono) {
			HttpParameters params = HttpParameters.library("Micronaut").uri(Utils.getRequestURI(request)).procedure(request.getMethodName()).noInboundHeaders().build();
			Transaction txn = NewRelic.getAgent().getTransaction();
			ReactorListener listener = new ReactorListener(txn, params);
			ResponseConsumer respConsumer = new ResponseConsumer(txn);
			if(result instanceof Mono) {
				Mono<io.micronaut.http.HttpResponse<ByteBuffer<?>>> mono = (Mono<io.micronaut.http.HttpResponse<ByteBuffer<?>>>)result;
				result = mono.doOnSubscribe(listener).doOnCancel(listener).doOnSuccess(respConsumer).doOnTerminate(listener);
			} else if(result instanceof Flux) {
				Flux<io.micronaut.http.HttpResponse<ByteBuffer<?>>> flux = (Flux<io.micronaut.http.HttpResponse<ByteBuffer<?>>>)result;
				result = flux.doOnSubscribe(listener).doOnCancel(listener).doOnNext(respConsumer).doOnTerminate(listener);
			}
			
		}
		return result;
	}
	
	@Trace(dispatcher = true)
	public <I, O> Publisher<O> jsonStream(io.micronaut.http.HttpRequest<I> request, Argument<O> type, Argument<?> errorType) {
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public Publisher<MutableHttpResponse<?>> proxy(io.micronaut.http.HttpRequest<?> request) {
		MicronautHeaders headers = new MicronautHeaders(request);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(headers);
		
		Publisher<MutableHttpResponse<?>> result =  Weaver.callOriginal();
		boolean isFlux = Utils.isFlux(result);
		boolean isMono = Utils.isMono(result);
		if(isFlux || isMono) {
			HttpParameters params = HttpParameters.library("Micronaut").uri(Utils.getRequestURI(request)).procedure(request.getMethodName()).noInboundHeaders().build();
			Transaction txn = NewRelic.getAgent().getTransaction();
			ReactorListener listener = new ReactorListener(txn, params);
			ResponseConsumer respConsumer = new ResponseConsumer(txn);
			if(result instanceof Mono) {
				Mono<MutableHttpResponse<?>> mono = (Mono<MutableHttpResponse<?>>)result;
				result = mono.doOnSubscribe(listener).doOnCancel(listener).doOnSuccess(respConsumer).doOnTerminate(listener);
			} else if(result instanceof Flux) {
				Flux<MutableHttpResponse<?>> flux = (Flux<MutableHttpResponse<?>>)result;
				result = flux.doOnSubscribe(listener).doOnCancel(listener).doOnNext(respConsumer).doOnTerminate(listener);
			}
			
		}
		return result;
	}
	
}
