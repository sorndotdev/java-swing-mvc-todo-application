package todo.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Model {
    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    protected Model() {}

    public final void addListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public final void removeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}