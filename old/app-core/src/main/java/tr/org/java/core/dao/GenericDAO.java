package tr.org.java.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

	public T getById(ID id, boolean lock);

	public T getById(ID id);

	public T loadById(ID id);

	public List<T> findAll();

	public ID save(T entity);

	public void update(T entity);

	public void saveOrUpdate(T entity);

	public void saveOrUpdateAll(Collection<T> entityCollection);

	public void delete(T entity);

	public void deleteById(ID id);

	public void deleteAll(Collection<T> entityCollection);

}

