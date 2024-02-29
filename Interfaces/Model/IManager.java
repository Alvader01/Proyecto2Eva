package Interfaces.Model;
import Model.Project;
import Model.Task;
import Model.Users.User;

import java.time.LocalDate;

public interface IManager {
    void createProject(String name, String description, User creator);
    void addCollaborator(Project project, User collaborator);
    void createTask(Project project, String name, String description, LocalDate startDate, LocalDate endDate, User assignee);
    void changeTaskStatus(Task task, String newStatus, String comment);
    void saveProject(Project project);
    void removeCollaborator(Project project, User collaborator);
    void deleteTask(Task task);
    void addUserTask(User user);
    void removeProject(Project project,String name);
}
