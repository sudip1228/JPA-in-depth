package jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path="courses")//using data-rest. here course is uri.it works as /course.use localhost8080/courses.
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {//Always create interface to define spring-data jpa.
				//here course is entity and Long is a ID of course.Extends keyword means CourseSpringDataRepository is inheriting from JpaRepository
	
	//below are the custom made methods and queries for CRUD operations.
	//make sure you use the right keyword like; findBy,countBy,deleteBy. findByname or findByName both works fine.
	//when you declare these methods.make sure you use it at other classes.If you just declare method here and dont use that method in other class,the program will not run.
	List<Course> findByname(String name);
	/*
	List<Course> findByNameAndId(String name, Long id);

	

	List<Course> countByName(String name);

	List<Course> findByNameOrderByIdDesc(String name);

	List<Course> deleteByName(String name);

	@Query("Select  c  From Course c where name like '%100 Steps'")//jpql query
	List<Course> courseWith100StepsInName();//giving name to the query.

	@Query(value = "Select  *  From Course c where name like '%100 Steps'", nativeQuery = true)//native query
	List<Course> courseWith100StepsInNameUsingNativeQuery();

	@Query(name = "query_get_100_Step_courses")//named query
	List<Course> courseWith100StepsInNameUsingNamedQuery();*/
	
}
