package todo.view;

import todo.model.TodoItemModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public final class TodoItemView extends JDialog implements PropertyChangeListener {
    private final TodoItemModel model;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckbox;
    private JButton saveButton;
    private JButton cancelButton;

    public TodoItemView(TodoItemModel model) {
        super();
        super.setTitle("Todo Details");
        this.model = model;
        model.addListener(this);

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        titleField.setText(model.getTitle());
        descriptionArea.setText(model.getDescription());
        completedCheckbox.setSelected(model.isCompleted());

        super.setLayout(new BorderLayout());
        super.setSize(400, 350);
        super.add(formPanel, BorderLayout.CENTER);
        super.add(buttonPanel, BorderLayout.SOUTH);
        super.setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new BorderLayout(5, 0));
        titlePanel.add(new JLabel("Title:"), BorderLayout.WEST);
        titleField = new JTextField(20);
        titlePanel.add(titleField, BorderLayout.EAST);
        panel.add(titlePanel);

        JPanel descPanel = new JPanel(new BorderLayout(5, 0));
        descPanel.add(new JLabel("Description:"), BorderLayout.WEST);
        descriptionArea = new JTextArea(5, 20);
        descPanel.add(new JScrollPane(descriptionArea), BorderLayout.EAST);
        panel.add(descPanel);

        completedCheckbox = new JCheckBox("Completed");
        panel.add(completedCheckbox);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }

    public JCheckBox getCompletedCheckbox() {
        return completedCheckbox;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case "title" -> titleField.setText(model.getTitle());
            case "description" -> descriptionArea.setText(model.getDescription());
            case "completed" -> completedCheckbox.setSelected(model.isCompleted());
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        model.removeListener(this);
    }
}