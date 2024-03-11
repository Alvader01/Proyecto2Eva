package View;

import Interfaces.View.ITabs;
import Model.Project;
import Model.Task;
import Model.TaskState;
import Model.User;

import java.util.Collection;
import java.util.Collections;

public class TabsView implements ITabs {

    /**
     * Este metodo muestra la información general de todos los proyectos.
     *
     * @param project
     */
    public void showAllProjects(Project project) {
        System.out.println("══════════════════════════════════════════");
        System.out.println("         Información del Proyecto         ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("    Nombre del Proyecto: " + project.getName());
        System.out.println("    Descripción: " + project.getDescription());
        System.out.println("    Creador del Proyecto: " + project.getProjectCreator());
        System.out.println("══════════════════════════════════════════");
    }

    /**
     * Este metodo muestra la información de un proyecto.
     *
     * @param project
     */
    public void showProject(Project project) {
        System.out.println("══════════════════════════════════════════");
        System.out.println("        Información del Proyecto      ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("    Nombre del Proyecto: " + project.getName());
        System.out.println("    Descripción: " + project.getDescription());
        System.out.println("    Creador del Proyecto: " + project.getProjectCreator());
        for (Task task : project.getTasks()) {
            System.out.println("    Tarea: " + task.getName());
        }
        for (User user : project.getCollaborators()) {
            System.out.println("    Colaborador: " + user.getName());
        }
        System.out.println("══════════════════════════════════════════");
    }

    /**
     * Este metodo muestra la información de todas las tareas.
     *
     * @param task
     */
    public void showAllTasks(Task task) {
        System.out.println("══════════════════════════════════════════");
        System.out.println("          Información de la Tarea       ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("    Nombre de la Tarea: " + task.getName());
        System.out.println("    Descripción: " + task.getDescription());
        System.out.println("    Fecha de Inicio: " + task.getStartDate());
        System.out.println("    Fecha de Fin: " + task.getEndDate());
        System.out.println("    Usuario asignado: " + task.getAssignedUser());
        if (task.getState() == TaskState.WITHOUT_STARTING) {
            System.out.println("    Estado:  Sin iniciar");
        } else if (task.getState() == TaskState.IN_PROGRESS) {
            System.out.println("    Estado:  En progreso");
        } else if (task.getState() == TaskState.FINISHED) {
            System.out.println("    Estado: Completado");
        }
        System.out.println("══════════════════════════════════════════");
    }

    /**
     * Este metodo muestra la información de una tarea.
     *
     * @param task
     */
    public void showTask(Task task) {
        System.out.println("══════════════════════════════════════════");
        System.out.println("          Información de la Tarea       ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("    Nombre de la Tarea: " + task.getName());
        System.out.println("    Descripción: " + task.getDescription());
        System.out.println("    Fecha de Inicio: " + task.getStartDate());
        System.out.println("    Fecha de Fin: " + task.getEndDate());
        System.out.println("    Asignado a: " + task.getAssignedUser());
        if (task.getState() == TaskState.WITHOUT_STARTING) {
            System.out.println("    Estado:  Sin iniciar");
        } else if (task.getState() == TaskState.IN_PROGRESS) {
            System.out.println("    Estado:  En progreso");
        } else if (task.getState() == TaskState.FINISHED) {
            System.out.println("    Estado: Completado");
        }
        for (String comment : task.getComments()) {
            System.out.println("    Comentario: " + comment);
        }
        System.out.println("══════════════════════════════════════════");
    }

    /**
     * Este metodo muestra la información de los colaboradores.
     *
     * @param user
     */
    public void showCollaborators(User user) {
        System.out.println("══════════════════════════════════════════");
        System.out.println("              Colaboradores               ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("    Colaborador: " + user.getName());
        System.out.println("══════════════════════════════════════════");
    }

    /**
     * Este metodo muestra la información de todos los usuarios.
     *
     * @param users
     */
    public void showAllUser(Collection<User> users) {
        System.out.println("══════════════════════════════════════════");
        System.out.println("            Lista de usuarios             ");
        System.out.println("══════════════════════════════════════════");
        for (User user : users) {
            System.out.println("     Usuario: " + user.getUsername());
        }
        System.out.println("══════════════════════════════════════════");
    }


}
