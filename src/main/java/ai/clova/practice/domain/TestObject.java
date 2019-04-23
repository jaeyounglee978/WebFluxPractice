package ai.clova.practice.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class TestObject {
	private long id;
	private String name;

	public TestObject(long id, String name) {
		this.id = id;
		this.name = name;
	}
}
