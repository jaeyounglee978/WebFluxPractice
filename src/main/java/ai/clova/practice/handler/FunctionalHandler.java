package ai.clova.practice.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ai.clova.practice.domain.TestObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class FunctionalHandler {

	public Mono<ServerResponse> handleDefaultRequest(ServerRequest request) {
		return ServerResponse.ok().body(Mono.just("Hello world!"), String.class);
	}

	public Mono<ServerResponse> handleMoreRequest(ServerRequest request) {
		String pathVariable = String.valueOf(request.pathVariable("pathVar"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(pathVariable), String.class);
	}

	public Mono<ServerResponse> flux(ServerRequest request) {
		Flux<String> stringFlux = Flux.just("1", "1", "1", "1", "1", "1");

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(stringFlux, String.class);
	}

	public Mono<ServerResponse> getJson(ServerRequest request) {
		Mono<TestObject> testObjectMono = Mono.just(new TestObject(300L, "Tony"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(testObjectMono, TestObject.class);
	}
}
