package ai.clova.practice.handler;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.clova.practice.domain.BackEndWorker;
import ai.clova.practice.domain.FrontEndWorker;
import ai.clova.practice.domain.TestObject;
import ai.clova.practice.repository.BackEndWorkerRepository;
import ai.clova.practice.repository.FrontEndWorkerRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/annotation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AnnotationHandler {

	private final BackEndWorkerRepository backEndWorkerRepository;
	private final FrontEndWorkerRepository frontEndWorkerRepository;

	@GetMapping("/1")
	public Mono<String> handleDefaultRequest() {
		return Mono.just("Hello");
	}

	@GetMapping("/flux")
	public Flux<String> fluxReturn() {
		return Flux.just("1", "1", "1", "1", "1", "1");
	}

	@GetMapping("/{pathVar}")
	public Mono<String> handleMoreRequest(@PathVariable String pathVar) {
		return Mono.just(pathVar);
	}

	@GetMapping("/json")
	public Mono<TestObject> getjson() {
		Mono<TestObject> testObjectMono = Mono.just(new TestObject(300L, "Tony"));
		return testObjectMono;
	}

	@GetMapping("/addB")
	public Mono<BackEndWorker> addBackEndWorker() {
		BackEndWorker newWorker = new BackEndWorker();
		return backEndWorkerRepository.insert(newWorker);
	}

	@GetMapping("/addF")
	public Mono<FrontEndWorker> addFrontEndWorker() {
		FrontEndWorker newWorker = new FrontEndWorker();
		return frontEndWorkerRepository.insert(newWorker);
	}
}
