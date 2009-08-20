package org.jtpd;

import java.util.Date;

import org.jtpd.dao.IUserDAO;
import org.jtpd.domain.model.Story;
import org.jtpd.domain.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hello world!
 * 
 */
@Transactional
public class App {

	public static void main(String[] args) {
//		try {
//			ApplicationContext factory = new FileSystemXmlApplicationContext(
//					"target\\classes\\application-context.xml");
//			IStudentDAO studentDAO = (IStudentDAO) factory
//					.getBean("studentDAO");
//			IUserDAO userDAO = (IUserDAO) factory.getBean("userDAO");
//			IUserExtraDAO userExtraDAO = (IUserExtraDAO) factory
//					.getBean("userExtraDAO");
//			Student student = new Student();
//			student.setFirstName("taner");
//			student.setLastName("diler");
//			student.setAge(29);
//			User user = new User();
//			user.setFirstName("taner");
//			user.setLastName("diler");
//			user.setUsername("jtaz35");
//			user.setPhone("5422953035");
//			user.setEmail("taner@jtpd.org");
//			user.setPassword("XXX");
//			Story story = new Story(user);
//			story.setBody("XXX");
//			story.setTitle("YYY");
//			story.setCreatedDate(new Date());
//			story.setStatus(StoryStatus.ONWAITING);
//			story.setType(StoryType.ARTICLE);
//			Integer id = studentDAO.saveOrUpdate(student);
//			Integer userId = userDAO.saveOrUpdate(user);
//			// userExtraDAO.saveOrUpdate(extra);
//			System.out.println("id : " + id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
