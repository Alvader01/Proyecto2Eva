package Model;

import Interfaces.Model.IProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Project implements IProject {
    private String name;
    private String description;
    private String projectCreator;
    private ArrayList<Task> tasks;

    public Project(String name, String description, String projectCreator, ArrayList<Task> tasks) {
        this.name = name;
        this.description = description;
        this.projectCreator = projectCreator;
        this.tasks = tasks;
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
     * @param assignee
     */
    @Override
    public void createTask(Project project, String name, String description, LocalDate startDate, LocalDate endDate, User assignee) {

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
    public void deleteTask(Task task) {

    }
    /**
     * Con este metodo añadimos usuarios a las tareas
     * @param user
     */
    @Override
    public void addUserTask(User user) {

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
