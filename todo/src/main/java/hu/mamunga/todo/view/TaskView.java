package hu.mamunga.todo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import hu.mamunga.todo.controller.PriorityController;
import hu.mamunga.todo.controller.StatusController;
import hu.mamunga.todo.controller.TaskController;
import hu.mamunga.todo.model.Priority;
import hu.mamunga.todo.model.Status;
import hu.mamunga.todo.model.Task;

public class TaskView extends JPanel {

	private static final long serialVersionUID = 214275989867731223L;
	private Task task;

	public void setTask(Task task) {
		this.task = task;
	}

	private JTextField nameField;
	private JTextArea descriptionField;
	private JComboBox priorityField;
	private JCheckBox doneField;
	private JComboBox statusField;

	public TaskView() {
		task = new Task();
		setUpComponents();
	}

	public TaskView(Task currentTask) {
		task = currentTask;
		setUpComponents();
	}

	private void setUpComponents() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);

		JLabel nameLabel = new JLabel("Task name");
		nameField = new JTextField(task.getName());
		nameField.setMaximumSize(new Dimension(1000, 1000));
		addFields(nameLabel, nameField);

		JLabel priorityLabel = new JLabel("Priority");
		List<Priority> allPriorities = PriorityController.getInstance().getAllPriorities();
		priorityField = new JComboBox(
				allPriorities.stream().map(sc -> sc.getName()).collect(Collectors.toList()).toArray());
		priorityField.setSelectedItem(priorityField.getItemAt(allPriorities.indexOf(task.getPriority())));
		priorityField.setMaximumSize(new Dimension(1000, 1000));
		addFields(priorityLabel, priorityField);

		JLabel statusLabel = new JLabel("Priority");
		List<Status> allStatuses = StatusController.getInstance().getAllStatuses();
		statusField = new JComboBox(
				allStatuses.stream().map(sc -> sc.getName()).collect(Collectors.toList()).toArray());
		statusField.setSelectedItem(statusField.getItemAt(allStatuses.indexOf(task.getStatus())));
		statusField.setMaximumSize(new Dimension(1000, 1000));
		addFields(statusLabel, statusField);

		JLabel descriptionLabel = new JLabel("Description");
		descriptionField = new JTextArea(task.getDescription());
		addFields(descriptionLabel, descriptionField);

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveButtonListener());
		JButton startTimerButton = new JButton("Start timer");
		this.add(saveButton);
		this.add(startTimerButton);

		SpringUtilities.makeCompactGrid(this, this.getComponentCount() / 2, 2, 6, 6, 6, 6);

		this.setVisible(true);
		this.setEnabled(true);
	}

	private void addFields(JLabel label, JComponent fieldValue) {
		this.add(label);
		label.setLabelFor(fieldValue);
		this.add(fieldValue);
	}

	private class SaveButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			task.setName(nameField.getText());
			task.setDescription(descriptionField.getText());
			task.setPriority(PriorityController.getInstance().getPriorityByName((String) priorityField.getSelectedItem()));
			TaskController.getInstance().updateTask(task);
			MainWindow.getInstance().refresh();
		}

	}

}
