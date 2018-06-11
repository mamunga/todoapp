package hu.mamunga.todo.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hu.mamunga.todo.model.Status;

public class StatusController {
	private static StatusController controller;
	private static String allStatusNamesSql = "SELECT s FROM Status s";
	private static String getStatusByName = "SELECT s FROM Status s WHERE s.name = :name";
	private EntityManagerFactory factory;
	private EntityManager entityManager;

	public static StatusController getInstance() {
		if (controller == null)
			controller = new StatusController();
		return controller;
	}

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public StatusController() {
		factory = Persistence.createEntityManagerFactory("TaskDB");
		entityManager = factory.createEntityManager();
	}

	public Status getStatus(int id) {
		return entityManager.find(Status.class, id);
	}

	public Status getStatusByName(String statusName) {
		return (Status) entityManager.createQuery(getStatusByName).setParameter("name", statusName).getSingleResult();
	}

	public List<Status> getAllStatuses() {
		return entityManager.createQuery(allStatusNamesSql).getResultList();

	}

}
