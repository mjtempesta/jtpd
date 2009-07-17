package tr.org.java.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tr.org.java.core.dao.GenericDAO;

public abstract class GenericHibernateDAO<T, ID extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, ID> {

	protected Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	public void setTemplate(HibernateTemplate hibernateTemplate) {
		super.setHibernateTemplate(hibernateTemplate);
	}

	@SuppressWarnings("unchecked")
	public T getById(ID id) {
		return (T) getHibernateTemplate().get(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	public T getById(ID id, boolean lock) {
		if (lock) {
			return (T) getHibernateTemplate().get(persistentClass, id, LockMode.UPGRADE);
		} else
			return getById(id);
	}

	@SuppressWarnings("unchecked")
	public T loadById(ID id) {
		return (T) getHibernateTemplate().load(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	public ID save(T entity) {
		return (ID) getHibernateTemplate().save(entity);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<T> entityCollection) {
		getHibernateTemplate().saveOrUpdateAll(entityCollection);
	}

	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public void deleteById(ID id) {
		getHibernateTemplate().delete(loadById(id));
	}

	public void deleteAll(Collection<T> entityCollection) {
		getHibernateTemplate().deleteAll(entityCollection);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(persistentClass));
	}


}
