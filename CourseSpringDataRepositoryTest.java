package jpa;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest(classes = jparunner.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository repository;

	@Test
	public void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);//this method returns optional back.so we assign it to the optional variable by doing right click and pressing assign to new local variable.
		//optional provides the way to check whether course exists or not.
		assertTrue(courseOptional.isPresent());
	}

	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);//this findbyid method is something present in jparepository which CourseSpringDataRepository extends.
		assertFalse(courseOptional.isPresent());
	}

	@Test
	public void playingAroundWithSpringDataRepository() {
		//Course course = new Course("Microservices in 100 Steps");
		//repository.save(course);

		//course.setName("Microservices in 100 Steps - Updated");
		//repository.save(course);
		logger.info("Courses -> {} ", repository.findAll());//finds all the courses.now no need to write long queries when you use springdata jpa.
		logger.info("Count -> {} ", repository.count());// shows how many courses are retrieved.
	}
/*
	@Test
	public void sort() {
		
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		logger.info("Sorted Courses -> {} ", repository.findAll(sort));
		//Courses -> [Course[JPA in 50 Steps], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]] 
	}
*/
	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);//prints page 1 to 3.It means prints 3 rows of a table.
		
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page -> {} ", firstPage.getContent());//shows contents.
		
		Pageable secondPageable = firstPage.nextPageable();//prints next page or row.
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {} ", secondPage.getContent());
		
	
}
	
	
	
	@Test
	public void findUsingName() {
		logger.info("FindByName -> {} ", repository.findByname("JPA in 50 Steps"));
	}

	@Test
	public void findUsingStudentsName() {
		logger.info("findUsingStudentsName -> {} ", repository.findByname("Ranga"));
	}
}