package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui;

/**
 * Author: Andrew Arnold (10/18/2017)
 */
public abstract class Panes {
    protected static abstract class StandardPane {
        protected abstract void lockControls();
        protected abstract void unlockControls();
        protected abstract void build();
        protected abstract void hookEvents(Events eventsDefinition);
        protected abstract void refresh();
    }
}
