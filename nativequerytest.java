package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//native query is a sql query that we know normally.
//we ave jpa,jsql query and native query to perform CRUD operation in database.They all have have different approach to perform CRUD operartion

@RunWith(SpringRunner.class)
@SpringBootTest(classes = jparunner.class)
public class nativequerytest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void nativequery_basic() {
		Query query = em.createNativeQuery("Select * from Course where is_deleted=0",Course.class);//0 represents false.Now,it shows only the courses which is not deleted.
		//native query dont follow or apply @where clause.so you have to add @where clause.
		List resultList = query.getResultList();
		logger.info("Select * from Course -> {}",resultList);
	}
	@Test
	public void nativequery_withparameter() {
		Query query = em.createNativeQuery("Select * from Course where id=?",Course.class);
		query.setParameter(1, 1001L);//setting id as a parameter.
		List resultList = query.getResultList();
		logger.info("Select * from Coursewhere id=? -> {}",resultList);
	}


	@Test
	public void nativequery_withnamedparameter() {
		Query query = em.createNativeQuery("Select * from Course where id= :identity",Course.class);
		query.setParameter("identity", 1001L);//setting id as a parameter.
		List resultList = query.getResultList();
		logger.info("Select * from Coursewhere id= :identity -> {}",resultList);
	}
	
	@Test
	@Transactional
	public void nativequery_toupdate() {
		Query query = em.createNativeQuery("Update Course set last_updated= sysdate()",Course.class);
		int noofrowsupdated=query.executeUpdate();//setting id as a parameter.
		logger.info("no. of rows updated -> {}",noofrowsupdated);
	}

	
}