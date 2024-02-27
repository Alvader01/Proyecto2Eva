package Interfaces.Model;
import Model.Project;
import Model.Task;
import Model.User;

import java.time.LocalDate;

public interface IManager {
    void createUser(String name, String username, String password, String email);
    User login(String username, String password);

    void createProject(String name, String description, User creator);
    void addCollaborator(Project project, User collaborator);
    void createTask(Project project, String name, String description, LocalDate startDate, LocalDate endDate, User assignee);
    void changeTaskStatus(Task task, String newStatus, String comment);
    void saveProject(Project project);
    User getLoggedInUser();
    void removeCollaborator(Project project, User collaborator);
    void deleteTask(Task task);
}
