package jpa;




import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//imagine we write a sql query here to perform CRUD operations
//repository
@Repository
//transaction
@Transactional//used when data is changed in the repository such as delete,update or insert.For retrieving,it is not required to put this annotation.
public class StudentRepository {
	private Logger log= LoggerFactory.getLogger(this.getClass());

	
	//connect to the database
	@Autowired //you can also use @PersistenceContext instead.
	EntityManager em;
	
	public Student findbyid(Long id){
		return em.find(Student.class, id);
	
}
	public void deleteById(Long id){
		Student student = findbyid(id);
		em.remove(student);
}
	
public Student save(Student c) {
		
		if (c.getId()==null) {
			em.persist(c);//inserting the data
		} else {
			em.merge(c);//updating the data
		}
		
		return c;
	}
public void saveStudentWithPassport() {
	Passport passport = new Passport("Z123456");
	em.persist(passport);

	Student student = new Student("Mike");

	student.setPassport(passport);//sets passport id number of student.It is the same number from the passport class.
	em.persist(student);	//now student have passport and name both updated.
}

public void insertHardcodedStudentAndCourse(){
	Student student = new Student("Jack");
	Course course = new Course("Microservices in 100 Steps");
	em.persist(student);
	em.persist(course);
	
	student.addCourse(course);
	course.addStudent(student);
	em.persist(student);
}
public void insertStudentAndCourse(Student student, Course course){
	//Student student = new Student("Jack");
	//Course course = new Course("Microservices in 100 Steps");
	student.addCourse(course);
	course.addStudent(student);

	em.persist(student);
	em.persist(course);
}

}
