package com.example.demo.advancedmappings.dao;

import com.example.demo.advancedmappings.entity.Course;
import com.example.demo.advancedmappings.entity.Instructor;
import com.example.demo.advancedmappings.entity.InstructorDetail;
import com.example.demo.advancedmappings.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyDaoImpl implements MyDao {

    EntityManager entityManager;

    @Autowired
    public MyDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Instructor findInstuctorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Instructor temp = entityManager.find(Instructor.class, id);
        entityManager.remove(temp);
    }

    @Override
    @Transactional
    public void deleteDetails(int id) {
        InstructorDetail temp = entityManager.find(InstructorDetail.class, id);

        //we need to set instructor detail to null that to enable one side deletion
        // (deletion of instructor details without deleting corresponding instructor)
        temp.getInstructor().setInstructorDetail(null);


        entityManager.remove(temp);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    public List<Course> findCoursesByInstructor(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public Instructor findCoursesByInstructorJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                + "JOIN FETCH i.courseList "
                + "where i.id = :data",
                Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void deleteInstructor(int id) {
        Instructor i = entityManager.find(Instructor.class, id);
        List<Course> courseList = i.getCourseList();
        for(Course c : courseList) {
            c.setInstructor(null);
        }
        entityManager.remove(i);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course c = entityManager.find(Course.class, id);
        entityManager.remove(c);
    }

    @Override
    public Course findCourseAndReviewsById(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "join fetch c.reviews " +
                        "where c.id = :data", Course.class
        );

        query.setParameter("data", id);
        Course course = query.getSingleResult();

        return course;


    }

    @Override
    public Course findCourseAndStudentsById(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "join fetch c.studentList " +
                        "where c.id = :data", Course.class
        );

        query.setParameter("data", id);
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCourseById(int id) {

        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s " +
                        "join fetch s.courseList " +
                        "where s.id = :data", Student.class
        );

        query.setParameter("data", id);
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student s = entityManager.find(Student.class, id);
        entityManager.remove(s);
    }


}
