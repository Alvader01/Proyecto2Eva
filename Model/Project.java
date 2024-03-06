package Model;

import Interfaces.Model.IProject;
import Model.Repos.RepoUser;

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
    private User projectCreator;
    private List<User> collaborators;
    private ArrayList<Task> tasks;



    public Project(String name, String description, User projectCreator) {
        this.name = name;
        this.description = description;
        this.projectCreator = projectCreator;
        this.tasks = new ArrayList<Task>();
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

    public User getProjectCreator() {
        return projectCreator;
    }

    public void setProjectCreator(User projectCreator) {
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
    public boolean createTask(Project project, String name, String description, LocalDate startDate, LocalDate endDate) {
        // Verificar que el creador del proyecto sea el usuario actual
        if (!project.getProjectCreator().equals(Session.getInstance().getLoggedInUser())) {
            // Mensaje de que solo el creador del proyecto puede añadir colaboradores
            //System.out.println("Solo el creador del proyecto puede añadir tareas.");
            return false;
        }
        // Solicitar el nombre de la tarea por teclado
        //System.out.print("Ingrese el nombre de la tarea: ");
        name = teclado.nextLine();

        // Solicitar la descripción de la tarea por teclado
        //System.out.print("Ingrese la descripción de la tarea: ");
        description = teclado.nextLine();

        // Solicitar la fecha de inicio de la tarea por teclado
        //System.out.print("Ingrese la fecha de inicio de la tarea (dd/MM/yyyy): ");
        String startDateInput = teclado.nextLine();
        startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Solicitar la fecha de fin de la tarea por teclado
        //System.out.print("Ingrese la fecha de fin de la tarea (dd/MM/yyyy): ");
        String endDateInput = teclado.nextLine();
        endDate = LocalDate.parse(endDateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String username = teclado.nextLine();
        User user = RepoUser.getInstance().getById(username);



        // Solicitar un comentario por teclado
        //System.out.print("Ingrese un comentario para la tarea: ");
        String comment = teclado.nextLine();

        createComment(comment);


        // Crear la tarea
        Task newTask = new Task(name, description, startDate, endDate,username, TaskState.WITHOUT_STARTING,comment);
        project.getTasks().add(newTask);
        if (user == null) {
            //System.out.println("El usuario no existe.");
            return false;
        }

        newTask.setAssignedUser(user);

        return true;
    }

    /**
     * Con este metodo cambiamos los estados de las tareas
     * @param task
     * @param newStatus
     * @param comment
     */
    @Override
    public void changeTaskStatus(Task task, String newStatus, String comment) {

    }
    /**
     * Con este metodo eliminamos tareas de los proyectos
     * @param task
     */
    @Override
    public boolean deleteTask(Task task) {

    }
    /**
     * Con este metodo añadimos usuarios a las tareas
     * @param user
     */
    @Override
    public boolean addUserTask(User user) {

    }

    @Override
    public boolean createComment(String comment) {
        return false;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", projectCreator='" + projectCreator + '\'' +
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
