/**
 * 
 */
package org.jtpd;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author tdiler
 *
 */
public class EntityManagerFactory {
	private final SessionFactory sf = new AnnotationConfiguration().configure(new File("src\\main\\resources\\persistence.xml")).buildSessionFactory();;
	public Session getEntityManager(){
		return sf.openSession(); 
	}
}
