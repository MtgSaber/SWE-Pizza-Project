package net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling;

/**
 * Represents a User.
 * Author: Andrew Arnold (10/23/2017)
 */
public class User {
    final String userName;

    public User(String name) {
        this.userName = name;
    }

    public String getName() {
        return userName;
    }
}
