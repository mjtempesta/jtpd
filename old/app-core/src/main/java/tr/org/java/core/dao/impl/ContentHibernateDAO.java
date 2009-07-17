package tr.org.java.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import tr.org.java.core.dao.ContentDAO;
import tr.org.java.core.domain.Content;

@Repository
public class ContentHibernateDAO extends GenericHibernateDAO<Content, Long> implements ContentDAO {

}
