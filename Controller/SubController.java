package Controller;

import Model.Project;
import Model.Repos.RepoProject;
import Model.Repos.RepoUser;
import Model.Session;
import Model.Task;
import Model.User;
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
                    Project projectToDelete = repoProjectController.getProject(projectName);
                    if (projectToDelete != null) {
                        if (projectToDelete.isCreator(Session.getInstance().getLoggedInUser())) {
                            // El usuario es el creador del proyecto
                            repoProjectController.deleteProject(projectToDelete);
                            saveAll();
                        } else {
                            System.out.println("Solo el creador del proyecto puede eliminar el proyecto.");
                        }
                    } else {
                        System.out.println("El proyecto no existe.");
                    }
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
                case 5: // Eliminar usuario
                    String usernameToDelete = session.getLoggedInUser().getUsername();
                    String deleteUser = IO.readString("Introduce el nombre de usuario: ");
                    if (usernameToDelete.equals(deleteUser)) {
                        User user =userController.getUser(deleteUser);
                        userController.deleteUser(user);
                        MainView.showMessage("Usuario eliminado con éxito");
                        saveAll();
                        session.logout();
                        stop();
                        System.exit(0);
                    } else {
                        MainView.showMessage("Nombre de usuario incorrecto");
                    }
                    break;
            }
        } while (option != 6);
    }

    /**
     * Este menu es para modificar los proyectos.
     */
    public void menuModifyProject() {
        int option;
        do {
            SubView.showGap();
            subView.menuModifyProject();
            option = IO.readInt("Elige una opción: ");
            switch (option) {
                case 1: // Cambiar nombre
                    String projectName = IO.readString("Introduce el nombre del proyecto que quieres modificar: ");
                    Project project = repoProjectController.getProject(projectName);
                    if (project != null) {
                        if (project.isCreator(Session.getInstance().getLoggedInUser())) {
                            String newProjectName = IO.readString("Introduce el nuevo nombre del proyecto: ");
                            repoProjectController.updateName(project.getName(), newProjectName);
                            MainView.showMessage("El nombre del proyecto ha sido modificado exitosamente");
                            saveAll();
                        } else {
                            MainView.showMessage("Solo el creador del proyecto puede cambiar el nombre.");
                        }
                    } else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;
                case 2: // Añadir colaborador
                    String projectNameToAddCollaborator = IO.readString("Introduce el nombre del proyecto al que añadir el colaborador: ");
                    Project projectToAddCollaborator = repoProjectController.getProject(projectNameToAddCollaborator);
                    if (projectToAddCollaborator != null) {
                        if (projectToAddCollaborator.isCreator(Session.getInstance().getLoggedInUser())) {
                            userController.showAllUsers();
                            String collaboratorToAdd = IO.readString("Introduce el nombre de usuario del colaborador: ");
                            if (userController.userExists(collaboratorToAdd)) {
                                repoProjectController.addCollaborator(projectToAddCollaborator, userController.getUser(collaboratorToAdd));
                                saveAll();
                            } else {
                                MainView.showMessage("El usuario no existe");
                            }
                        } else {
                            MainView.showMessage("Solo el creador del proyecto puede añadir un colaborador.");
                        }
                    } else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;
                case 3: // Borrar colaborador
                    String projectModify = IO.readString("Introduce el nombre del proyecto al que añadir el colaborador: ");
                    Project projectToRemoveCollaborator = repoProjectController.getProject(projectModify);
                    if (projectToRemoveCollaborator != null) {
                        if (projectToRemoveCollaborator.isCreator(Session.getInstance().getLoggedInUser())) {
                            repoProjectController.showAllCollaborators(projectToRemoveCollaborator);
                            String collaboratorToRemove = IO.readString("Introduce el nombre del colaborador que quieres eliminar: ");
                            if (userController.userExists(collaboratorToRemove)) {
                                repoProjectController.removeCollaborator(projectToRemoveCollaborator, userController.getUser(collaboratorToRemove));
                                MainView.showMessage("El colaborador ha sido eliminado exitosamente");
                                saveAll();
                            } else {
                                MainView.showMessage("El colaborador no existe");
                            }
                        } else {
                            MainView.showMessage("Solo el creador del proyecto puede eliminar un colaborador.");
                        }
                    } else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;
                case 4: // Modificar tareas
                    menuManageTasks();
                    break;
            }
        } while (option != 5);
    }

    public void menuManageTasks() {
        int option;
        do {
            SubView.showGap();
            subView.menuManageTasks();
            option = IO.readInt("Elige una opción: ");
            switch (option) {
                case 1: // Añadir tarea
                    String projectTask = IO.readString("Introduce el nombre del proyecto al que quieres añadir una tarea: ");
                    if (repoProjectController.projectExists(projectTask)) {
                        Project project = repoProjectController.getProject(projectTask);
                        if (project.isCreator(Session.getInstance().getLoggedInUser())) {
                            projectController.createTask(project);
                            saveAll();
                        } else {
                            MainView.showMessage("Solo el creador del proyecto puede añadir una tarea.");
                        }
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
                        if (projectUName.isCreator(Session.getInstance().getLoggedInUser())) {
                            projectController.updateTaskName(projectUName, taskToRename, newTaskName);
                            saveAll();
                        } else {
                            MainView.showMessage("Solo el creador del proyecto puede cambiar el nombre de una tarea");
                        }
                    } else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;

                case 4:// Eliminar tarea
                    String projectTaskDelete = IO.readString("Introduce el nombre del proyecto al que quieres borrar una tarea: ");
                    Project projectTDelete = repoProjectController.getProject(projectTaskDelete);
                    if (repoProjectController.projectExists(projectTDelete.getName())) {
                        String taskToDelete = IO.readString("Introduce el nombre de la tarea que quieres borrar: ");
                        if (projectTDelete.isCreator(Session.getInstance().getLoggedInUser())) {
                            projectController.deleteTask(projectTDelete, taskToDelete);
                            saveAll();
                            MainView.showMessage("La tarea ha sido eliminada exitosamente");
                        } else {
                            MainView.showMessage("Solo el creador del proyecto puede eliminar una tarea");
                        }
                    } else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;
                case 5: // Cambiar el estado
                    String projectTaskStatus = IO.readString("Introduce el nombre del proyecto al que quieres cambiar el estado de una tarea: ");
                    Project project = repoProjectController.getProject(projectTaskStatus);
                    if (project != null) {
                        String taskToChangeStatus = IO.readString("Introduce el nombre de la tarea que quieres cambiar el estado: ");
                        Task selectedTask = null;
                        for (Task task : project.getTasks()) {
                            if (task.getName().equals(taskToChangeStatus)) {
                                selectedTask = task;
                                break;
                            }
                        }
                        if (selectedTask != null && (project.isCreator(Session.getInstance().getLoggedInUser()) ||
                                selectedTask.getAssignedUser().equals(Session.getInstance().getLoggedInUser().getUsername()))) {
                            projectController.changeTaskStatus(project, taskToChangeStatus);
                            saveAll();
                        } else if (selectedTask == null) {
                            MainView.showMessage("La tarea no existe en el proyecto");
                        } else {
                            MainView.showMessage("Solo el creador del proyecto o el usuario asignado a la tarea pueden cambiar el estado");
                        }
                    } else {
                        MainView.showMessage("El proyecto no existe");
                    }
                    break;
                case 6: // Cambiar usuario asignado
                    String projectTaskChangeUser = IO.readString("Introduce el nombre del proyecto al que quieres cambiar el usuario asignado de la tarea: ");
                    Project projectUsers = repoProjectController.getProject(projectTaskChangeUser);

                    if (projectUsers != null) {
                        String taskToChangeUser = IO.readString("Introduce el nombre de la tarea que quieres cambiar el usuario asignado: ");
                        Task task = projectController.getTask(projectUsers, taskToChangeUser);

                        if (task != null) {
                            // Verificar si el usuario conectado es el creador del proyecto
                            if (projectUsers.isCreator(Session.getInstance().getLoggedInUser())) {
                                repoProjectController.showAllCollaborators(projectUsers);
                                String changeUserAssigned = IO.readString("Introduce el nombre del usuario asignado nuevo: ");
                                // Verificar si el usuario nuevo asignado es un colaborador del proyecto
                                if (repoProjectController.collaboratorExists(projectUsers, changeUserAssigned)) {
                                    projectController.updateAssignedUser(projectUsers, task, changeUserAssigned);
                                    saveAll();
                                } else {
                                    MainView.showMessage("El usuario asignado debe ser un colaborador del proyecto.");
                                }
                            } else {
                                MainView.showMessage("Solo el creador del proyecto puede cambiar el usuario asignado de la tarea.");
                            }
                        } else {
                            MainView.showMessage("Esta tarea no existe.");
                        }
                    } else {
                        MainView.showMessage("Este proyecto no existe.");
                    }

                    break;
                case 7: // Crear comentario
                    String projectTaskComment = IO.readString("Introduce el nombre del proyecto al que quieres añadir un comentario: ");
                    Project projectComment = repoProjectController.getProject(projectTaskComment);

                    if (projectComment != null) {
                        String taskToComment = IO.readString("Introduce el nombre de la tarea que quieres comentar: ");
                        Task taskComment = projectController.getTask(projectComment, taskToComment);

                        if (taskComment != null) {
                            User loggedInUser = Session.getInstance().getLoggedInUser();
                            // Verificar si el usuario conectado es el creador del proyecto o está asignado a la tarea
                            if (projectComment.isCreator(loggedInUser) ||
                                    taskComment.getAssignedUser().equals(Session.getInstance().getLoggedInUser().getUsername())) {
                                String comment = IO.readString("Introduce el comentario a añadir: ");
                                projectController.createComment(projectComment, taskComment, comment);
                                saveAll();
                            } else {
                                MainView.showMessage("Solo el creador del proyecto o los usuarios asignados a la tarea pueden añadir comentarios.");
                            }
                        } else {
                            MainView.showMessage("La tarea no existe en el proyecto.");
                        }
                    } else {
                        MainView.showMessage("El proyecto no existe.");
                    }
                    break;
            }
        } while (option != 8);
    }

    public void saveAll(){
        repoProject.save();
        repoUser.save();
    }

    /**
     * Para el programa y guarda.
     */
    public void stop(){
        repoProject.save();
        repoUser.save();
        MainView.showMessage("ADIO");
    }

}
