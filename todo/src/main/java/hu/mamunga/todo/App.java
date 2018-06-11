package hu.mamunga.todo;

import javax.swing.JFrame;

import hu.mamunga.todo.controller.TaskController;
import hu.mamunga.todo.model.Task;
import hu.mamunga.todo.view.MainWindow;

public class App {
	public static void main(String[] args) {
		MainWindow.getInstance().setEnabled(true);
		MainWindow.getInstance().setVisible(true);
	}
}
