package Controller;

import Model.Project;
import Model.Repos.RepoProject;
import Model.Repos.RepoUser;
import Model.Session;
import Model.Task;
import Utils.IO;
import View.MainView;
import View.SubView;
import View.TabsView;

import java.security.NoSuchAlgorithmException;

public class SubController {
    SubView subView = new SubView();
    Session session = Session.getInstance();
    ProjectController projectController = new ProjectController();
    RepoProjectController repoProjectController = new RepoProjectController();
    RepoUserController userController = new RepoUserController();
    RepoUser repoUser = RepoUser.getInstance();
    RepoProject repoProject = RepoProject.getInstance();
    TabsView tabsView = new TabsView();

    /**
     * Menu de nuestra primera opción
     *
     * @throws NoSuchAlgorithmException
     */
    public void menuOption1() throws NoSuchAlgorithmException {
        int option;
        do {
            SubView.showGap();
            MainView.showMessage("Usuario: " + session.getLoggedInUser().getUsername());
            subView.menuOption_1();
            option = IO.readInt("Elige una opción: ");
            switch (option) {
                case 1:
                    menuManageProjects();
                    break;
                case 2:
                    menuConfigUser();
                    break;
                case 3:
                    MainView.showMessage("Cerrando sesión...");
                    session.logout();
                    MainView.showMessage("Sesion cerrada");
                    break;
            }
        } while (option != 3);
    }

    /**
     * Submenu para el CRUD Proyectos
     *
     * @throws NoSuchAlgorithmException
     */
    public void menuManageProjects() throws NoSuchAlgorithmException {
        int option;
        do {
            SubView.showGap();
            subView.menuManageProjects_1();
            option = IO.readInt("Elige una opción: ");
            switch (option) {
                case 1: // Crear proyecto
                    Project project = repoProjectController.createProject();
                    repoProjectController.showProject(project.getName());
                    saveAll();
                    break;
                case 2: // Modificar proyecto
                    menuModifyProject();
                    break;
                case 3: // Mostrar proyecto/proyectos
                    int innerOption;
                    do {
                        SubView.showGap();
                        MainView.showMessage("1. Mostrar proyecto");
                        MainView.showMessage("2. Mostrar todos los proyectos");
                        MainView.showMessage("3. Volver");
                        innerOption = IO.readInt("Elige una opción ahora: ");
                        switch (innerOption) {
                            case 1:
                                String projectName = IO.readString("Introduce el nombre del proyecto que quieres mostrar: ");
                                repoProjectController.showProject(projectName);
                                break;
                            case 2:
                                repoProjectController.showAllProjects();
                                break;
                        }
                    } while (innerOption != 3);
                    break;
                case 4: // Borrar proyecto
                    String projectName = IO.readString("Introduce el nombre del proyecto que quieres borrar: ");
                    Project deleteProject = repoProjectController.getProject(projectName);
                    repoProjectController.deleteProject(deleteProject);
                    saveAll();
                    break;
            }
        } while (option != 5);
    }

    /**
     * Este menu es para el CRUD del usuario
     *
     * @throws NoSuchAlgorithmException
     */
    public void menuConfigUser() throws NoSuchAlgorithmException {
        int option;
        do {
            SubView.showGap();
            subView.menuConfigUser_1();
            option = IO.readInt("Elige una opción: ");
            switch (option) {
                case 1: // Cambiar nombre
                    String name = session.getLoggedInUser().getName();
                    String newName = IO.readString("Introduce el nuevo nombre de usuario: ");
                    if (!newName.equals(name)) {
                        userController.updateName(name, newName);
                        MainView.showMessage("El nombre ha sido cambiado con exito");
                        saveAll();
                    } else {
                        MainView.showMessage("El nuevo nombre de usuario es el mismo que tienes actualmente");
                    }
                    break;
                case 2: // Cambiar username
                    String username = session.getLoggedInUser().getUsername();
                    String newUsername = IO.readString("Introduce el nuevo nombre de usuario: ");
                    if (!newUsername.equals(username)) {
                        userController.updateUsername(username, newUsername);
                        MainView.showMessage("El nombre de usuario ha sido cambiado con exito");
                        saveAll();
                    } else {
                        MainView.showMessage("El nuevo nombre de usuario es el mismo que tienes actualmente");
                    }
                    break;
                case 3: // Cambiar contraseña
                    String user1name = session.getLoggedInUser().getUsername();
                    String newPassword = IO.readString("Introduce la contraseña nueva: ");
                    userController.updatePassword(user1name, newPassword);
                    MainView.showMessage("Tu contraseña ha sido cambiada máquinista");
                    saveAll();
                    break;
                case 4: // Cambiar email
                    String newEmail = userController.getEmailWithFormat();
                    if (!newEmail.equals(session.getLoggedInUser().getEmail())) {
                        userController.updateEmail(session.getLoggedInUser().getUsername(), newEmail);
                        MainView.showMessage("El email ha sido cambiado con exito");
                        saveAll();
                    } else {
                        MainView.showMessage("El nuevo email es el mismo que tienes actualmente");
                    }
                    break;
            }
        } while (option != 5);
    }

