package Model;

import Interfaces.Model.IManager;

import java.time.LocalDate;
import java.util.ArrayList;


public class Manager implements IManager {
    private ArrayList<Project> projects;
    /**
     * Con este metodo creamos los proyectos
     * @param name
     * @param description
     * @param creator
     */
    @Override
    public void createProject(String name, String description, User creator) {

    }


    /**
     * Con este metodo añadimos usuarios a las tareas
     * @param project
     * @param collaborator
     */
    @Override
    public void addCollaborator(Project project, User collaborator) {

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
        //Solo usuario encargado y manager la cambia
    }

    /**
     * Con este metodo guardamos los proyectos en un archivo
     * @param project
     */
    @Override
    public void saveProject(Project project) {

    }


    /**
     * Con este metodo eliminamos usuarios de los proyectos
     * @param project
     * @param collaborator
     */
    @Override
    public void removeCollaborator(Project project, User collaborator) {

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

    /**
     * Con este metodo eliminariamos proyectos
     * @param project
     * @param name
     */
    @Override
    public void removeProject(Project project,String name) {

    }
}
