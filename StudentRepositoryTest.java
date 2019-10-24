package jpa;

import javax.persistence.EntityManager;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = jparunner.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private EntityManager em;

	//Session & Session Factory

	
	//EntityManager & Persistence Context
	//Transaction
	/*
	@Test
	public void someTest() {
		repository.someOperationToUnderstandPersistenceContext();
	}

*/
	@Test
	@Transactional//either a whole transaction is occured or nothing will occur at all.use when you want to update data.
	//Eager fetching-It is the situation where you get details of all the student field or passport field even if you have not requested for it.It occurs in one-to-one mapping.
	//here we are also seeing passport details even though we only want student details.We dont need to see passport details in log.
	//use @transactional to connect to database.If you dont use entitymanager to fire query,then you need @Transactional.
	public void retrieveStudentAndPassportDetails() {//transaction started
		Student student = em.find(Student.class, 20001L);//retrieving data
		logger.info("student -> {}", student);
		logger.info("passport -> {}",student.getPassport());
	}//transaction closed
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("student -> {}", passport.getStudent());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		
		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses());
	}
	@Test
	@Transactional
	public void setAddressDetails() {
		Student student = em.find(Student.class, 20001L);
		student.setAddress(new Address("No 101", "Some Street", "Hyderabad"));
		em.flush();
	}
	
}