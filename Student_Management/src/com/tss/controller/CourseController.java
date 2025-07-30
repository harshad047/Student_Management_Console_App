package com.tss.controller;

import java.util.List;
import java.util.Scanner;

import com.tss.model.Course;
import com.tss.service.CourseService;

public class CourseController {
	private CourseService courseService;
	private Scanner scanner = new Scanner(System.in);

	public CourseController() {
		this.courseService = new CourseService();
	}

	public void readAllCourseRecords() {
		List<Course> courses = courseService.readAllCourses();

		System.out.println("\n+-------------------------------------------------------------+");
		System.out.println("|                        COURSE RECORDS                       |");
		System.out.println("+-------------------------------------------------------------+");
		System.out.printf("| %-10s | %-20s | %-10s | %-10s |\n", "Course ID", "Course Name", "Fees", "Active");
		System.out.println("+-------------------------------------------------------------+");

		for (Course course : courses) {
			System.out.printf("| %-10d | %-20s | %-10.2f | %-10s |\n",
				course.getCourseId(),
				course.getCourseName(),
				course.getCourseFees(),
				course.isActive() ? "Yes" : "No");
		}

		System.out.println("+-------------------------------------------------------------+");
	}

	public void addNewCourse() {
		System.out.print("Enter Course Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Course Fees: ");
		double fees = scanner.nextDouble();

		System.out.print("Is the course active? (true/false): ");
		boolean isActive = scanner.nextBoolean();
		scanner.nextLine();  // consume newline

		Course course = new Course();
		course.setCourseName(name);
		course.setCourseFees(fees);
		course.setActive(isActive);

		boolean success = courseService.addCourse(course);
		if (success) {
			System.out.println("✅ Course added successfully.");
		} else {
			System.out.println("❌ Failed to add course.");
		}
	}

	
}
