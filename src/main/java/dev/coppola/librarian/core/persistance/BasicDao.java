package dev.coppola.librarian.core.persistance;

import java.util.List;

public interface BasicDao<T> {
	T findById(long id);
	List<T> findAll();
	void save(T t);
	void delete(T t);
}
