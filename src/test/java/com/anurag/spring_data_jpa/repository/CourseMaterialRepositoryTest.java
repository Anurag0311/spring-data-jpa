package com.anurag.spring_data_jpa.repository;

import com.anurag.spring_data_jpa.entity.Course;
import com.anurag.spring_data_jpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial(){

        Course course =
                Course.builder()
                        .title("AGI")
                        .credit(5)
                        .build();

        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                        .url("www.w3school.com")
                        .course(course)
                        .build();

        courseMaterialRepository.save(courseMaterial);
    }


    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        System.out.println(courseMaterials);
    }

}