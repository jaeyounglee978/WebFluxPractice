package ai.clova.practice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import ai.clova.practice.domain.Worker;
import reactor.core.publisher.Flux;

@Repository
public interface WorkerRepository extends ReactiveMongoRepository<Worker, String> {
	Flux<Worker> findAll();
}
