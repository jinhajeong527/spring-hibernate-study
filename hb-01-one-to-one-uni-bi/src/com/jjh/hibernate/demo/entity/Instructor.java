package com.jjh.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity//클래스 엔티티로 어노테이트 해준다
@Table(name="instructor")// 디비의 instructor 테이블과 맵핑해준다.
public class Instructor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	//모든 operation에 적용되는 관계가 된다.(persisting, deleting, updating..)
	@OneToOne(cascade=CascadeType.ALL) 
	//instructor_detail_id는 Instructor 테이블에 정의되어 있다. 이 외부키는 instructor_detail 테이블의 id 필드를 참조한다.
	@JoinColumn(name="instructor_detail_id")
	private InstructorDetail instructorDetail;

	@OneToMany(mappedBy="instructor", cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
			  						  CascadeType.MERGE, CascadeType.REFRESH})
	private List<Course> courses;
	
	public Instructor() {
	}

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public void add(Course course) {
		
		if(courses == null) {
			courses = new ArrayList<>();
		}
		
		courses.add(course);
		
		course.setInstructor(this);
		
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	}
	

}
