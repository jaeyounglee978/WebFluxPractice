package ai.clova.practice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Worker {
	@Id
	private String id;

	private String name;
	private String officeId;
	private int age;

	public Worker(String name, String officeId, int age) {
		this.name = name;
		this.officeId = officeId;
		this.age = age;
	}
}
