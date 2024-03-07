package Model.Repos;

import Interfaces.Repos.IRepository;
import Model.Project;
import Model.User;

import java.util.*;

public class RepoProject extends Repository<Project, String> implements IRepository<Project, String> {
    private final static String FILENAME = "projects.bin";
    private static RepoProject _instance;
    List<Project> projects;

    private RepoProject() {
        this.projects = new ArrayList<>();
    }

    public static RepoProject getInstance() {
        if (_instance == null) {
            _instance = (RepoProject) load(FILENAME);
            if (_instance == null) {
                _instance = new RepoProject();
            }
        }
        return _instance;
    }

    /**
     * Añade proyecto a la lista de proyectos*
     *
     * @param newProject Proyecto a añadir
     * @return Proyecto añadido
     */
    @Override
    public Project create(Project newProject) {
        projects.add(newProject);
        return newProject;
    }

    /**
     * Actualiza un proyecto con los datos introducidos
     *
     * @param data datos del proyecto a actualizar
     * @return Proyecto actualizado
     */
    @Override
    public Project update(Project data) {
        Project result = getById(data.getName());
        if (result != null) {
            projects.remove(result);
            projects.add(data);
            result = data;
        }
        return result;
    }

    /**
     * Borrar proyecto de la lista de proyectos
     *
     * @param projectName Nombre del proyecto
     * @return True si se ha eliminado el proyecto, false si ha habido un error al borrar el proyecto
     */
    @Override
    public boolean delete(String projectName) {
        boolean projectDeleted = false;
        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                projects.remove(project);
                projectDeleted = true;
            }
        }
        return projectDeleted;
    }

    /**
     * Obtener un proyecto según su nombre
     *
     * @param projectName Nombre del proyecto
     * @return Proyecto encontrado
     */
    @Override
    public Project getById(String projectName) {
        Project foundProject = null;
        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                foundProject = project;
            }
        }
        return foundProject;
    }

    /**
     * Obtener la lista de proyectos
     *
     * @return
     */
    @Override
    public Collection<Project> getAll() {
        return projects;
    }

    /**
     * Guarda la lista de proyectos en un fichero
     *
     * @return true si se ha guardado con éxito, false si no
     */
    public boolean save() {
        return save(FILENAME);
    }
}
