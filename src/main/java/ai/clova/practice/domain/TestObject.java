package ai.clova.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TestObject {
	private long id;
	private String name;

	public TestObject(long id, String name) {
		this.id = id;
		this.name = name;
	}
}
