package hu.mamunga.todo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Status {
	@Id
	private int id;
	private String name;

	public Status(String n) {
		name = n;
	}

	public Status() {
		name = "To do";
	}
}
