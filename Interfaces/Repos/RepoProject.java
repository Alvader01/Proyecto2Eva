package Interfaces.Repos;

import Model.Project;
import Model.User;

public interface RepoProject {
    void createProject(String name, String description, User creator);
    void addCollaborator(Project project, User collaborator);
    void saveProject(Project project);
    void removeCollaborator(Project project, User collaborator);
    void removeProject(Project project,String name);
}
