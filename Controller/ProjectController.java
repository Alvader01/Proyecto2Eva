package Controller;

import Interfaces.Controller.IProjectController;
import Model.*;
import Utils.IO;
import View.MainView;
import View.TabsView;

import java.time.LocalDate;
import java.util.Iterator;

public class ProjectController implements IProjectController {
    TabsView tabsView = new TabsView();
    Project project = new Project();
    Session session = Session.getInstance();

    /**
     * Con este metodo creamos las tareas
     *
     * @param project Proyecto donde añadir la tarea
     */
    public void createTask(Project project) {
        Task newTask = new Task();
        String name = IO.readString("Introduce el nombre de la tarea: ");
        String description = IO.readString("Introduce la descripción de la tarea: ");
        LocalDate startDate = IO.readDate("Introduce la fecha de inicio de la tarea: ");
        LocalDate endDate = IO.readFinalDate("Introduce la fecha de finalización de la tarea: ", startDate);
        String assignedUser = addAssignedUser(newTask);
        newTask = new Task(name, description, startDate, endDate, assignedUser, TaskState.WITHOUT_STARTING);

        if (!taskExists(name)) {
            project.addTask(newTask);
            MainView.showMessage("La tarea ha sido creada exitosamente");
        } else {
            MainView.showMessage("La tarea ya existe");
        }
    }

    /**
     * Tarea existe
     * @param name nombre
     * @return la tarea existente
     */
    private boolean taskExists(String name) {
        boolean taskExists = false;
        for (Task task : project.getTasks()) {
            if (task.getName().equals(name)) {
                taskExists = true;
            }
        }
        return taskExists;
    }

    /**
     * Muestra todas las tareas
     * @param project de un proyecto
     */
    public void showAllTasks(Project project) {
        for (Task task : project.getTasks()) {
            tabsView.showAllTasks(task);
        }
    }

    /**
     * Muestra la tarea de un proyecto
     * @param project nombre
     * @param taskName nombre de la tarea
     */
    public void showTask(Project project, String taskName) {
        for (Task task : project.getTasks()) {
            if (task.getName().equals(taskName)) {
                tabsView.showTask(task);
            }else{
                MainView.showMessage("La tarea no existe");
            }
        }
    }

    /**
     * Actualiza el nombre de la tarea
     * @param project el proyecto
     * @param name el nombre
     * @param newName el nuevo nombre
     */
    public void updateTaskName(Project project, String name, String newName) {
        Task taskToUpdate = project.getById(name);
        if (taskToUpdate != null &&
                session.getLoggedInUser().getUsername().equals(project.getProjectCreator())) {
            taskToUpdate.setName(newName);
            project.update(taskToUpdate);
            MainView.showMessage("El nombre de la tarea ha sido modificado exitosamente.");
        } else {
            MainView.showMessage("Solo el creador del proyecto puede modificar su nombre");
        }
    }


    /**
     * Con este método cambiamos los estados de las tareas
     *
     * @param project Proyecto Tarea en la que cambias el estado
     */
    public void changeTaskStatus(Project project, String taskName) {
        for (Task task : project.getTasks()) {
            if (task.getName().equals(taskName)) {
                MainView.changeStatus();
                int option = IO.readInt("Selecciona el estado de la tarea deseado: ");
                switch (option) {
                    case 1:
                        project.changeTaskStatus(task.getName(), TaskState.WITHOUT_STARTING);
                        MainView.showMessage("Estado de la tarea cambiado a sin iniciar");
                        break;
                    case 2:
                        project.changeTaskStatus(task.getName(), TaskState.IN_PROGRESS);
                        MainView.showMessage("Estado de la tarea cambiado a en progreso");
                        break;
                    case 3:
                        project.changeTaskStatus(task.getName(), TaskState.FINISHED);
                        MainView.showMessage("Estado de la tarea cambiado a completado");
                        String choice = IO.readString("¿Desea borrar de la lista la tarea ya completada?(Si / No)");
                        if (choice.equalsIgnoreCase("si")) {
                            project.deleteTask(task.getName());
                            MainView.showMessage("La tarea ha sido borrada");
                        }
                        break;
                }
            }
        }
    }
    public void deleteTask(Project project, String taskName) {
        if (Session.getInstance().getLoggedInUser().getUsername().equals(project.getProjectCreator())) {
            Iterator<Task> iterator = project.getTasks().iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.getName().equals(taskName)) {
                    iterator.remove();  // Utiliza el iterador para eliminar la tarea de forma segura
                    return;
                }
            }
            MainView.showMessage("La tarea no existe");
        } else {
            MainView.showMessage("No eres el creador del proyecto");
        }
    }


    public void createComment(Project project, Task task, String comment) {
        if (session.getLoggedInUser().getUsername().equals(task.getAssignedUser()) ||
                project.getProjectCreator().equals(session.getLoggedInUser().getUsername())) {
            if (task.getComments().contains(comment)) {
                MainView.showMessage("El comentario ya existe");
            }
            project.addComment(task.getName(), comment);
            MainView.showMessage("El comentario ha sido creado exitosamente");
        } else {
            MainView.showMessage("Solo el usuario asignado o el creador del proyecto puede crear un comentario en la tarea.");
        }
    }

    /**
     * Añadir el username del usuario asignado a la tarea
     *
     */
    public String addAssignedUser(Task task) {
        String assignedUser;
        assignedUser = IO.readString("Introduce el username del usuario asignado: ");
        for (User user : project.getCollaborators()) {
            if (user.getUsername().equals(assignedUser)) {
                project.updateAssignedUser(task, assignedUser);
                MainView.showMessage("El usuario asignado ha sido añadido correctamente");
            } else {
                MainView.showMessage("El usuario no es colaborador en el proyecto");
            }
        }
        return assignedUser;
    }

    public void updateAssignedUser(Project project, Task task, String username) {
        if (project.getProjectCreator().equals(session.getLoggedInUser().getUsername())) {
            project.updateAssignedUser(task, username);
            MainView.showMessage("Usuario asignado actualizado correctamente");
        } else {
            MainView.showMessage("Solo el creador del proyecto puede cambiar el usuario asignado");
        }
    }

    public Task getTask(Project project, String taskName) {
        return project.getById(taskName);
    }
}
