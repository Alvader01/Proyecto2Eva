package Interfaces.Model;

import Model.Users.User;

import java.util.List;

public interface IUser {
    void createUser(String name, String username, String password, String email);
    boolean deleteUser(User user);
    void updatePassword(User user, String newPassword);
    List<User> getAllUsers();
    boolean isEmailUnique(String email);


}
