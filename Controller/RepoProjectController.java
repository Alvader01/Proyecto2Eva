package Controller;

import Interfaces.Controller.IRepoProjectController;
import Model.Project;
import Model.Repos.RepoProject;
import Model.Session;
import Model.User;
import Utils.IO;
import View.MainView;
import View.TabsView;

public class RepoProjectController implements IRepoProjectController {
    private TabsView tabsView = new TabsView();
    private RepoProject repoProject = RepoProject.getInstance();
    private Session session = Session.getInstance();
    ProjectController projectController = new ProjectController();

    /**
     * Crea un nuevo proyecto
     * @return Proyecto creado
     */
    public Project createProject() {
        Project newProject = new Project();
        String name = IO.readString("Introduce el nombre: ");
        String description = IO.readString("Introduce la descripcion del proyecto: ");
        String creator = session.getLoggedInUser().getUsername();
        if (!newProject.getName().equals(name)) {
            newProject = new Project(name, description, creator);
            repoProject.create(newProject);
        } else {
            MainView.showMessage("El proyecto ya existe");
        }
        return newProject;
    }

    /**
     * Muestra todos los proyectos
     */
    public void showAllProjects(){
        for (Project project : repoProject.getAll()) {
            tabsView.showAllProjects(project);
        }
    }

    /**
     * Muestra un proyecto
     * @param projectName el parámetro a introducir es el nombre del proyecto.
     */
    public void showProject(String projectName) {
        for (Project project : repoProject.getAll()) {
            if (project.getName().equals(projectName)) {
                tabsView.showProject(project);
            }
        }
    }

    /**
     * Lo que hace es actualizar el nombre del usuario
     * @param name el nombre del usuario
     * @param newName y el nuevo nombre introducido
     */
    public void updateName(String name, String newName) {
        Project ProjectToUpdate = repoProject.getById(name);
        if (ProjectToUpdate != null &&
                session.getLoggedInUser().getUsername().equals(ProjectToUpdate.getProjectCreator())) {
            ProjectToUpdate.setName(newName);
            repoProject.update(ProjectToUpdate);
        } else {
            MainView.showMessage("Solo el creador del proyecto puede modificar su nombre");
        }
    }

    /**
     * Borrar un proyecto
     * @param projectToDelete el proyecto a borrar.
     */
    public void deleteProject(Project projectToDelete) {
        if (projectToDelete != null) {
            if (projectToDelete.isCreator(Session.getInstance().getLoggedInUser())) {
                repoProject.delete(projectToDelete.getName());
            } else {
                System.out.println("Error: No tienes permisos para eliminar el proyecto.");
            }
        } else {
            System.out.println("Error: El proyecto no existe.");
        }
    }
    public Project getProject(String projectName){
        return repoProject.getById(projectName);
    }

    /**
     * Añade un colaborador al proyecto
     * @param project recibe el proyecto
     * @param collaborator recibe el colaborador
     */
    public void addCollaborator(Project project, User collaborator) {
        if (project.getProjectCreator().equals(session.getLoggedInUser().getUsername())) {
            if (project.getCollaborators().contains(collaborator)) {
                MainView.showMessage("Este usuario ya ha sido agregado como colaborador en el proyecto.");
            } else {
                project.getCollaborators().add(collaborator);
            }
        }
    }

    /**
     * Muestra todos los colaboradores
     * @param project recibe el proyecto
     */
    public void showAllCollaborators(Project project) {
        for (User user : project.getCollaborators()) {
            tabsView.showCollaborators(user);
        }
    }

    /**
     * Muestra colaboradores
     * @param project nombre del proyecto
     * @param username nombre del usuario
     */
    public void showCollaborator(Project project, String username) {
        for (User user : project.getCollaborators()) {
            if (user.getUsername().equals(username)) {
                tabsView.showCollaborators(user);
            }
        }
    }

    /**
     * Quita un colaborador
     * @param project proyecto
     * @param collaborator colaborador
     */
    public void removeCollaborator(Project project, User collaborator) {
        if (collaboratorExists(project, collaborator.getUsername())) {
            project.getCollaborators().remove(collaborator);
        }
    }


    public boolean isProjectCreator(Project project, String username) {
        return project.getProjectCreator().equals(username);
    }

    /**
     * Proyecto existe
     * @param name nombre
     * @return el proyecto que si existe
     */
    public boolean projectExists(String name) {
        boolean projectExists = false;
        for (Project project : repoProject.getAll()) {
            if (project.getName().equals(name)) {
                projectExists = true;
            }
        }
        return projectExists;
    }

    /**
     * Metodo para validar que el usuario existe
     * @param project nombre del proyecto
     * @param username el usuario al que esta asociado
     * @return true o false
     */
    public boolean collaboratorExists(Project project, String username) {
        boolean collaboratorExists = false;
        for (User user : project.getCollaborators()) {
            collaboratorExists = user.getName().equals(username);
        }
        return collaboratorExists;
    }
}
