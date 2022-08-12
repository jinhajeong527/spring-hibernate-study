package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
/* 
 * JpaRepository를 상속했기 때문에
 * save(), findOne(), findById(), findAll(), count(), delete(), deleteById()..등과 같은 메서드들은
 * 구현 없이 바로 사용이 가능하다.
 * custom finder methods는 따로 작성함 : The implementation is plugged in by Spring Data JPA automatically.
 */

    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}
