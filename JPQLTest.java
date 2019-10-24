package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//full form of jpql is java persistence query language
//jpql uses entity where sql uses table for the query.here entity is Course class.
@RunWith(SpringRunner.class)
@SpringBootTest(classes = jparunner.class)
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpql_basic() {
		//Query query = em.createQuery("Select  c  From Course c");//creating query. this query is similar to select * from Course
		Query query = em.createNamedQuery("GetCourses");
		List resultList = query.getResultList();//executing query
		logger.info("Select  c  From Course c -> {}",resultList);
	}

	@Test
	public void jpql_typed() {
		//TypedQuery<Course> query = em.createQuery("Select  c  From Course c", Course.class);//here the use of generics is made.The class Course is called type.
		TypedQuery<Course> query = em.createNamedQuery("GetCourses", Course.class);
		List<Course> resultList = query.getResultList();//Typed query is better than above general query in practice.
		
		logger.info("Select  c  From Course c -> {}",resultList);
	}

	@Test
	public void jpql_where() {
		//TypedQuery<Course> query = em.createQuery("Select  c  From Course c where name like '%100 Steps'", Course.class);
		TypedQuery<Course> query = em.createNamedQuery("Get100", Course.class);

		List<Course> resultList = query.getResultList();
		
		logger.info("Select  c  From Course c where name like '%100 Steps'-> {}",resultList);
		//[Course[Web Services in 100 Steps], Course[Spring Boot in 100 Steps]]
	}
	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);//jpql query courses which donot have any students.
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		
	}
	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);//courses with atleast two students.JPQL query.
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		
	}
	
	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);//ordering the students in descending order.
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);//jpql query for courses for student which contains '1234' passport number in the middle.
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	//following are some jpql clause you can use.
	//like
		//BETWEEN 100 and 1000
		//IS NULL
		//upper, lower, trim, length
	
	//JOIN => Select c, s from Course c JOIN c.students s...similar to inner join
		//LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
		//CROSS JOIN => Select c, s from Course c, Student s..shows evrything on the all tables.example if you have 2 rows and 2 columns.it shows total of 2*2=4 rows.
		//3 and 4 =>3 * 4 = 12 Rows....cross join example
		@Test
		public void join(){
			Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
			List<Object[]> resultList = query.getResultList();
			logger.info("Results Size -> {}", resultList.size());//checks how many rows are there.
			for(Object[] result:resultList){//here result contains data of both student and course.
				logger.info("Course{} Student{}", result[0], result[1]);//result[0] is for course and result[1] is for student.
			}
		}

		@Test
		public void left_join(){
			Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
			List<Object[]> resultList = query.getResultList();
			logger.info("Results Size -> {}", resultList.size());
			for(Object[] result:resultList){
				logger.info("Course{} Student{}", result[0], result[1]);
			}
		}

		@Test
		public void cross_join(){
			Query query = em.createQuery("Select c, s from Course c, Student s");
			List<Object[]> resultList = query.getResultList();
			logger.info("Results Size -> {}", resultList.size());
			for(Object[] result:resultList){
				logger.info("Course{} Student{}", result[0], result[1]);
			}
			
			
		}	
		

}