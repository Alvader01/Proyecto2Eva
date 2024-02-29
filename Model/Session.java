package Model;

import Interfaces.Model.ISession;
import Model.Users.User;

import java.util.ArrayList;
import java.util.List;

public class Session implements ISession {
    private static Session instance;
    private User loggedInUser;
    private final List<User> users;


    private Session() {
        this.users = new ArrayList<>();

    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
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
