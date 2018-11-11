package todo.controller;

import todo.model.TodoItemModel;
import todo.model.TodoListModel;
import todo.view.TodoItemView;
import todo.view.TodoListView;

public final class TodoListController {
    private final TodoListView view;
    private final TodoListModel model;

    public TodoListController(TodoListView view, TodoListModel model) {
        this.view = view;
        this.model = model;
        this.initController();
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> addNewTodo());
        view.getDetailsButton().addActionListener(e -> showTodoDetails());
        view.getRemoveButton().addActionListener(e -> removeSelectedTodo());
        view.getList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                TodoItemModel selected = view.getSelectedTodo();
                model.selectTodo(selected);
            }
        });
    }

    private void addNewTodo() {
        TodoItemModel newTodo = new TodoItemModel("New Todo", "");
        model.addTodo(newTodo);
        model.selectTodo(newTodo);
    }

    private void showTodoDetails() {
        TodoItemModel selected = view.getSelectedTodo();
        if (selected != null) {
            TodoItemView editorView = new TodoItemView(selected);
            new TodoItemController(editorView, selected);
        } else {
            view.showWarning("Please select a todo first");
        }
    }

    private void removeSelectedTodo() {
        TodoItemModel selected = model.getSelectedTodo();
        if (selected != null) {
            boolean confirm = view.showConfirmDialog("Are you sure you want to remove: " + selected.getTitle() + "?");
            if (confirm) {
                model.removeTodo(selected);
                model.selectTodo(null);
            }
        } else {
            view.showWarning("Please select a todo to remove");
        }
    }
}