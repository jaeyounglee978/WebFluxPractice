package ai.clova.practice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import ai.clova.practice.domain.BackEndWorker;

public interface BackEndWorkerRepository extends ReactiveMongoRepository<BackEndWorker, String> {

}
