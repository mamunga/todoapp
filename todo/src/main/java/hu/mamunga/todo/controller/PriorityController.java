package hu.mamunga.todo.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hu.mamunga.todo.model.Priority;

public class PriorityController {

	private static PriorityController controller;
	private static String allPrioritiesSql = "SELECT p FROM Priority p";
	private static String getPriorityByName = "SELECT p FROM Priority p WHERE p.name = :name";

	private EntityManagerFactory factory;
	private EntityManager entityManager;

	public static PriorityController getInstance() {
		if (controller == null)
			controller = new PriorityController();
		return controller;
	}

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public PriorityController() {
		factory = Persistence.createEntityManagerFactory("TaskDB");
		entityManager = factory.createEntityManager();
	}

	public Priority getPriority(int id) {
		return entityManager.find(Priority.class, id);
	}

	public Priority getPriorityByName(String priorityName) {
		return (Priority) entityManager.createQuery(getPriorityByName).setParameter("name", priorityName).getSingleResult();
	}

	public List<Priority> getAllPriorities() {
		return entityManager.createQuery(allPrioritiesSql).getResultList();

	}

}
