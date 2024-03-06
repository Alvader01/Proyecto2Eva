package Model.Repos;

import Interfaces.Repos.IRepoProject;
import Interfaces.View.IMainView;
import Model.Project;
import Model.Session;
import Model.User;
import Utils.IO;
import View.MainView;


import java.util.*;

public class RepoProject extends Repository<Project, String> implements IRepoProject {
    private final static String FILENAME = "projects.txt";
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
    public boolean createProject(String name, String description,User creator) {
        // Mensaje para Solicitar el nombre del proyecto al usuario
        name = scanner.nextLine();

        for (Project project : projects) {
            if (project.getName().equals(name)) {
                // Mensaje de que no se puede crear porque el nombre ya existe
                return false;
            }
        }

        // Mensaje para Solicitar la descripción del proyecto al usuario
        description = scanner.nextLine();

        // Crear un nuevo proyecto con el nombre y la descripción proporcionados
        creator = Session.getInstance().getLoggedInUser();
        Project newProject = new Project(name, description, creator);

        // Agregar el nuevo proyecto a la lista de proyectos
        projects.add(newProject);

        // Devuelve true para indicar que el proyecto se creo correctamente
        return true;
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


    public void showCollaborators(){
        for (User user :  ) {
            //Esto tambien tenemos que implementarlo en el view pero lo voy a dejar asi para
            // saber que tenemos que poner
            System.out.println("Nombre de usuario: " + user.getName());
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

    public boolean collaboratorExists(Project project, String username) {
        boolean collaboratorExists = false;
        for (User user : project.getCollaborators()) {
            if (user.getName().equals(username)) {
                collaboratorExists = true;
            }

        }
        return collaboratorExists;
    }

        public boolean isProjectCreator (Project project,String username) {
            boolean isProjectCreator = false;
                if (project.getProjectCreator().equals(username)) {
                    isProjectCreator = true;
                }
            return isProjectCreator;
        }


    public boolean delete(String projectname) {
        boolean userDeleted = false;
        for (Project project : projects) {
            if (Project.getName().equals(username)) {
                projectname.remove(user);
                userDeleted = true;
            }
        }
        return userDeleted;
    }




    public boolean save() {
        return save(FILENAME);
    }



}
