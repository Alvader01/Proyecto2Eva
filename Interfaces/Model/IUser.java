package Interfaces.Model;

import Model.User;

import java.util.List;

public interface IUser extends IManager {
    boolean deleteUser(User user);
    void updatePassword(User user, String newPassword);
    List<User> getAllUsers();
    boolean isEmailUnique(String email);
}
