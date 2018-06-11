package hu.mamunga.todo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import hu.mamunga.todo.model.Task;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -6639602810248017563L;
	private JPanel leftPanel;
	private JPanel mainArea;
	private static MainWindow mainWindow;

	public static MainWindow getInstance() {
		if (mainWindow == null)
			mainWindow = new MainWindow();
		return mainWindow;
	}

	public MainWindow() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		setUpComponents();
	}

	private void setUpComponents() {
		this.setTitle("Task Manager");
		this.setMinimumSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setUpMenuBar();

		leftPanel = new TaskListView();
		mainArea = new TaskView();
		mainArea.setLayout(new BorderLayout());
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, mainArea);
		this.add(splitPane, BorderLayout.CENTER);
		Dimension minimumSize = new Dimension(200, 100);
		leftPanel.setMinimumSize(minimumSize);
		pack();

	}

	private void setUpMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");

		JMenuItem newTaskMenu = new JMenuItem(new AbstractAction("New Task") {
			public void actionPerformed(ActionEvent ae) {
				showTask(new Task());
			}
		});
		fileMenu.add(newTaskMenu);

		JMenuItem exitMenu = new JMenuItem(new AbstractAction("Exit") {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		fileMenu.add(exitMenu);

		menuBar.add(fileMenu);

		this.setJMenuBar(menuBar);
	}

	public void showTask(Task currentTask) {
		mainArea.removeAll();
		mainArea.add(new TaskView(currentTask));
		revalidate();
	}

	public void refresh() {
		((TaskListView) leftPanel).updateTaskList();
		leftPanel.revalidate();
	}
}
