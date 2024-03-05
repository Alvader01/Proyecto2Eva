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
     * Con este metodo eliminariamos proyectos
     * @param project
     * @param name
     */
    @Override
    public void removeProject(Project project,String name) {

    }
}
