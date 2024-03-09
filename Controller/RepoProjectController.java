package Controller;

import Model.Project;
import Model.Repos.RepoProject;
import Model.Session;
import Model.User;
import Utils.IO;
import View.MainView;
import View.TabsView;

public class RepoProjectController {
    private TabsView tabsView = new TabsView();
    private RepoProject repoProject = RepoProject.getInstance();
    private Session session = Session.getInstance();

    public Project createProject(Project newProject) {
        String name = IO.readString("Introduce el nombre: ");
        String description = IO.readString("Introduce la descripcion del proyecto: ");
        String creator = session.getLoggedInUser().getUsername();
        if (!newProject.getName().equals(name)) {
            newProject = new Project(name, description, creator);
            repoProject.create(newProject);
        } else {
            System.out.println("El proyecto ya existe");
        }
        return newProject;
    }

    public void showAllProjects(){
        for (Project project : repoProject.getAll()) {
            tabsView.showAllProjects(project);
        }
    }

    public void showProject(String projectName) {
        for (Project project : repoProject.getAll()) {
            if (project.getName().equals(projectName)) {
                tabsView.showProject(project);
            }
        }
    }

    public Project updateName(String name, String newName) {
        Project ProjectToUpdate = repoProject.getById(name);
        if (ProjectToUpdate != null &&
                session.getLoggedInUser().getUsername().equals(ProjectToUpdate.getProjectCreator())) {
            ProjectToUpdate.setName(newName);
            repoProject.update(ProjectToUpdate);
            MainView.showMessage("El nombre del  proyecto ha sido modificado exitosamente.");
        } else {
            MainView.showMessage("Solo el creador del proyecto puede modificar su nombre");
        }
        return ProjectToUpdate;
    }

        public void deleteProject(Project project) {
        if (project.getProjectCreator().equals(session.getLoggedInUser().getUsername())) {
            repoProject.delete(project.getName());
            MainView.showMessage("El proyecto ha sido eliminado exitosamente.");
        } else {
            MainView.showMessage("Solo el creador del proyecto puede eliminar el proyecto.");
        }
    }

    public Project getProject(String projectName){
        return repoProject.getById(projectName);
    }

    public boolean addCollaborator(Project project, User collaborator) {
        boolean addedCollaborator;
        if (project.getProjectCreator().equals(session.getLoggedInUser().getUsername())) {
            if (project.getCollaborators().contains(collaborator)) {
                MainView.showMessage("Este usuario ya ha sido agregado como colaborador en el proyecto.");
                addedCollaborator = false;
            } else {
                project.getCollaborators().add(collaborator);
                MainView.showMessage("El colaborador " + collaborator.getUsername() + " ha sido agregado exitosamente.");
                addedCollaborator = true;
            }
        } else {
            MainView.showMessage("Solo el creador del proyecto puede agregar colaboradores");
            addedCollaborator = false;
        }
        return addedCollaborator;
    }

    public void showAllCollaborators(Project project) {
        for (User user : project.getCollaborators()) {
            tabsView.showCollaborators(user);
        }
    }

    public void showCollaborator(Project project, String username) {
        for (User user : project.getCollaborators()) {
            if (user.getUsername().equals(username)) {
                tabsView.showCollaborators(user);
            }
        }
    }

    public boolean removeCollaborator(Project project, User collaborator) {
        boolean removedCollaborator = false;
        if (!isProjectCreator(project, collaborator.getUsername())) {
            MainView.showMessage("Solo el creador del proyecto puede eliminar colaboradores");
        } else {
            String username = IO.readString("Introduce el nombre del usuario: ");
            if (collaboratorExists(project, username)) {
                project.getCollaborators().remove(collaborator);
                removedCollaborator = true;
                MainView.showMessage("El colaborador ha sido eliminado exitosamente.");
            } else {
                MainView.showMessage("El colaborador no existe en el proyecto.");
            }
        }
        return removedCollaborator;
    }

    public boolean isProjectCreator(Project project, String username) {
        return project.getProjectCreator().equals(username);
    }

    public boolean projectExists(String name) {
        boolean projectExists = false;
        for (Project project : repoProject.getAll()) {
            if (project.getName().equals(name)) {
                projectExists = true;
            }
        }
        return projectExists;
    }

    public boolean collaboratorExists(Project project, String username) {
        boolean collaboratorExists = false;
        for (User user : project.getCollaborators()) {
            collaboratorExists = user.getName().equals(username);
        }
        return collaboratorExists;
    }
}
