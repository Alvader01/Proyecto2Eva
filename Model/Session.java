package Model;

import Interfaces.Model.ISession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session implements ISession {
    private static Session instance;
    private User loggedInUser;
    private final List<User> users;
    private final List<Project> projects;


    private Session() {
        this.users = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }


    @Override
    public void createUser(String name, String username, String password, String email) {
        User newUser = new User(name, username, password, email);
        users.add(newUser);
        loggedInUser = newUser;
    }

    @Override
    public User login(String username, String password) {
        User foundUser = null;

        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                foundUser = user;
            }
        }

        loggedInUser = foundUser;
        return foundUser;
    }


    @Override
    public User getLoggedInUser() {
        return null;
    }
}
