package hu.mamunga.todo.view;

import java.awt.BorderLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import hu.mamunga.todo.controller.TaskController;
import hu.mamunga.todo.model.Task;

public class TaskListView extends JPanel {

	private List<Task> tasks;
	private JList<Object> taskList;
	private JScrollPane scrollPane;

	public TaskListView() {
		updateTaskList();
		setUpComponents();
	}

	public void updateTaskList() {
		tasks = TaskController.getInstance().getAllTasks();
		setUpComponents();
	}

	private void setUpComponents() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		taskList = new JList<>(tasks.stream().map(sc -> sc.getName()).collect(Collectors.toList()).toArray());
		taskList.getSelectionModel().addListSelectionListener(new ItemSelectedListener());
		scrollPane = new JScrollPane(taskList);
		this.add(scrollPane, BorderLayout.CENTER);
//		this.setEnabled(true);
//		this.setVisible(true);
	}

	private class ItemSelectedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			int firstIndex = e.getFirstIndex();
			int lastIndex = e.getLastIndex();

			if (e.getValueIsAdjusting()) {
				return;
			}
			if (lsm.isSelectionEmpty()) {
			} else {
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
				for (int i = minIndex; i <= maxIndex; i++) {
					if (lsm.isSelectedIndex(i)) {
						MainWindow.getInstance().showTask(tasks.get(i));
					}
				}
			}
		}

	}

}
