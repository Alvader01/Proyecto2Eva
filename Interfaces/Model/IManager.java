package Interfaces.Model;
import Model.Project;
import Model.Task;
import Model.User;

import java.time.LocalDate;

public interface IManager {
    void createProject(String name, String description, User creator);
    void addCollaborator(Project project, User collaborator);
    void saveProject(Project project);
    void removeCollaborator(Project project, User collaborator);
    void removeProject(Project project,String name);
}
