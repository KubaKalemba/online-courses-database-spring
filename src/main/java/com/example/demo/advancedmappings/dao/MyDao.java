package com.example.demo.advancedmappings.dao;

import com.example.demo.advancedmappings.entity.Course;
import com.example.demo.advancedmappings.entity.Instructor;
import com.example.demo.advancedmappings.entity.InstructorDetail;
import com.example.demo.advancedmappings.entity.Student;

import java.util.List;

public interface MyDao {

    void save(Instructor instructor);

    void save(Course course);

    Instructor findInstuctorById(int id);

    void delete(int id);

    void deleteDetails(int id);

    InstructorDetail findInstructorDetailById(int id);

    List<Course> findCoursesByInstructor(int id);

    public Instructor findCoursesByInstructorJoinFetch(int id);

    void update(Instructor instructor);

    void update(Course course);

    void update(Student student);

    Course findCourseById(int id);

    void deleteInstructor(int id);

    void deleteCourseById(int id);

    Course findCourseAndReviewsById(int id);

    Course findCourseAndStudentsById(int id);

    Student findStudentAndCourseById(int id);

    void deleteStudentById(int id);

}
