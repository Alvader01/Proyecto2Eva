package Interfaces.Model;

import Model.Project;
import Model.Task;
import Model.User;

import java.time.LocalDate;

public interface IProject  {
    boolean deleteTask(String taskName);
    boolean createComment(Task task, String comment);
    boolean updateAssignedUser(Task task, String username);
    void changeTaskStatus(Task task);
    boolean createTask(Project project, String name, String description, LocalDate startDate, LocalDate endDate,String assignedUser);


}
