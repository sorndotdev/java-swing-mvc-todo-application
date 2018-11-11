package todo.model;

import java.util.ArrayList;
import java.util.List;

public final class TodoListModel extends Model {
    private final List<TodoItemModel> todos;
    private TodoItemModel selectedTodo;

    public TodoListModel() {
        this.todos = new ArrayList<>();
    }

    public TodoItemModel[] getTodos() {
        return todos.toArray(TodoItemModel[]::new);
    }

    public TodoItemModel getSelectedTodo() {
        return selectedTodo;
    }

    public void selectTodo(TodoItemModel selectedTodo) {
        TodoItemModel oldValue = this.selectedTodo;
        this.selectedTodo = selectedTodo;
        pcs.firePropertyChange("selectedTodo", oldValue, selectedTodo);
    }

    public void addTodo(TodoItemModel todo) {
        todos.add(todo);
        pcs.firePropertyChange("todos", null, todos);
        todo.addListener(event -> pcs.firePropertyChange("todos", null, todos));
    }

    public void removeTodo(TodoItemModel todo) {
        if (todos.remove(todo)) {
            pcs.firePropertyChange("todos", null, todos);
        }
    }
}