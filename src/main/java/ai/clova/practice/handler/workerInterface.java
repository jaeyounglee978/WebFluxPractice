package ai.clova.practice.handler;

@FunctionalInterface
public interface workerInterface<T, W> {
	T addMemberToTeamInfo(T i, W w);
}
