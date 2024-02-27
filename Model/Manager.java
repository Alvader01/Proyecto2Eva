package Model;

import Interfaces.Model.IManager;

import java.time.LocalDate;


public class Manager implements IManager {


    @Override
    public void createUser(String name, String username, String password, String email) {

    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public void createProject(String name, String description, User creator) {

    }

    @Override
    public void addCollaborator(Project project, User collaborator) {

    }

    @Override
    public void createTask(Project project, String name, String description, LocalDate startDate, LocalDate endDate, User assignee) {

    }

    @Override
    public void changeTaskStatus(Task task, String newStatus, String comment) {

    }

    @Override
    public void saveProject(Project project) {

    }

    @Override
    public User getLoggedInUser() {
        return null;
    }

    @Override
    public void removeCollaborator(Project project, User collaborator) {

    }

    @Override
    public void deleteTask(Task task) {

    }
}
