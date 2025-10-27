package swingy.DAO.interfaceDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DAO<T> {
	public abstract Optional<T> getById(UUID id);

	public abstract List<T> getAll();

	public abstract boolean create(T entity);

	public abstract boolean update(T entity);

	public abstract boolean delete(T entity);

	public abstract boolean ifExist(T entity);
}
