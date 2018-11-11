package todo.view;

import todo.model.TodoItemModel;
import todo.model.TodoListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public final class TodoListView extends JPanel implements PropertyChangeListener {
    private final TodoListModel model;
    private final JList<TodoItemModel> list;
    private final JButton addButton;
    private final JButton detailsButton;
    private final JButton removeButton;

    public TodoListView(TodoListModel model) {
        this.model = model;
        this.list = new JList<>(model.getTodos());
        this.list.setCellRenderer(new TodoListCellRenderer());
        this.addButton = new JButton("Add Todo");
        this.detailsButton = new JButton("Details");
        this.removeButton = new JButton("Remove");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(detailsButton);
        buttonPanel.add(removeButton);

        super.setLayout(new BorderLayout());
        super.add(new JScrollPane(list), BorderLayout.CENTER);
        super.add(buttonPanel, BorderLayout.SOUTH);

        model.addListener(this);

        if (model.getTodos().length != 0) {
            list.setSelectedIndex(0);
        }
    }

    public JList<TodoItemModel> getList() {
        return list;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDetailsButton() {
        return detailsButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public boolean showConfirmDialog(String message) {
        int result = JOptionPane.showConfirmDialog(this, message, "Confirm", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    public TodoItemModel getSelectedTodo() {
        return list.getSelectedValue();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("todos".equals(event.getPropertyName())) {
            TodoItemModel[] todos = model.getTodos();
            list.setListData(todos);
        }
        if ("selectedTodo".equals(event.getPropertyName())) {
            TodoItemModel selected = model.getSelectedTodo();
            list.setSelectedValue(selected, true);
        }
    }

    private static class TodoListCellRenderer extends JPanel implements ListCellRenderer<TodoItemModel> {
        private final JCheckBox completedCheckbox;
        private final JLabel titleLabel;

        public TodoListCellRenderer() {
            this.completedCheckbox = new JCheckBox();
            this.completedCheckbox.setEnabled(false);
            this.titleLabel = new JLabel();

            super.setLayout(new FlowLayout(FlowLayout.LEFT));
            super.setOpaque(true);
            super.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
            super.add(completedCheckbox);
            super.add(titleLabel);
        }

        @Override
        public Component getListCellRendererComponent(
            JList<? extends TodoItemModel> list,
            TodoItemModel todo,
            int index,
            boolean isSelected,
            boolean cellHasFocus
        ) {
            completedCheckbox.setSelected(todo.isCompleted());
            if (todo.isCompleted()) {
                titleLabel.setForeground(Color.GRAY);
                titleLabel.setText("<html><strike>" + todo.getTitle() + "</strike></html>");
            } else {
                titleLabel.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                titleLabel.setText(todo.getTitle());
            }
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                completedCheckbox.setBackground(list.getSelectionBackground());
            } else {
                setBackground(list.getBackground());
                completedCheckbox.setBackground(list.getBackground());
            }
            return this;
        }
    }
}