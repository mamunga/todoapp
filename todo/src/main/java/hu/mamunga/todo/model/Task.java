package hu.mamunga.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import hu.mamunga.todo.controller.PriorityController;
import hu.mamunga.todo.controller.StatusController;
import lombok.Data;

@Data
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	@ManyToOne
	private Priority priority;

	@ManyToOne
	private Status status;

	public Task() {
		setUpDefaultValues();
	}
	
	public Task(int p_id) {
		id = p_id;
		setUpDefaultValues();
	}

	private void setUpDefaultValues() {
		priority = PriorityController.getInstance().getPriorityByName("Normal");
		status = StatusController.getInstance().getStatusByName("To do");
	}

}
