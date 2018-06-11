package hu.mamunga.todo.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hu.mamunga.todo.model.Task;

public class TaskController {
	private static TaskController controller;
	private static String allTasksSql = "SELECT t FROM Task t";

	private EntityManagerFactory factory;
	private EntityManager entityManager;

	public static TaskController getInstance() {
		if (controller == null)
			controller = new TaskController();
		return controller;
	}

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public TaskController() {
		factory = Persistence.createEntityManagerFactory("TaskDB");
		entityManager = factory.createEntityManager();
	}

	public Task getTaskById(int i) {
		return entityManager.find(Task.class, i);
	}

	public void updateTask(Task task) {
		entityManager.getTransaction().begin();
		entityManager.merge(task);
		entityManager.getTransaction().commit();
	}

	public void creatTask(Task task) {
		entityManager.getTransaction().begin();
		entityManager.persist(task);
		entityManager.getTransaction().commit();

	}

	public void createOrUpdateTask(Task task) {
		if (getTaskById(task.getId()) == null)
			creatTask(task);
		else
			updateTask(task);
	}

	public List<Task> getAllTasks() {
		return entityManager.createQuery(allTasksSql).getResultList();

	}

}
