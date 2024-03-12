package Interfaces.Controller;

import Model.User;

import java.security.NoSuchAlgorithmException;

public interface IRepoUserController {
    void createUser() throws NoSuchAlgorithmException;
    void showAllUsers();
    void showUser(User user);
    User updateName(String username, String newName);
    User updateUsername(String username, String newUsername);
    void updatePassword(String username, String newPassword) throws NoSuchAlgorithmException;
    void updateEmail(String username, String newEmail);
    String getEmailWithFormat();
    boolean isValidEmailFormat(String email);
    boolean userExists(String username);
    boolean isEmailUnique(String email);
    User getUser(String user);

}
