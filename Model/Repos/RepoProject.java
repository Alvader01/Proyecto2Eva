package Model.Repos;

import Interfaces.Repos.IRepoProject;
import Interfaces.View.IMainView;
import Model.Project;
import Model.Session;
import Model.User;
import Utils.IO;
import View.MainView;


import java.security.NoSuchAlgorithmException;
import java.util.*;

public class RepoProject extends Repository<Project, String> implements IRepoProject {
    private final static String FILENAME = "projects.bin";
    private static RepoProject _instance;
    List<Project> projects;

    private RepoProject() {
        this.projects = new ArrayList<>();
    }
    public static RepoProject getInstance() {
        if (_instance == null) {
            _instance = (RepoProject) load(FILENAME);
            if (_instance == null) {
                _instance = new RepoProject();
            }
        }
        return _instance;
    }

    @Override
    public Project create(Project newProject) {
        String name = IO.readString("Introduce el nombre: ");
        String description = IO.readString("Introduce la descripcion del proyecto: ");
        String creator = Session.getInstance().getLoggedInUser().getUsername();
        if (!newProject.getName().equals(name)) {
            newProject = new Project(name, description, creator);
            projects.add(newProject);
        } else {
            System.out.println("El proyecto ya existe");
        }
        return  newProject;
    }


    @Override
    public boolean addCollaborator(Project project, User collaborator) {
        boolean addedCollaborator;
        if (project.getCollaborators().contains(collaborator)) {
           MainView.showMessage("Colaborador ya agregado en el proyecto");
            addedCollaborator = false;
        } else {
            project.getCollaborators().add(collaborator);
            addedCollaborator = true;
        }
        return addedCollaborator;
    }

    @Override
    public void showCollaborators(Project project){
        for (User user : project.getCollaborators()) {
            System.out.println("Nombre de usuario: " + user.getUsername());
        }

    }


    /**
     *
     * @param project
     * @param collaborator
     * @return
     */
    @Override
    public boolean removeCollaborator(Project project, User collaborator) {
        boolean removedCollaborator = false;
        if (!isProjectCreator(project,collaborator.getUsername())) {
            removedCollaborator = false;
        } else {
            String username = IO.readString("Introduce el nombre del usuario");
            if (collaboratorExists(project, username)) {
                project.getCollaborators().remove(collaborator);
                removedCollaborator = true;
            }

        }
        return removedCollaborator;
    }
    @Override
    public boolean collaboratorExists(Project project, String username) {
        boolean collaboratorExists = false;
        for (User user : project.getCollaborators()) {
            if (user.getName().equals(username)) {
                collaboratorExists = true;
            }

        }
        return collaboratorExists;
    }
    @Override
    public boolean isProjectCreator(Project project, String username) {
        boolean isProjectCreator = false;
        if (project.getProjectCreator().equals(username)) {
                    isProjectCreator = true;
                }
        return isProjectCreator;
    }

    @Override
    public boolean update(String projectName) {
        boolean projectUpdated = false;
        for (Project project : projects){
            if (project.getName().equals(projectName) &&
                    project.getProjectCreator().equals(Session.getInstance().getLoggedInUser().getUsername())){
                project.setName(projectName);
                projectUpdated= true;
            }
        }
       return  projectUpdated;
    }

    @Override
    public boolean delete(String projectName) {
        boolean projectDeleted = false;
        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                projects.remove(project);
                projectDeleted = true;
            }
        }
        return projectDeleted;
    }

    @Override
    public Project getById(String projectName) {
        Project foundProject = null;
        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                foundProject = project;
            }
        }
        return foundProject;
    }

    @Override
    public List<Project> getAll() {
        return projects;
    }

    @Override
    public boolean save() {
        return save(FILENAME);
    }

}
