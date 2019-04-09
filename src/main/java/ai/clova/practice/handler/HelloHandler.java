package ai.clova.practice.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ai.clova.practice.domain.TestObject;
import ai.clova.practice.domain.Worker;
import ai.clova.practice.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HelloHandler {

	private final WorkerRepository workerRepository;

	public Mono<ServerResponse> handleDefaultRequest(ServerRequest request) {
		return ServerResponse.ok().body(Mono.just("Hello world!"), String.class);
	}

	public Mono<ServerResponse> handleMoreRequest(ServerRequest request) {
		Mono<TestObject> testObjectMono = Mono.just(new TestObject(300L, "Tony"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(testObjectMono, TestObject.class);
	}

	public Mono<ServerResponse> handleAddingWorkers(ServerRequest request) {
		List<Worker> workers = new ArrayList<>();
		workers.add(new Worker("aaa", "KR00001", 25));
		workers.add(new Worker("aab", "KR00002", 30));
		workers.add(new Worker("aac", "KR00003", 50));
		workers.add(new Worker("aad", "KR00004", 18));
		workers.add(new Worker("aae", "KR00005", 23));

		Stream<Worker> streamForDb = workers.stream();

		Stream<Worker> stream = streamForDb.map(worker -> {
			workerRepository.save(worker);
			return worker;
		});

		Flux<Worker> workerFlux = Flux.fromStream(stream);

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(workerFlux, Worker.class);
	}
}
