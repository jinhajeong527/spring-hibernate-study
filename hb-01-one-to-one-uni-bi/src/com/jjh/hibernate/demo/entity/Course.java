package com.jjh.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
						  CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")//Course 테이블에 있는 컬럼이다.
	private Instructor instructor;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")//review table에 있는 컬럼으로 course 테이블의 id 컬럼으로 pointing back 한다.
	private List<Review> reviews;
	
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
						   CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
			name="course_student",
			joinColumns =@JoinColumn(name="course_id"),
			inverseJoinColumns =@JoinColumn(name="student_id")
			)
	private List<Student> students;
	
	public Course() {
	}
	
	public Course(String title) { // id: auto-generated, Instructor는 나중에 값 assign 해줄 것이므로
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}
	
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	//add a student to a course
	public void AddStudent(Student theStudent) {
		if(students == null) {
			students = new ArrayList<Student>();
		}
		students.add(theStudent);
	}

	//add support method for adding review
	public void addReview(Review theReview) {
		
		if(reviews == null) {
			reviews = new ArrayList<Review>();
		}
		reviews.add(theReview);
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
	
	
	
	

}
