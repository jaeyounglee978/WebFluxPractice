package ai.clova.practice.domain;

import lombok.Getter;

@Getter
public class TeamInfo {
	int ageSum;
	int memberCount;
	float laborTimeSum;

	public TeamInfo() {
		this.ageSum = 0;
		this.laborTimeSum = 0.0f;
		this.memberCount = 0;
	}

	public TeamInfo(int i, float f, int c) {
		this.ageSum = i;
		this.laborTimeSum = f;
		this.memberCount = c;
	}

	public TeamInfo addWorker(Worker w) {

		this.ageSum += w.getAge();
		this.laborTimeSum += w.getLaborTime();
		this.memberCount += 1;
		return this;
	}

	public void addAge(int i) {
		this.ageSum += i;
	}

	public void addLaborTime(float i) {
		this.laborTimeSum += i;
	}

	public void addMember() {
	}

	public float getAverageLaborTime() {
		return laborTimeSum / memberCount;
	}

	public float getAverageAge() {
		return (float)ageSum / memberCount;
	}
}