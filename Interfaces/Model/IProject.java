package Interfaces.Model;

import Model.Project;
import Model.Task;
import Model.User;

import java.time.LocalDate;

public interface IProject  {
    void createTask(Project project, String name, String description, LocalDate startDate, LocalDate endDate, User assignee);
    void changeTaskStatus(Task task, String newStatus, String comment);
    void deleteTask(Task task);
    void addUserTask(User user);
}
