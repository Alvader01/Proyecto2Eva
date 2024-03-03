package Model;

import Utils.Security;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class User {
    private String name;
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User(String username) throws NoSuchAlgorithmException {
        this("", username, "", "");
    }

    public User(String name, String username, String password, String email) throws NoSuchAlgorithmException {
        this.name = name;
        this.username = username;
        setPassword(password);
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = Security.hashPassword(password);
    }

    /**
     * Comprueba si la contraseña obtenida es la misma que la almacenada despues de hashear
     *
     * @param  password  la contraseña a comprobar
     * @return           true si la contraseña es la misma, false en caso contrario
     */
    public boolean isMyPassword(String password) throws NoSuchAlgorithmException {
        return this.password.equals(Security.hashPassword(password));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) || Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
