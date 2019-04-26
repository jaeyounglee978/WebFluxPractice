package ai.clova.practice.handler;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

@Slf4j
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

	@GetMapping("/interval")
	public Flux<Integer> asdf() {
		return Flux.range(0, 20).map(i -> {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				return i;
			}
			System.out.println(i);
			return i * 2;
		});
	}

	@GetMapping("/addB")
	public Flux<BackEndWorker> addBackEndWorker() {
		Flux flux = Flux.range(0, 300)
						.map(i -> {
							BackEndWorker newWorker = new BackEndWorker();
							return newWorker;
						});

		return backEndWorkerRepository.insert(flux);
	}

	@GetMapping("/addF")
	public ParallelFlux<Integer> addFrontEndWorker() {
		log.info("main : " + Thread.currentThread().getId());
		log.info("main : " + Runtime.getRuntime().availableProcessors());
		return Flux.range(0, 30).parallel(8)
				   .map(i -> {
					   try {
						   Thread.sleep((long)ThreadLocalRandom.current().nextFloat() * 30);
					   } catch (Exception e) {
						   return i;
					   }
					   log.info("map : " + Thread.currentThread().getId());
					   return i;
				   });
	}

	@GetMapping("/addB-wrong")
	public Mono<List<Mono<BackEndWorker>>> addBackEndWorkerWrong() {
		return Flux.range(0, 300)
				   .map(i -> {
					   BackEndWorker newWorker = new BackEndWorker();
					   return backEndWorkerRepository.insert(newWorker);
				   }).collectList();
	}

	@GetMapping("/addF-wrong")
	public Flux<FrontEndWorker> addFrontEndWorkerWrong() {
		return Flux.range(0, 300)
				   .map(i -> {
					   FrontEndWorker newWorker = new FrontEndWorker();
					   frontEndWorkerRepository.insert(newWorker);
					   return newWorker;
				   });
	}
}
