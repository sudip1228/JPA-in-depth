package jpa;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass//cant use @Entity and @ Inheritance annotation when you use this annotation.It eliminates the inheritance relationship.and now this class is not an entity.but the inheritated class will have column of other table as well like table per class annotation.
//@Entity

//@Inheritance(strategy=InheritanceType.JOINED)//everybody gets the seperate table and column.Also, the foreign key is inlcuded in the inherited table.It slows donw the performance because it use join to provide details and using join is time consuming.Advantage is data integrity and data quality.
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)//seperate table for all the concrete class is created.Employee table is a abstract class.The column name of another table will also get inserted into inherited class.
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//the default is single table..It shows the part_time and full_time employee in a single table.Advantage is performance.
@DiscriminatorColumn(name="EmployeeType")//changing the name of the discriminatory column(the inherited class column)
public abstract class Employee {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	protected Employee() {
	}

	public Employee(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Employee[%s]", name);
	}
}