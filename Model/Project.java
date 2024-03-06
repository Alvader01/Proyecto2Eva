package Model;

import Interfaces.Model.IProject;
import Model.Repos.RepoProject;
import Model.Repos.RepoUser;
import Utils.IO;
import View.MainView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Project implements IProject {
    Scanner teclado = new Scanner(System.in);
    private String name;
    private String description;
    private String projectCreator;
    private List<User> collaborators;
    private ArrayList<Task> tasks;



    public Project(String name, String description, String projectCreator) {
        this.name = name;
        this.description = description;
        this.projectCreator = projectCreator;
        this.tasks = new ArrayList<Task>();
        this.collaborators = new ArrayList<User>();
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<User> collaborators) {
        this.collaborators = collaborators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectCreator() {
        return projectCreator;
    }

    public void setProjectCreator(String projectCreator) {
        this.projectCreator = projectCreator;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Con este metodo creamos las tareas
     * @param project
     * @param name
     * @param description
     * @param startDate
     * @param endDate
     */
    @Override
    public boolean createTask(Project project, String name, String description, LocalDate startDate, LocalDate endDate,String assignedUser) {
        boolean taskCreated = false;
        name=IO.readString("Introduce el nombre de la tarea");
        description=IO.readString("Introduce la descripción de la tarea");
        startDate=IO.readDate("Introduce la fecha de inicio de la tarea");
        endDate=IO.readDate("Introduce la fecha de finalización de la tarea");
        assignedUser=getAssignedUser();
        if (!project.getTasks().equals(name)) {
            Task newTask = new Task(name, description, startDate, endDate, assignedUser, TaskState.WITHOUT_STARTING);
            project.getTasks().add(newTask);
            taskCreated=true;
        }else {
            MainView.showMessage("La tarea ya existe");
        }
        return taskCreated;
    }
    public String getAssignedUser(){
        String username = IO.readString("Introduce el nombre del usuario");
        String assignedUser = "";
        for (User user : collaborators) {
           if (user.getUsername().equals(username)) {
               assignedUser = username;
           }else {
               MainView.showMessage("El usuario introducido no existe");
           }
        }
        return assignedUser;
    }


    /**
     * Con este metodo cambiamos los estados de las tareas
     * @param task
     */
    @Override
    public void changeTaskStatus(Task task) {
        if (Session.getInstance().getLoggedInUser().getUsername().equals(task.getAssignedUser())||
                getProjectCreator().equals(Session.getInstance().getLoggedInUser().getUsername())) {
            int option = IO.readInt("1. Sin iniciar\n2. En progreso\n3. finalizado\n");

            switch (option) {
                case 1:
                    task.setState(TaskState.WITHOUT_STARTING);
                    break;
                case 2:
                    task.setState(TaskState.IN_PROGRES);
                    break;
                case 3:
                    task.setState(TaskState.FINISHED);
                    break;
            }

        };
    }
    /**
     * Con este metodo eliminamos tareas de los proyectos
     * @param taskName
     */
    @Override
    public boolean deleteTask(String taskName) {
        boolean taskDeleted = false;
        for (Task task : tasks) {
            if (task.getName().equals(taskName) && Session.getInstance().getLoggedInUser().getUsername().equals(getProjectCreator())) {
                tasks.remove(task);
                taskDeleted = true;
            }else {
                MainView.showMessage("La tarea no existe o no eres el creador del proyecto");
            }
        }
        return taskDeleted;
    }

    /**
     * Con este metodo añadimos usuarios a las tareas
     * @param task
     * @param username
     */
    @Override
    public boolean updateAssignedUser(Task task, String username) {
        boolean assignedUser = false;
        for (User user : collaborators) {
            if (user.getUsername().equals(username)) {
                task.setAssignedUser(user.getUsername());
                assignedUser = true;
            }
        }
        return assignedUser;


    }

    @Override
    public boolean createComment(Task task, String comment) {
        boolean commentCreated;
        if (Session.getInstance().getLoggedInUser().getUsername().equals(task.getAssignedUser()) ||
                getProjectCreator().equals(Session.getInstance().getLoggedInUser().getUsername())) {
            task.getComments().add(comment);
            commentCreated = true;
        } else {
            System.out.println("Solo el usuario asignado o el creador del proyecto puede crear un comentario en la tarea.");
            commentCreated = false;
        }
        return commentCreated;
    }

    @Override
    public String toString() {
        return "Project{" +
                "Nombre del Proyecto='" + name + '\'' +
                ", Descripción del Proyecto='" + description + '\'' +
                ", Creador del Proyecto='" + projectCreator + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
