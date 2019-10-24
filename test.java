



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jpa.CourseRepository;

@SpringBootApplication
public class test implements CommandLineRunner {
	private Logger log= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(test.class, args);

	}

	public void run(String... args) throws Exception {
		
	log.info("course 1001->{}",repo.findById(1001L));
		
		//log.info("inserting ->{}",repo.insert(new course("tara","berlin",new Date())));
		//log.info("inserting ->{}",repo.insert(new course("sachin","india",new Date())));

	//	log.info("updating 1003->{}",repo.update(new course(1003,"rabin","nyc",new Date())));
		//repo.deletebyid(1);
		//log.info("all users->{}",dao.findall());

	//	log.info("delete users->{}",dao.deletebyid(1002));
	//	System.out.println("deleted "+ dao.deletebyid(1002));
		
	
	
		
	
	}

}
