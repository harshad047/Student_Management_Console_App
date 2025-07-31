package com.tss.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Course;
import com.tss.model.Fees;
import com.tss.model.StudentCourse;
import com.tss.service.CourseService;
import com.tss.service.FeeService;
import com.tss.service.StudentCourseService;

public class StudentCourseController {

	private StudentCourseService studentCourseService;
	private FeeService feesservice;
	private CourseService courseService;
	private Scanner scanner = new Scanner(System.in);

	public StudentCourseController() {
		this.studentCourseService = new StudentCourseService();
		this.feesservice = new FeeService();
		this.courseService = new CourseService();
	}

	public void AssignCourseToStudent(StudentController studentController, CourseController courseController) {
		try {
			studentController.readAllRecords();

			System.out.print("Enter Student ID: ");
			int studentId = Integer.parseInt(scanner.nextLine().trim());

			if (studentController.studentExistance(studentId)) {
				courseController.radAllActiveCourse();
				System.out.print("Enter Course ID: ");
				int courseId = Integer.parseInt(scanner.nextLine().trim());

				if (courseController.courseExistance(courseId)) {
					StudentCourse studentCourse = new StudentCourse();
					studentCourse.setStudentId(studentId);
					studentCourse.setCourseId(courseId);
					studentCourse.setEnrolledAt(LocalDateTime.now());
					
					studentCourseService.AssignCourseToStudent(studentCourse);
					
					Course course = courseService.searchCourse(courseId);
					
					
					Fees fee = new Fees(courseId,studentId,0.0,course.getCourseFees());
					feesservice.insertNewRecord(fee);
					
					return;
				}
				System.out.println("Course With id " + courseId + " doesn't exists Or Not Active!!");
				return;
			}
			System.out.println("Student With id " + studentId + " doesn't exists !!");
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter numeric values for IDs.");
		} catch (Exception e) {
			System.out.println("An error occurred while assigning course.");
			e.printStackTrace();
		}
	}

	public void deleteCourseFromStudent(int student_id) {
		studentCourseService.deleteStudentCourse(student_id);
	}

	public void getAllCourses(int student_id) {
		List<Course> courses = studentCourseService.getAllCourses(student_id);

		if (courses.isEmpty() || courses == null) {
			System.out.println("Student With ID " + student_id + " Not Enrolled in Any Course !!");
			return;
		}
		String border = "+-------------+--------------------------+-----------+--------+";
		String title = "|                 Student's Enrolled Courses                  |";

		System.out.println("+-------------------------------------------------------------+");
		System.out.println(title);
		System.out.println(border);
		System.out.printf("| %-11s | %-24s | %-9s | %-6s |\n", "Course ID", "Course Name", "Fees (₹)", "Active");
		System.out.println(border);

		for (Course course : courses) {
			System.out.printf("| %-11d | %-24s | ₹%-8.2f | %-6s |\n", course.getCourseId(), course.getCourseName(),
					course.getCourseFees(), course.isActive() ? "Yes" : "No");
		}

		System.out.println(border);
	}

}
