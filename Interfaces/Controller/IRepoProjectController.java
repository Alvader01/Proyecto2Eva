package Interfaces.Controller;

import Model.Project;
import Model.Task;
import Model.User;

public interface IRepoProjectController {
    Project createProject();
    void showAllProjects();
    void showProject(String projectName);
    void updateName(String name, String newName);
    void deleteProject(Project projectToDelete);
    Project getProject(String projectName);
    void addCollaborator(Project project, User collaborator);
    void showAllCollaborators(Project project);
    void showCollaborator(Project project, String username);
    void removeCollaborator(Project project, User collaborator);
    boolean isProjectCreator(Project project, String username);
    boolean projectExists(String name);
    boolean collaboratorExists(Project project, String username);

}
