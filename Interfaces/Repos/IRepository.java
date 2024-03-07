package Interfaces.Repos;


import java.util.Collection;

public interface IRepository<T, K> {
    public boolean save(String filename);
    public T create(T data);
    public T getById(K id);
    public Collection<T> getAll();
    public T update(T data);
    public boolean delete(K id);
}
