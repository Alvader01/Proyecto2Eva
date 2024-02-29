package Interfaces.Model;

import Model.User;

public interface ISession{
    User login(String username, String password);
    User getLoggedInUser();

}
