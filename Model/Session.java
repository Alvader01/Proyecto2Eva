package Model;

import Interfaces.Model.ISession;

public class Session implements ISession {
    private static Session instance;
    private User actualUser;

    private Session() {
        this.actualUser = null;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    @Override
    public void logIn(User user) {
        this.actualUser = user;
    }

    @Override
    public void logOut() {
        this.actualUser = null;
    }

    @Override
    public User getActualUser() {
        return this.actualUser;
    }
}
