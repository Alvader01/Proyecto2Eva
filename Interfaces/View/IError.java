package Interfaces.View;

public interface IError {
    public void userDontExist();

    public void userAlreadyExist();

    public void emailAlreadyExist();

    public void projectDontExist();

    public void taskDontExist();
}
