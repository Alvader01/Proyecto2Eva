package View;

import Interfaces.View.ITabs;
import Model.Project;
import Model.Task;
import Model.User;

public class TabsView implements ITabs {

    /**
     * Este metodo muestra la información general de todos los proyectos.
     * @param project
     */
    public void showAllProjects(Project project) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           Información del Proyecto     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║   Nombre del Proyecto: " + formatString(project.getName()) + "    ║");
        System.out.println("║   Descripción: " + formatString(project.getDescription()) + "║");
        System.out.println("║   Creador del Proyecto: " + formatString(project.getProjectCreator()) + "  ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    /**
     * Este metodo muestra la información de un proyecto.
     * @param project
     */
    public void showProject(Project project) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           Información del Proyecto     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║   Nombre del Proyecto: " + formatString(project.getName()) + "    ║");
        System.out.println("║   Descripción: " + formatString(project.getDescription()) + "║");
        System.out.println("║   Creador del Proyecto: " + formatString(project.getProjectCreator()) + "  ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    /**
     * Este metodo muestra la información de todas las tareas.
     * @param task
     */
    public void showAllTask(Task task) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           Información de la Tarea      ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║   Tareas: " +  formatString(task.getName())+ "  ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    /**
     * Este metodo muestra la información de una tarea.
     * @param task
     */
    public void showTask(Task task) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           Información de la Tarea      ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║   Nombre de la Tarea: " + formatString(task.getName()) + "  ║");
        System.out.println("║   Descripción: " + formatString(task.getDescription()) + "║");
        System.out.println("║   Fecha de Inicio: " + task.getStartDate() + "  ║");
        System.out.println("║   Fecha de Fin: " + task.getEndDate() + "  ║");
        System.out.println("║   Asignado a: " + formatString(task.getAssignedUser()) + "  ║");
        System.out.println("║   Estado: " + task.getState() + "  ║");
        System.out.println("║   Comentarios: " + task.getComments() + "  ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    /**
     * Este metodo muestra la información de los colaboradores.
     * @param user
     */
    public void showCollaborators(User user) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   Información de los Colaboradores     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║   Colaborador: " + formatString(user.getName()) + "    ║");
        System.out.println("║   Usuario: " + formatString(user.getUsername()) + "    ║");
        System.out.println("║   Email: " + formatString(user.getEmail()) + "    ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    /**
     * Este metodo muestra la información de todos los usuarios.
     * @param user
     */
    public void showAllUser(User user){
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           Lista de usuarios            ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║   Usuario: " + formatString(user.getUsername()) + "    ║");
        System.out.println("║   Email: " + formatString(user.getEmail()) + "    ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    /**
     * Este metodo te formatea una cadena de texto.
     * @param input Cadena de texto
     * @return La cadena de texto formateada
     */
    private String formatString(String input) {
        int maxLength = 38;
        if (input.length() > maxLength) {
            return input.substring(0, maxLength - 3) + "...";
        } else {
            return String.format("%-" + maxLength + "s", input);
        }
    }
}

