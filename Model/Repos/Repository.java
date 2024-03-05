package Model.Repos;

import Model.Serializator;

import java.io.Serializable;
import java.util.Collection;

public class Repository implements Serializable {

    /**
     * Carga un objeto desde un archivo
     *
     * @param filename El nombre del archivo desde el cual se cargará el objeto
     * @return El objeto cargado
     */
    public static Repository load(String filename) {
        return Serializator.desearize(filename);
    }

    /**
     * Guarda el objeto en un archivo
     *
     * @param filename El nombre del archivo donde se guardara el objeto
     * @return True si el objeto se guardó, false en caso contrario
     */
    public boolean save(String filename) {
        return Serializator.serializable(this, filename);
    }

}
