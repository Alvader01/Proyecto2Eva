package Interfaces.Repos;

public interface IRepoUser {
    boolean isEmailUnique(String email);
    boolean userExists(String username);
    public String getEmailWithFormat();
    boolean isValidEmailFormat(String email);
    boolean save();
}
