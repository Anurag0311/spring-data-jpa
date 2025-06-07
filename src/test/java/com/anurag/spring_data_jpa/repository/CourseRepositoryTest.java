package com.anurag.spring_data_jpa.repository;

import com.anurag.spring_data_jpa.entity.Course;
import com.anurag.spring_data_jpa.entity.Student;
import com.anurag.spring_data_jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.SQLOutput;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses =
                courseRepository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithTeacher(){

        Teacher teacher =
                Teacher.builder()
                        .firstName("Frank")
                        .lastName("Sinatra")
                        .build();

        Course course =
                Course.builder()
                        .title("Networking")
                        .credit(5)
//                        .teacher(teacher)
                        .build();

        courseRepository.save(course);

    }

    //PAGINATION
    @Test
    public void findAllPaginated(){
        Pageable firstPagewithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPagewithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                courseRepository.findAll(secondPagewithTwoRecords).getContent();

        Long totalElements =
                courseRepository.findAll(secondPagewithTwoRecords).getTotalElements();

        long totalPages =
                courseRepository.findAll(secondPagewithTwoRecords).getTotalPages();

        System.out.println("totalPages="+totalPages);

        System.out.println("totalElements="+totalElements);

        System.out.println("courses = " + courses);
    }

    //PAGINATION WITH SORTING
    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(0, 2, Sort.by("title"));

        Pageable sortByCreditDesc =
                PageRequest.of(0, 2, Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));


        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();

        System.out.println("course = "+ courses);


    }

    @Test
    public void printFindByTitleCotaining(){
         Pageable firstPageTenRecords =
                 PageRequest.of(0, 10);

         List<Course> courses =
                 courseRepository.findByTitleContaining("A", firstPageTenRecords).getContent();

        System.out.println("courses :" + courses);

    }


    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Lizzo")
                .lastName("Lizz")
                .build();

        Student student =
                Student.builder()
                        .firstName("Abhishek")
                        .lastName("Singh")
                        .emailId("abhishek@gmail.com")
                        .build();

        Course course = Course.builder()
                .title("DS")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);
        courseRepository.save(course);
    }

}