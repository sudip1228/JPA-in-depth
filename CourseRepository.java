package jpa;




import java.util.List;

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
public class CourseRepository {
	private Logger logger= LoggerFactory.getLogger(this.getClass());

	
	//connect to the database
	@Autowired //you can also use @PersistenceContext instead.
	private EntityManager em;
	
	public Course findById(Long id){
		return em.find(Course.class, id);
	
}
	public void deleteById(Long id){
		Course course = findById(id);
		em.remove(course);
}
	
public Course save(Course c) {
		//you can use save method to update and insert instead of using persist or merge.
		if (c.getId()==null) {
			em.persist(c);//inserting the data
		} else {
			em.merge(c);//updating the data
		}
		
		return c;
	}
/*
public void playWithEntityManager() {//playing with Entitymanager.this is a update operation
	Course course = new Course("Web Services in 100 Steps");
	em.persist(course);
	course.setName("Web Services in 100 Steps - Updated");
	
}*/

/*
public void playWithEntityManager() {
	
	Course course1 = new Course("Web Services in 100 Steps");
	
	em.persist(course1);
	//em.flush();
	Course course2 = new Course("Angular Js in 100 Steps");
	em.persist(course2);
	em.flush();//(flush)-changes upto this point is sent to database.it means saving to database.

	//em.detach(course2);//course2 is detached from this point(but above this line if course2 had any update,then it will be updated)  and will not be updated but course1 is updated because course2 is not tracked by entity manager.Detach clear something but not everything like clear().
	//em.detach(course1);//course 1 is also not updated along with course2.
	//em.clear();//clears everything in the entity  manager which means the below code is not printed.it clear everything.

	course2.setName("Angular Js in 100 Steps - Updated");
	//em.flush();
	course1.setName("Web Services in 100 Steps - Updated");
	em.refresh(course1);//all the changes done to course1 is lost and no any update is stored in the database.
	em.refresh(course2);//changes to course2 is also lost.
	em.flush();
	
}
*/
public void playWithEntityManager()
{
	Course course1 = new Course("Web Services in 100 Steps");
	em.persist(course1);
	
	Course course2 = new Course("JPA in 50 Steps-updated");
	em.persist(course2);
	
}

public void addHardcodedReviewsForCourse() {
	//get the course 10003
	Course course = findById(10003L);
	logger.info("course.getReviews() -> {}", course.getReviews());
	
	//add 2 reviews to it
	Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff.");	//using enum values.
	Review review2 = new Review(ReviewRating.FIVE, "Hatsoff.");//using enum values.
	
	//setting the relationship
	course.addReview(review1);
	review1.setCourse(course);
	
	course.addReview(review2);
	review2.setCourse(course);
	
	//save it to the database
	em.persist(review1);
	em.persist(review2);
}
public void addReviewsForCourse(Long courseId, List<Review> reviews) {		//the above hardcoded method is summarized to this method for easiness.
	Course course = findById(courseId);
	logger.info("course.getReviews() -> {}", course.getReviews());
	for(Review review:reviews)
	{			
		//setting the relationship
		course.addReview(review);
		review.setCourse(course);
		em.persist(review);
	}

}
}
