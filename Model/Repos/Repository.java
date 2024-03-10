package Model.Repos;

import Interfaces.Repos.IRepository;
import Model.Serializator;

import java.io.Serializable;
import java.util.Collection;

public abstract class Repository<T, K> implements Serializable, IRepository<T, K> {

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
    @Override
    public boolean save(String filename) {
        return Serializator.serializable(this, filename);
    }

    /**
     * Crea un nuevo objeto
     *
     * @param data Los datos del objeto
     * @return El nuevo objeto
     */
    @Override
    public abstract T create(T data);

    /**
     * Obtener un objeto por su identificador
     *
     * @param id El identificador
     * @return El objeto encontrado
     */
    @Override
    public abstract T getById(K id);

    /**
     * Obtener todos los objetos de la lista
     *
     * @return La lista de objetos
     */
    @Override
    public abstract Collection<T> getAll();

    /**
     * Actualizar un objeto
     *
     * @param data Los datos del objeto
     * @return El objeto actualizado
     */
    @Override
    public abstract T update(T data);

    /**
     * Eliminar un objeto
     *
     * @param id El identificador del objeto
     * @return True si se ha eliminado el objeto, false en caso contrario
     */
    @Override
    public abstract boolean delete(K id);
}
