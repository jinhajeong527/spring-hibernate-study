package com.jjh.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//클래스 엔티티로 어노테이트 하고, 디비 테이블에 맵핑한다.
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)// auto increment 도와준다.
	@Column(name="id")
	private int id;
	
	@Column(name="youtube_channel")//실제 테이블의 컬럼이름
	private String youtubeChnnel;
	
	@Column(name="hobby")
	private String hobby;
	
	//Instructor field 추가한다.
	//uni direction 하고 싶으면 이부분 없애면 된다.
	//bi direction일 때 필요한  Instructor field
	//Instructor 클래스의 instructorDetail 프로퍼티 참조하게 된다. 
	@OneToOne(mappedBy = "instructorDetail", 
			  cascade= { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Instructor instructor;
	

	public InstructorDetail() {
	}
	
	//아이디는 auto increment로 테이블 생성되었으므로 id는 생성자 인자에서 제외한다.
	public InstructorDetail(String youtubeChnnel, String hobby) { 
		this.youtubeChnnel = youtubeChnnel;
		this.hobby = hobby;
	}
	
	//getter, setter 만들기
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYoutubeChnnel() {
		return youtubeChnnel;
	}

	public void setYoutubeChnnel(String youtubeChnnel) {
		this.youtubeChnnel = youtubeChnnel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	//for bi directional
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	//toString() 오버라이딩
	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youtubeChnnel=" + youtubeChnnel + ", hobby=" + hobby + "]";
	}
	
	
	

}
