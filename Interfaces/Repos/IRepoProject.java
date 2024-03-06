package Interfaces.Repos;

import Model.Project;
import Model.User;

import java.util.List;

public interface IRepoProject {
    boolean addCollaborator(Project project, User collaborator);
    boolean removeCollaborator(Project project, User collaborator);
    void showCollaborators(Project project);
    boolean save();
    boolean isProjectCreator(Project project, String username);
    boolean collaboratorExists(Project project, String username);

}