    /**
     * Este menu es para modificar los proyectos.
     */
    public void menuModifyProject() throws NoSuchAlgorithmException {
        int option;
        do {
            SubView.showGap();
            subView.menuModifyProject();
            option = IO.readInt("Elige una opción: ");
            switch (option) {
                case 1: // Cambiar nombre
                    String projectName = IO.readString("Introduce el nombre del proyecto que quieres modificar: ");
                    Project project = repoProjectController.getProject(projectName);
                    if (repoProjectController.projectExists(project.getName())) {
                        String newProjectName = IO.readString("Introduce el nuevo nombre del proyecto: ");
                        repoProjectController.updateName(project.getName(), newProjectName);
                        MainView.showMessage("El nombre del proyecto ha sido modificado exitosamente");
                        saveAll();
                    } else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;
                case 2: // Añadir colaborador
                    String projectToModify = IO.readString("Introduce el nombre del proyecto al que añadir el colaborador: ");
                    if (projectToModify.equals(repoProjectController.getProject(projectToModify).getName())) {
                        String collaboratorToAdd = IO.readString("Introduce el nombre de usuario del colaborador: ");
                        if (userController.userExists(collaboratorToAdd)) {
                            repoProjectController.addCollaborator(repoProjectController.getProject(projectToModify), userController.getUser(collaboratorToAdd));
                            MainView.showMessage("El colaborador "+ collaboratorToAdd +" ha sido añadido exitosamente");
                            saveAll();
                        } else {
                            MainView.showMessage("El colaborador no existe");
                        }
                    }
                    break;
                case 3: // Borrar colaborador
                    String projectModify = IO.readString("Introduce el nombre del proyecto al que añadir el colaborador: ");
                    if (projectModify.equals(repoProjectController.getProject(projectModify).getName())) {
                        String collaboratorToRemove = IO.readString("Introduce el nombre de usuario del colaborador: ");
                        if (userController.userExists(collaboratorToRemove)) {
                            repoProjectController.removeCollaborator(repoProjectController.getProject(projectModify), userController.getUser(collaboratorToRemove));
                            MainView.showMessage("El colaborador ha sido eliminado exitosamente");
                            saveAll();
                        } else {
                            MainView.showMessage("El colaborador no existe");
                        }
                    }
                    break;
                case 4: // Modificar tareas
                    menuManageTasks();
                    break;
            }
        } while (option != 5);
    }

