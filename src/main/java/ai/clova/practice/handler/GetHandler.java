package ai.clova.practice.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ai.clova.practice.domain.TeamInfo;
import ai.clova.practice.repository.BackEndWorkerRepository;
import ai.clova.practice.repository.FrontEndWorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetHandler {

	private final BackEndWorkerRepository frontendWorkerRepository;
	private final FrontEndWorkerRepository backendWorkerRepository;

	public Mono<ServerResponse> getLaborTimeDiff(ServerRequest request) {

		log.info("get data");

		TeamInfo frontTeamInfo = new TeamInfo();
		Mono<TeamInfo> frontendTeamInfo = frontendWorkerRepository.findAll()
																  .reduce(frontTeamInfo,
																		  (teamInfo, worker) -> teamInfo.addWorker(worker));
		//accumulateWorker.addMemberToTeamInfo(teamInfo, worker));

		TeamInfo backTeamInfo = new TeamInfo();
		Mono<TeamInfo> backendTeamInfo = backendWorkerRepository.findAll()
																.reduce(backTeamInfo,
																		(teamInfo, worker) -> teamInfo.addWorker(worker));
		//accumulateWorker.addMemberToTeamInfo(teamInfo, worker));

		Flux result = Flux.zip(frontendTeamInfo, backendTeamInfo);

		return ServerResponse.ok().body(result, TeamInfo.class);
	}
}

