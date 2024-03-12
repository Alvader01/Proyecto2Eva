package Interfaces.Controller;

import java.security.NoSuchAlgorithmException;

public interface ISubController {
    void menuOption1() throws NoSuchAlgorithmException;
    void menuManageProjects() throws NoSuchAlgorithmException;
    void menuConfigUser() throws NoSuchAlgorithmException;
    void menuModifyProject() throws NoSuchAlgorithmException;
    void menuManageTasks() throws NoSuchAlgorithmException;
    void saveAll();
}
