package com.anurag.spring_data_jpa.repository;

import com.anurag.spring_data_jpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRespository extends JpaRepository<Teacher, Long> {


}
