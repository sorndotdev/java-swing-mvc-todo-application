package todo;

import todo.controller.TodoListController;
import todo.model.TodoListModel;
import todo.view.TodoListView;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class TodoApp {
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            TodoListModel model = new TodoListModel();
            TodoListView listView = new TodoListView(model);

            new TodoListController(listView, model);

            JFrame mainFrame = new JFrame("To-Do List");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(480, 640);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.add(listView);
            mainFrame.setVisible(true);
        });
    }
}