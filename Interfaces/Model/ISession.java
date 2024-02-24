package Interfaces.Model;

import Model.Session;
import Model.User;

public interface ISession {

    public void logIn(User user);
    public void logOut();
    public User getActualUser();

}
