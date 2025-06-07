package com.anurag.spring_data_jpa.repository;


import com.anurag.spring_data_jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRespository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);


    public List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String lastName);


    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String EmailId);

    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String EmailId);

    //NATIVE QUERIES
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String EmailId);

    //NATIVE QUERIES NAMED PARAM
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(
            @Param("emailId") String EmailId
    );


    @Modifying//USED WHEN MODIFYING A ROW IN DB
    @Transactional// ALL the CHANGES WILL ONLY BE COMMITTED WHEN ALL THE WORKS
    // FINE IF AN ERROR OCCUR ALL THE THINGS WILL BE ROLLEDBACK
    //CAN BE USED FOR METHOD AND CLASSES
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);

}
