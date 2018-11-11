package todo.model;

public final class TodoItemModel extends Model {
    private String title;
    private String description;
    private boolean completed;

    public TodoItemModel(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        String oldValue = this.title;
        this.title = title;
        pcs.firePropertyChange("title", oldValue, title);
    }

    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        pcs.firePropertyChange("description", oldValue, description);
    }

    public void setCompleted(boolean completed) {
        boolean oldValue = this.completed;
        this.completed = completed;
        pcs.firePropertyChange("completed", oldValue, completed);
    }
}