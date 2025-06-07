package com.anurag.spring_data_jpa.repository;

import com.anurag.spring_data_jpa.entity.Course;
import com.anurag.spring_data_jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRespositoryTest {

    @Autowired
    private TeacherRespository teacherRespository;

    @Test
    public void saveTeacher(){

        Course course = Course.builder()
                .title("AI/ML")
                .credit(10)
                .build();

        Course coursePython = Course.builder()
                .title("Python")
                .credit(5)
                .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Joy")
                        .lastName("Lobo")
                        .course(List.of(course, coursePython))
                        .build();


        teacherRespository.save(teacher);
    }


}