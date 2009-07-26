package org.jtpd;

import org.jtpd.daos.idao.IStudentDAO;
import org.jtpd.domain.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
try {
    ApplicationContext factory = new FileSystemXmlApplicationContext("target\\classes\\application-context.xml");
    IStudentDAO studentDAO = (IStudentDAO) factory.getBean("studentDAO");
	Student student = new Student();
	student.setFirstName("taner");
	student.setLastName("diler");
	student.setAge(29);
	Integer id = studentDAO.saveOrUpdate(student);
	System.out.println("id : " + id);
} catch (Exception e) {
	e.printStackTrace();
}
    }
}
