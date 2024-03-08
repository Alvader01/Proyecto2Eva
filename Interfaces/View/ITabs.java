package Interfaces.View;

import Model.Project;
import Model.Task;
import Model.User;

public interface ITabs {

    public void showAllProject(Project project);
    public void showProject(Project project);
    public void showAllTask(Task task);
    public void showTask(Task task);
    public void showCollaborators(User user);
}
