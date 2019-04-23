package ai.clova.practice.domain;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Worker {
	@Id
	private String id;

	private String name;
	private String officeId;
	private int age;

	private float laborTime;

	public Worker() {
		this.name = "defaultName";
		this.officeId = "defaultId";
		this.age = 20 + ThreadLocalRandom.current().nextInt(10);
		this.laborTime = ThreadLocalRandom.current().nextFloat() + 7.0f;
	}

	public Worker(String name, String officeId, int age, float laborTime) {
		this.name = name;
		this.officeId = officeId;
		this.age = age;
		this.laborTime = laborTime;
	}
}