    public void menuManageTasks() throws NoSuchAlgorithmException {
        int option;
        do {
            SubView.showGap();
            subView.menuManageTasks();
            option = IO.readInt("Elige una opción: ");
            switch (option) {
                case 1: // Añadir tarea
                    String projectTask = IO.readString("Introduce el nombre del proyecto al que quieres añadir una tarea: ");
                    if (repoProjectController.projectExists(projectTask)) {
                        projectController.createTask(repoProjectController.getProject(projectTask));
                        saveAll();
                    } else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;
                case 2: // Mostrar tarea/tareas
                    int innerOption;
                    do {
                        SubView.showGap();
                        MainView.showMessage("1. Mostrar tarea");
                        MainView.showMessage("2. Mostrar todas las tareas");
                        MainView.showMessage("3. Volver");
                        innerOption = IO.readInt("Elige una opción: ");
                        switch (innerOption) {
                            case 1:
                                String projectTaskShow = IO.readString("Introduce el nombre del proyecto al que quieres mostrar una tarea: ");
                                Project project = repoProjectController.getProject(projectTaskShow);
                                    String taskToShow = IO.readString("Introduce el nombre de la tarea que quieres mostrar: ");
                                    projectController.showTask(project, taskToShow);

                                break;
                            case 2:
                                String projectTasksShow = IO.readString("Introduce el nombre del proyecto del que quieres mostrar las tareas: ");
                                Project tasks = repoProjectController.getProject(projectTasksShow);
                                projectController.showAllTasks(tasks);
                                break;
                        }
                    } while (innerOption != 3);
                    break;
                case 3: // Cambiar nombre
                    String projectTaskRename = IO.readString("Introduce el nombre del proyecto al que quieres cambiar el nombre de una tarea: ");
                    Project projectUName = repoProjectController.getProject(projectTaskRename);
                    if (repoProjectController.projectExists(projectUName.getName())){
                        String taskToRename = IO.readString("Introduce el nombre de la tarea que quieres cambiar el nombre: ");
                        String newTaskName = IO.readString("Introduce el nuevo nombre de la tarea: ");
                        projectController.updateTaskName(projectUName, taskToRename, newTaskName);
                        MainView.showMessage("El nombre de la tarea ha sido cambiado exitosamente");
                        saveAll();
                    }else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;

                case 4:// Eliminar tarea
                    String projectTaskDelete = IO.readString("Introduce el nombre del proyecto al que quieres borrar una tarea: ");
                    Project projectTDelete = repoProjectController.getProject(projectTaskDelete);
                    if (repoProjectController.projectExists(projectTDelete.getName())) {
                        String taskToDelete = IO.readString("Introduce el nombre de la tarea que quieres borrar: ");
                        projectController.deleteTask(projectTDelete, taskToDelete);
                        saveAll();
                        MainView.showMessage("La tarea ha sido eliminada exitosamente");
                    } else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;
                case 5: // Cambiar el estado
                    String projectTaskStatus = IO.readString("Introduce el nombre del proyecto al que quieres cambiar el estado de una tarea: ");
                    Project project = repoProjectController.getProject(projectTaskStatus);
                    String taskToChangeStatus = IO.readString("Introduce el nombre de la tarea que quieres cambiar el estado: ");
                    projectController.changeTaskStatus(project, taskToChangeStatus);
                    saveAll();
                    break;
                case 6: // Cambiar usuario asignado
                    String projectTaskChangeUser = IO.readString("Introduce el nombre del proyecto al que quieres cambiar el usuario asignado de la tarea: ");
                    Project projectUsers = repoProjectController.getProject(projectTaskChangeUser);
                    String taskToChangeUser = IO.readString("Introduce el nombre de la tarea que quieres cambiar el usuario asignado: ");
                    Task task = projectController.getTask(projectUsers, taskToChangeUser);
                    repoProjectController.showAllCollaborators(projectUsers);
                    String changeUserAssigned = IO.readString("Introduce el nombre del usuario asignado nuevo: ");
                    projectController.updateAssignedUser(projectUsers,task, changeUserAssigned);
                    saveAll();
                    break;
                case 7: // Crear comentario
                    String projectTaskComment = IO.readString("Introduce el nombre del proyecto al que quieres añadir un comentario: ");
                    Project projectComment = repoProjectController.getProject(projectTaskComment);
                    if (projectComment != null) {
                        String taskToComment = IO.readString("Introduce el nombre de la tarea que quieres comentar: ");
                        Task taskComment = projectController.getTask(projectComment, taskToComment);
                        if (taskComment != null) {
                            String comment = IO.readString("Introduce el comentario a añadir: ");
                            projectController.createComment(projectComment, taskComment, comment);
                            saveAll();
                        } else {
                            MainView.showMessage("La tarea no existe en el proyecto.");
                        }
                    } else {
                        MainView.showMessage("El proyecto no existe.");
                    }
            }
        } while (option != 8);
    }

    public void saveAll(){
        repoProject.save();
        repoUser.save();
    }
}