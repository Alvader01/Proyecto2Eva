package View;

public class SubView {

    /**
     * Primer menu
     */
    public void menuOption_1(){
        System.out.println("1. Gestionar proyectos");
        System.out.println("2. Configuración de usuario");
        System.out.println("3. Cerrar session");
    }

    /**
     * Menu para gestionar proyectos
     */
    public void menuManageProjects_1(){
        System.out.println("1. Crear proyecto");
        System.out.println("2. Modificar proyecto");
        System.out.println("3. Mostrar proyectos");
        System.out.println("4. Borrar proyecto");
        System.out.println("5. Volver");
    }

    /**
     * Menu para configurar usuario
     */
    public void menuConfigUser_1(){
        System.out.println("1. Cambiar nombre");
        System.out.println("2. Cambiar nombre de usuario");
        System.out.println("3. Cambiar contraseña");
        System.out.println("4. Cambiar email");
        System.out.println("5. Borrar usuario");
        System.out.println("6. Volver");
    }

    /**
     * Menu para modificar proyecto
     */
    public void menuModifyProject(){
        System.out.println("1. Cambiar nombre del proyecto");
        System.out.println("2. Añadir colaborador");
        System.out.println("3. Borrar colaborador");
        System.out.println("4. Modificar tareas");
        System.out.println("5. Volver");
    }

    /**
     * Menu para gestionar tareas
     */
    public void menuManageTasks(){
        System.out.println("1. Añadir tarea");
        System.out.println("2. Mostrar tareas");
        System.out.println("3. Cambiar nombre de la tarea");
        System.out.println("4. Eliminar tareas");
        System.out.println("5. Cambiar el estado");
        System.out.println("6. Cambiar el usuario asignado");
        System.out.println("7. Crear comentario");
        System.out.println("8. Volver");

    }

    /**
     * Menu para espaciar
     */
    public static void showGap() {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("                                           ");
        System.out.println("                                           ");
        System.out.println("                                           ");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - -");
    }
}
