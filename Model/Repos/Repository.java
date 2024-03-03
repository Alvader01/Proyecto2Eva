package Model.Repos;

import Model.Serializator;

import java.io.Serializable;
import java.util.Collection;

public abstract class Repository<T, K> implements Serializable {
    public static Repository load(String filename) {
        return Serializator.desearize(filename);
    }

    public boolean save(String filename) {
        return Serializator.serializable(this, filename);
    }

    public abstract T create(T data);

    public abstract T getById(K id);

    public abstract Collection<T> getAll();

    public abstract T update(T data);

    public abstract boolean delete(K username);
}
