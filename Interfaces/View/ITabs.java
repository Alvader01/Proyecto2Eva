package Interfaces.View;

import Model.Project;
import Model.Task;
import Model.User;

import java.util.Collection;
import java.util.List;

public interface ITabs {
    public void showAllProjects(Project project);
    public void showProject(Project project);
    public void showAllTasks(Task task);
    public void showTask(Task task);
    public void showAllUser(Collection<User> users);
}
