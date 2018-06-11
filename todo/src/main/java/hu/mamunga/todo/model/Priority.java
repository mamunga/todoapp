package hu.mamunga.todo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Priority {
	@Id
	private int id;
	private String name;

	public Priority(String p_name) {
		name = p_name;
	}

	public Priority() {

	}

}
