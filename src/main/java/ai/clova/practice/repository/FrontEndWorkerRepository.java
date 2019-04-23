package ai.clova.practice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import ai.clova.practice.domain.FrontEndWorker;

public interface FrontEndWorkerRepository extends ReactiveMongoRepository<FrontEndWorker, String> {
}
