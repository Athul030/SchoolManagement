package com.athul.schoolmanagement.repository;

import com.athul.schoolmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);
    List<Student> findByClassName(String className);

    // searched using spring data JPA
    List<Student> findByNameContaining(String name);

    // searched using spring data JPQL query
    @Query("SELECT s FROM Student s WHERE s.className LIKE %?1%")
    List<Student> findByClassNameContaining(String className);

}
