package Interfaces.Model;

import Model.User;

public interface ISession{
    void createUser(String name, String username, String password, String email);
    User login(String username, String password);
    User getLoggedInUser();

}
