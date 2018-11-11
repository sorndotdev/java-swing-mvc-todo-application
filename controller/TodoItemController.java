package todo.controller;

import todo.model.TodoItemModel;
import todo.view.TodoItemView;
import java.awt.event.WindowAdapter;

public final class TodoItemController {
    private final TodoItemView view;
    private final TodoItemModel model;

    public TodoItemController(TodoItemView view, TodoItemModel model) {
        this.view = view;
        this.model = model;
        this.initController();
    }

    private void initController() {
        view.getSaveButton().addActionListener(e -> saveTodo());
        view.getCancelButton().addActionListener(e -> closeDialog());
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closeDialog();
            }
        });
    }

    private void saveTodo() {
        String title = view.getTitleField().getText().trim();
        String description = view.getDescriptionArea().getText();
        boolean completed = view.getCompletedCheckbox().isSelected();

        model.setTitle(title);
        model.setDescription(description);
        model.setCompleted(completed);

        closeDialog();
    }

    private void closeDialog() {
        view.setVisible(false);
        view.dispose();
    }
}