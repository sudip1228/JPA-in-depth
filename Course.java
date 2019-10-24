package jpa
;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
//imagine this class is used for creating a table and columns.
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





//@Table(name="coursedetails")//now the name of table is coursedetails and name of entity(class) is Course.But now, you have to rename the table name in data.sql.
@Entity//used for jpa.With this annotation,this class becomes same as table in sql.It is used to map rows in the table.Objects are also called as an entity.
//@NamedQuery(name="GetCourses",query="select c from Course c")...single queries
//@NamedQuery(name="Get100",query="Select  c  From Course c where name like '%100 Steps")..these are just single queries.
@NamedQueries(value= 
{@NamedQuery(name="GetCourses",query="select c from Course c"),
@NamedQuery(name = "query_get_all_courses_join_fetch", 
		query = "Select  c  From Course c JOIN FETCH c.students s"),//joining course and student using join fetch which helps to give us a eager fetch.
@NamedQuery(name="Get100",query="Select  c  From Course c where name like '%100 Steps'")
}
)//..writing multiple queries.
@Cacheable//using second level caching.
@SQLDelete(sql="update course set is_deleted=true where id=?")//using soft delete..soft delete means storing some data in history even after delete.This is a hibernate annotation. 
@Where(clause="is_deleted = false")//it is hibernate query.checking whether the data is deleted or not.if deleted it gives true back which means 1 in the log and gives 0 in the log when it is not deleted which means false.
//above where clause retrieves only data which has is_deleted=false.It shows as 0 in the log.
public class Course {
				
	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);//static is used to indicate that this is not mapped by @entity.
	//we are using loggerfactory beccause we cannot use 'this.class' with static keyword.
	
	@Id
	@GeneratedValue
private Long id;

	//@Column(name= "fullname", nullable=false)//same as @Table.Now you have to also rename the column name in data.sql. Nullable= false means name cannot have anull value.
	//you can check other features of @Column by pressing ctrl.
	@Column(nullable=false)
private String name;
	
	@OneToMany(mappedBy="course")//one to many means one course can have many reviews.
	
	private List<Review> reviews = new ArrayList<>();//arraylist is used because we can have multiple reviews in a course.
	
	@ManyToMany(mappedBy="courses")
	//@JsonIgnore//..dont know why not working.It suppose to manage or display the data "/courses" in data-rest 'localhost8080/courses' in a properway.
	private List<Student> students = new ArrayList<>();
	
	@UpdateTimestamp//it is a hibernate annotation not jpa.It shows  lastupdated time..
	private LocalDateTime last_updated_date;
	
	@CreationTimestamp//shows a created date.
	private LocalDateTime created_date;
	
	
	private boolean isDeleted;
	
	@PreRemove//sets the is_deleted=true in the cache because hibernate doesnot know what is happening. so @sqldelete and @where clause is not enough to store data in cache.
	private void preRemove(){//These annotations are also available besides@preremove.You can use these annotation on your methods to get desired result.@postpersist,@postload,@postupdate,@prepersist,@preupdate,@postremove.
		LOGGER.info("Setting isDeleted to True");
		this.isDeleted = true;
	}

protected Course() {	
}//mandatory to jpa
public Course(Long id, String name) {
	
	this.id = id;
	this.name = name;
	
}
public Course( String name) {
	
	
	this.name = name;
	
}
public Long getId() {
	return id;
}

public void setName(String name) {
	this.name = name;
}
public String getName() {
	return name;
}
public List<Review> getReviews() {
	return reviews;
}

public void addReview(Review review) {//similar to set method.addreview is used to safeguard a code.
	this.reviews.add(review);
}

public void removeReview(Review review) {
	this.reviews.remove(review);//removes reviews
}

public List<Student> getStudents() {
	return students;
}

public void addStudent(Student student) {
	this.students.add(student);
}

@Override
public String toString() {
	return "course [id=" + id + ", name=" + name + "]";//be careful what field to use in here because it affects your performance.This code helps what to display or which fiel to use.
}

	
}
