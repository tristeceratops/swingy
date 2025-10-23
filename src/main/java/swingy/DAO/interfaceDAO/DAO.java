package swingy.DAO.interfaceDAO;

import java.util.List;

public interface DAO<T> {
	public abstract T getById(int id);

	public abstract List<T> getAll();

	public abstract boolean create(T entity);

	public abstract boolean update(T entity);

	public abstract boolean delete(T entity);

	public abstract boolean ifExist(T entity);
}
