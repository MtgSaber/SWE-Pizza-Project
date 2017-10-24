package net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling;

/**
 * Author: Andrew Arnold (10/23/2017)
 */
public class User {
    final String name;

    private boolean loggedOn;

    public User(String name, boolean loggedOn) {
        this.name = name;
        this.loggedOn = loggedOn;
    }

    public String getName() {
        return name;
    }

    public boolean isLoggedOn() {
        return loggedOn;
    }

    public void setLoggedOn(boolean loggedOn) {
        this.loggedOn = loggedOn;
    }
}
