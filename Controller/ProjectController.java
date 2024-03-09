package Controller;

import Model.*;
import Utils.IO;
import View.MainView;

import java.time.LocalDate;

public class ProjectController {
    Project project = new Project();
    Session session = Session.getInstance();

    /**
     * Con este metodo creamos las tareas
     *
     * @param project Proyecto donde añadir la tarea
     */
    public Task createTask(Project project) {
        String name = IO.readString("Introduce el nombre de la tarea: ");
        String description = IO.readString("Introduce la descripción de la tarea: ");
        LocalDate startDate = IO.readDate("Introduce la fecha de inicio de la tarea: ");
        LocalDate endDate = IO.readDate("Introduce la fecha de finalización de la tarea: ");
        String assignedUser = getAssignedUser();
        Task newTask = null;
        if (!project.getTasks().equals(name)) {
            newTask = new Task(name, description, startDate, endDate, assignedUser, TaskState.WITHOUT_STARTING);
            project.addTask(newTask);
            MainView.showMessage("La tarea ha sido creada exitosamente");
        } else {
            MainView.showMessage("La tarea ya existe");
        }
        return newTask;
    }

    public void showAllTasks() {
        for (Task task : project.getTasks()) {
            System.out.println(task);
        }
    }

    public void showTask(String taskName) {
        for (Task task : project.getTasks()) {
            if (task.getName().equals(taskName)) {
                System.out.println(task);
            }
        }
    }







    public Task updateTaskName(String name, String newName) {
        Task taskToUpdate = project.getById(name);
        if (taskToUpdate != null &&
                session.getLoggedInUser().getUsername().equals(project.getProjectCreator())) {
            taskToUpdate.setName(newName);
            project.update(taskToUpdate);
            MainView.showMessage("El nombre de la tarea ha sido modificado exitosamente.");
        } else {
            MainView.showMessage("Solo el usuario puede modificar su nombre");
        }
        return taskToUpdate;
    }


    /**
     * Con este método cambiamos los estados de las tareas
     *
     * @param task Tarea en la que cambias el estado
     */
    public void changeTaskStatus(Task task) {
        if (session.getLoggedInUser().getUsername().equals(task.getAssignedUser()) ||
                project.getProjectCreator().equals(session.getLoggedInUser().getUsername())) {
            MainView.changeStatus();
            int option = IO.readInt("Selecciona el estado de la tarea deseado: \n");

            switch (option) {
                case 1:
                    project.changeTaskStatus(task.getName(), TaskState.WITHOUT_STARTING);
                    MainView.showMessage("Estado de la tarea cambiado a sin iniciar");
                    break;
                case 2:
                    project.changeTaskStatus(task.getName(),TaskState.IN_PROGRES);
                    MainView.showMessage("Estado de la tarea cambiado a en progreso");
                    break;
                case 3:
                    project.changeTaskStatus(task.getName(),TaskState.FINISHED);
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

    public boolean deleteTask(String taskName) {
        boolean taskDeleted = false;
        for (Task task : project.getTasks()) {
            if (task.getName().equals(taskName) && Session.getInstance().getLoggedInUser().getUsername().equals(project.getProjectCreator())) {
                project.deleteTask(task.getName());
                taskDeleted = true;
            }else {
                MainView.showMessage("La tarea no existe o no eres el creador del proyecto");
            }
        }
        return taskDeleted;
    }

    public boolean createComment(Task task, String comment) {
        boolean commentCreated;
        if (session.getLoggedInUser().getUsername().equals(task.getAssignedUser()) ||
                project.getProjectCreator().equals(session.getLoggedInUser().getUsername())) {
            if (task.getComments().contains(comment)) {
                MainView.showMessage("El comentario ya existe");
            }
            project.addComment(task.getName(), comment);
            commentCreated = true;
            MainView.showMessage("El comentario ha sido creado exitosamente");
        } else {
            MainView.showMessage("Solo el usuario asignado o el creador del proyecto puede crear un comentario en la tarea.");
            commentCreated = false;
        }
        return commentCreated;
    }

    /**
     * Obtener el usuario asignado a la tarea
     * @return El username del usuario
     */
    public String getAssignedUser() {
        String username = IO.readString("Introduce el nombre del usuario: ");
        String assignedUser = "";
        for (User user : project.getCollaborators()) {
            if (user.getUsername().equals(username)) {
                assignedUser = username;
            } else {
                MainView.showMessage("El usuario introducido no existe");
            }
        }
        return assignedUser;
    }

    public void updateAssignedUser( Task task, String username) {
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
