package Interfaces.Repos;

import Model.Project;
import Model.User;

import java.util.List;

public interface IRepoProject {
    boolean createProject(String name, String description, User creator);
    boolean addCollaborator(Project project, List<User> users, String username);
    boolean removeCollaborator(Project project, User collaborator);
    boolean removeProject(Project project,String name);
    boolean saveProject(String project);
}
