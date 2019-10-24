

package jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest( classes= jparunner.class)
class repeat_vako_ho_yo {

	private Logger log= LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repo;
	@Test
	void findbyidbasics() {
		//log.info("test run");
		Course c= repo.findById(1001L);
		assertEquals("melisha",c.getName());
	}
	
	@Test
	@DirtiesContext//it resets the data into its previous state so that the further test is not affected.
	public void deleteById_basic() {
		repo.deleteById(1002L);
		assertNull(repo.findById(1002L));//checks whether something is null or not!!here 1002L id no. is null.so it means it is deleted
	}	
	
	@Test
	@DirtiesContext
	public void save_basic() {

		// get a course
		Course course = repo.findById(1001L);
		assertEquals("melisha", course.getName());

		// update details
		course.setName("melisha - Updated");

		repo.save(course);

		// check the value
		Course course1 = repo.findById(1001L);
		assertEquals("melisha - Updated", course1.getName());
	}
	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repo.playWithEntityManager();
	}


}
