package Interfaces.Model;


import Model.Task;
import Model.TaskState;

import java.util.List;

public interface IProject  {
    Task addTask(Task task);
    List<Task> getAll();
    boolean deleteTask(String taskName);
    Task getById(String nameTask);
    void changeTaskStatus(String nameTask, TaskState state);
    void addComment(String nameTask, String comment);
    boolean updateAssignedUser(Task task, String username);
}