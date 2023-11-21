package com.example.demo.advancedmappings;

import com.example.demo.advancedmappings.dao.MyDao;
import com.example.demo.advancedmappings.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MyDao myDao) {
		return runner -> {
			//findInstructorCourses(myDao);
			//findCoursesByInstructorJoinFetch(myDao);
			//updateCourse(myDao);
			//deleteCourseAndReviews(myDao);
			//createCourseAndStudents(myDao);
			//retrieveCourseAndStudents(myDao);
			//retrieveStudentAndCourses(myDao);
			//addMoreCoursesToStudent(myDao);
			//deleteCourseById(myDao);
			deleteStudentById(myDao);
		};
	}

	private void createInstructor(MyDao myDao) {

		System.out.println("saving...");
		Instructor i = new Instructor("Kuma", "Kumeczka", "Kuma@kuma.kuma");
		InstructorDetail instructorDetail = new InstructorDetail("yt.com", "boxing");
		i.setInstructorDetail(instructorDetail);
		myDao.save(i);
		System.out.println("saved");
	}

	private void findInstructor(MyDao myDao) {
		int id = 1;
		System.out.println("finding instructor with id of " + id + "...");

		Instructor temp = myDao.findInstuctorById(id);
		System.out.println(temp);
	}

	private void deleteInstructor(MyDao myDao) {
		int id = 1;
		System.out.println("deleting instructor with id of " + id + "...");
		myDao.delete(id);
		System.out.println("delete done");
	}

	private void findInsDetails(MyDao myDao) {
		int id = 3;
		InstructorDetail instructorDetail = myDao.findInstructorDetailById(id);
		System.out.println(instructorDetail);
	}

	private void deleteInstructorDetails(MyDao myDao) {
		int id = 5;
		myDao.deleteDetails(id);
	}

	private void createInstructorWithCourses(MyDao myDao) {
		Instructor i = new Instructor("Kumitka", "Kumeczka", "Kuma@kuma.kuma");
		InstructorDetail instructorDetail = new InstructorDetail("yt.com", "boxing");
		i.setInstructorDetail(instructorDetail);

		Course c = new Course("boxing");
		i.addCourse(c);
		myDao.save(i);
	}

	private  void findInstructorCourses(MyDao myDao) {

		int id=1;
		System.out.println(myDao.findCoursesByInstructor(id));
	}

	private void findCoursesByInstructorJoinFetch(MyDao myDao) {
		int id=1;
		Instructor temp = myDao.findCoursesByInstructorJoinFetch(id);
		System.out.println(temp.getCourseList());
	}

	private void updateInstructor(MyDao myDao) {
		int id=1;
		Instructor i = myDao.findInstuctorById(id);
		i.setEmail("nowymail");
		myDao.update(i);
	}

	private void updateCourse(MyDao myDao) {
		int id=10;
		Course c = myDao.findCourseById(id);
		c.setTitle("boxing");
		myDao.update(c);
	}

	private void deleteInstructorById(MyDao myDao) {
		int id=1;
		myDao.deleteInstructor(id);
	}

	private void deleteCourseById(MyDao myDao) {
		int id=11;
		myDao.deleteCourseById(id);
	}

	private void deleteStudentById(MyDao myDao) {
		int id = 1;
		myDao.deleteStudentById(id);
	}

	private void createCourseAndReviews(MyDao myDao) {
		Course c= new Course("Pacman");

		c.addReview(new Review("great"));
		c.addReview(new Review("good"));
		c.addReview(new Review("cool"));
		c.addReview(new Review("nice"));

		System.out.println("saving...");

		myDao.save(c);

		System.out.println("saved");
	}

	private void retrieveCourseAndReviews(MyDao myDao) {
		int id=12;
		Course c = myDao.findCourseAndReviewsById(id);
		System.out.println(c);
		System.out.println(c.getReviews());
	}

	private void deleteCourseAndReviews(MyDao myDao) {
		int id=12;

		System.out.println("deleting...");

		myDao.deleteCourseById(id);
		//it will delete corresponding reviews because cascade type is set to Cascade.ALL
	}

	private void createCourseAndStudents(MyDao myDao) {
		Course c = new Course("Kickboxing");

		Student s1 = new Student("kuma", "kumeczka", "kuma@gmail.com");
		Student s2 = new Student("kumaa", "kumeeczka", "kuuma@gmail.com");
		Student s3 = new Student("kumitka", "kumeczkaa", "kumitka@gmail.com");

		c.addStudent(s1);
		c.addStudent(s2);
		c.addStudent(s3);

		myDao.save(c);
		//it will cascade save students

	}

	private void retrieveCourseAndStudents(MyDao myDao) {
		int id=10;
		Course c = myDao.findCourseAndStudentsById(id);
		System.out.println(c);
		System.out.println(c.getStudentList());
	}

	private void retrieveStudentAndCourses(MyDao myDao) {
		int id=2;
		Student s = myDao.findStudentAndCourseById(id);
		System.out.println(s);
		System.out.println(s.getCourseList());
	}

	private void addMoreCoursesToStudent(MyDao myDao) {
		int id=2;

		Student s = myDao.findStudentAndCourseById(id);

		Course c1 = new Course("Boxing");

		s.addCourse(c1);

		myDao.update(s);
	}

}
