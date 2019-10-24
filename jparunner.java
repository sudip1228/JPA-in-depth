package jpa;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("jpa")
public class jparunner implements CommandLineRunner {
	private Logger log= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(jparunner.class, args);

	}

	public void run(String... args) throws Exception {
		
	//log.info("course 10001->{}",repo.findbyid(10001L));
	//repo.deleteById(10001L);
	//repo.save(new Course("Computer"));
	//studentRepository.saveStudentWithPassport();
	//repo.playWithEntityManager();
		
		//courseRepository.addHardcodedReviewsForCourse();
		/*
		List<Review> reviews = new ArrayList<>();
		
		reviews.add(new Review("5", "Great Hands-on Stuff."));	
		reviews.add(new Review("5", "Hatsoff."));
		courseRepository.addReviewsForCourse(10003L, reviews );	
		
		//studentRepository.insertHardcodedStudentAndCourse();
		//studentRepository.insertStudentAndCourse(new Student("Jack"), 
			//	new Course("Microservices in 100 Steps")); 
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));//bigdecimal is a class so we use new for it.
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));

	//	logger.info("All Employees -> {}", employeeRepository.retrieveAllEmployees());
		
		logger.info("Full Time Employees -> {}", 
				employeeRepository.retrieveAllFullTimeEmployees());
		
		logger.info("Part Time Employees -> {}", 
				employeeRepository.retrieveAllPartTimeEmployees());*/

	}

}
