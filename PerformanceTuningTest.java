package jpa;

import java.util.List;


import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

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
public class PerformanceTuningTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	public void creatingNPlusOneProblem() {//this is a problem with hibernate.
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses", Course.class)
				.getResultList();
		for(Course course:courses){
			logger.info("Course -> {} Students -> {}",course, course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblem_EntityGraph() {//solving problem of lazy fetch.here this code enables the eager fetch.

		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
		
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph)
				.getResultList();
		
		for(Course course:courses){
			logger.info("Course -> {} Students -> {}",course, course.getStudents());
		}
	}

	@Test
	@Transactional
	public void solvingNPlusOneProblem_JoinFetch() {//alternative way to solve problemof lazy fetch.
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
				.getResultList();
		for(Course course:courses){
			logger.info("Course -> {} Students -> {}",course, course.getStudents());
		}
	}

}