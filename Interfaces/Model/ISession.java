package Interfaces.Model;

import Model.Session;
import Model.User;

import java.security.NoSuchAlgorithmException;

public interface ISession{
    User login(String username, String password) throws NoSuchAlgorithmException;
    User getLoggedInUser();

}
