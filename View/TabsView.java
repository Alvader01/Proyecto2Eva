package View;

import Interfaces.View.ITabs;
import Model.Project;

public class TabsView implements ITabs {

    /**
     * Este metodo lo que hace es mostrar todos los proyectos, con su Nombre, Descripción y creador del Proyecto.
     */
    @Override
    public void showTabs() {
        System.out.println("-------------------------------");
        System.out.println(RepoProject.);
        System.out.println("-------------------------------");
    }
}
