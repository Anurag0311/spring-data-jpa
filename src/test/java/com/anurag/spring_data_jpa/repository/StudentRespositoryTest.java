package com.anurag.spring_data_jpa.repository;

import com.anurag.spring_data_jpa.entity.Guardian;
import com.anurag.spring_data_jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRespositoryTest {

    @Autowired
    private StudentRespository studentRespository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("anurag@gmail.com")
                .firstName("Anurag")
                .lastName("Singh")
//                .guardianName("Anita")
//                .guardianEmail("anita@gmail.com")
//                .guardianMobile("9898989898")
                .build();

        studentRespository.save(student);
    }

    @Test
    public void saveStudendWithGuardianDetails(){

        Guardian guardian = Guardian.builder()
                .name("Sunita")
                .email("sunita@gmail.com")
                .mobile("9080808982")
                .build();

        Student student = Student.builder()
                .firstName("Shivam")
                .lastName("kumar")
                .emailId("shivam@gmail.com")
                .guardian(guardian)
                .build();

        studentRespository.save(student);
    }


    @Test
    public void getAllStudent(){
        List<Student> studentList =
                studentRespository.findAll();

        System.out.println("StudentList:- "+studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRespository.findByFirstName("shivam");
        System.out.println("STUDENTS:-"+ students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRespository.findByFirstNameContaining("an");
        System.out.println("STUDENTS:-"+ students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students =
                studentRespository.findByGuardianName("Anita");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentBasedOnFirstNameAndLastName(){
        Student student =
                studentRespository.findByFirstNameAndLastName("shivam","kumar");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentBasedOnEmailAddress(){
        Student student =
                studentRespository.getStudentByEmailAddress("shivam@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstNameBasedOnEmailAddress(){
        String student =
                studentRespository.getStudentFirstNameByEmailAddress("shivam@gmail.com");
        System.out.println("student = " + student);
    }


    @Test
    public void printStudentBasedOnEmailAddressNative(){
        Student student =
                studentRespository.getStudentByEmailAddressNative("shivam@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentBasedOnEmailAddressNativeNamedParam(){
        Student student =
                studentRespository.getStudentByEmailAddressNativeNamedParam("shivam@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest(){
        studentRespository.updateStudentNameByEmailId("Shubham", "shivam@gmail.com");
    }
}