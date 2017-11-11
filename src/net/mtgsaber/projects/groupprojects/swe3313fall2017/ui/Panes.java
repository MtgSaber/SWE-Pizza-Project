package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui;

import javafx.scene.control.Control;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.LinkedList;

/**
 * Author: Andrew Arnold (10/18/2017)
 */
public abstract class Panes {
    protected static abstract class StandardPane extends VBox {
        protected LinkedList<Control> controls;
        protected final Text txtStatus;

        protected StandardPane() {
            super(8);
            controls = new LinkedList<>();
            txtStatus = new Text();
        }

        protected void lockControls() {
            for (Control control : controls)
                control.setDisable(true);
        }
        protected void unlockControls() {
            for (Control control : controls)
                control.setDisable(false);
        }
        protected abstract void build();
        protected abstract void hookEvents(Events eventsDefinition);
        protected abstract void refresh();
    }
}
