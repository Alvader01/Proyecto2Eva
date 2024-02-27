package Model;

import Interfaces.Model.IUser;

import java.util.List;

public class User implements IUser {
    private String name;
    private String user;
    private String email;
    private String password;


    public User(String name, String user, String email, String password) {
        this.name = name;
        this.user = user;
        this.email = email;
        this.password = password;

    }

    public User(){
        this("","","","");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public void updatePassword(User user, String newPassword) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean isEmailUnique(String email) {
        return false;
    }

    @Override
    public void addUser(User user) {

    }
}
