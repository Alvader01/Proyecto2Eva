package Controller;

import Model.*;
import Utils.IO;
import View.MainView;
import View.TabsView;

import java.time.LocalDate;

public class ProjectController {
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

    private boolean taskExists(String name) {
        boolean taskExists = false;
        for (Task task : project.getTasks()) {
            if (task.getName().equals(name)) {
                taskExists = true;
            }
        }
        return taskExists;
    }

    public void showAllTasks() {
        for (Task task : project.getTasks()) {
            tabsView.showAllTask(task);
        }
    }

    public void showTask(String taskName) {
        for (Task task : project.getTasks()) {
            if (task.getName().equals(taskName)) {
                tabsView.showTask(task);
            }
        }
    }

    public void updateTaskName(String name, String newName) {
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
     * @param task Tarea en la que cambias el estado
     */
    public void changeTaskStatus(Task task) {
        MainView.changeStatus();
        int option = IO.readInt("Selecciona el estado de la tarea deseado: \n");
        switch (option) {
            case 1:
                project.changeTaskStatus(task.getName(), TaskState.WITHOUT_STARTING);
                MainView.showMessage("Estado de la tarea cambiado a sin iniciar");
                break;
            case 2:
                project.changeTaskStatus(task.getName(), TaskState.IN_PROGRES);
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

    public void deleteTask(String taskName) {
        for (Task task : project.getTasks()) {
            if (task.getName().equals(taskName) && Session.getInstance().getLoggedInUser().getUsername().equals(project.getProjectCreator())) {
                project.deleteTask(task.getName());
            } else {
                MainView.showMessage("La tarea no existe o no eres el creador del proyecto");
            }
        }
    }

    public void createComment(Task task, String comment) {
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

    public void updateAssignedUser(Task task, String username) {
        if (project.getProjectCreator().equals(session.getLoggedInUser().getUsername())) {
            project.updateAssignedUser(task, username);
            MainView.showMessage("Usuario asignado actualizado correctamente");
        } else {
            MainView.showMessage("Solo el creador del proyecto puede cambiar el usuario asignado");
        }
    }

    public Task getTask(String taskName) {
        return project.getById(taskName);
    }
}