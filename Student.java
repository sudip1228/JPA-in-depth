package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


//To understand jpa.understand these concepts: persistence context,Entitymanager and transaction.
//persistence context carries the entity in every step of program.We use entitymanager which is interface of persistencecontext to talk to persistence context.


@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
	
	//@OneToOne//this annotation is present in jpa. Student can have one passport and each passport is associated with one student so it is one to one mapping.
	@OneToOne(fetch= FetchType.LAZY)//In log,you only see student details now and you wont see the passport details when you look for student details.
	
	//@one to one and @Many to one ---> default fetch is EAGER.@One to many and @many to many ---->default fetch is LAZY
	//Under eager fetch, the table is joint while showing the data.In eager fetch, tables are not joint while showing the data in log.
	private Passport passport;
	@Embedded//address class is embedded in student.
	private Address address;
	
	@ManyToMany
	@JoinTable(name="STUDENT_COURSE",joinColumns = @JoinColumn(name = "STUDENT_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
	

	private List<Course> courses = new ArrayList<>();
	

	protected Student() {
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);//similar to this.course=course.
	}


	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}
}