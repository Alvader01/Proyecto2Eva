package Interfaces.Controller;

import Model.Project;
import Model.Task;

public interface IProjectController {
    void createTask(Project project);
    void showAllTasks(Project project);
    void showTask(Project project, String taskName);
    void updateTaskName(Project project, String name, String newName);
    void changeTaskStatus(Project project, String taskName);
    void deleteTask(Project project, String taskName);
    void createComment(Project project, Task task, String comment);
    String addAssignedUser(Task task);
    void updateAssignedUser(Project project, Task task, String username);
    Task getTask(Project project, String taskName);
}
