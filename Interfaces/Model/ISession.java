package Interfaces.Model;

import Model.Users.User;

public interface ISession{
    User login(String username, String password);
    User getLoggedInUser();

}
