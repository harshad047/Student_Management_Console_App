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
			System.out.printf("| %-10d | %-20s | %-10.2f | %-10s |\n", course.getCourseId(), course.getCourseName(),
					course.getCourseFees(), course.isActive() ? "Yes" : "No");
		}

		System.out.println("+-------------------------------------------------------------+");
	}

	public void addNewCourse() {
		String name;
		double fees = -1;

		// Validate course name (non-empty + contains alphabet)
		while (true) {
			System.out.print("Enter Course Name: ");
			name = scanner.nextLine().trim();

			if (name.isEmpty()) {
				System.out.println(">> Course name cannot be empty. Please try again.");
			} else if (!name.matches(".*[a-zA-Z].*")) {
				System.out.println(">> Course name must contain at least one alphabet character.");
			} else {
				break;
			}
		}

		// Validate course fees
		while (true) {
			System.out.print("Enter Course Fees: ");
			String input = scanner.nextLine().trim();

			try {
				fees = Double.parseDouble(input);
				if (fees < 0) {
					System.out.println(">> Course fees cannot be negative. Please enter a positive amount.");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println(">> Invalid input. Please enter a valid number for fees.");
			}
		}

		// Create and save course
		Course course = new Course();
		course.setCourseName(name);
		course.setCourseFees(fees);

		boolean success = courseService.addCourse(course);
		if (success) {
			System.out.println(">> Course added successfully.");
		} else {
			System.out.println(">> Failed to add course.");
		}
	}

	public void searchCourse() {

		System.out.println("Enter course id to search the course");
		int course_id = scanner.nextInt();

		Course course = courseService.searchCourse(course_id);

		if (course != null) {
			System.out.println("\n+-------------------------------------------------------------+");
			System.out.println("|                        COURSE RECORDS                       |");
			System.out.println("+-------------------------------------------------------------+");
			System.out.printf("| %-10s | %-20s | %-10s | %-10s |\n", "Course ID", "Course Name", "Fees", "Active");
			System.out.println("+-------------------------------------------------------------+");

			System.out.printf("| %-10d | %-20s | %-10.2f | %-10s |\n", course.getCourseId(), course.getCourseName(),
					course.getCourseFees(), course.isActive() ? "Yes" : "No");

			System.out.println("+-------------------------------------------------------------+");
		} else {
			System.out.println("\nNo existing course with this ID: " + course_id);
		}
	}

	public boolean courseExistance(int course_id) {
		Course course = courseService.searchCourse(course_id);
		if (course != null)
			return true;
		return false;
	}

	public void softDeleteCourse() {

		radAllActiveCourse();

		System.out.println("Enter course id to delete the course");
		int course_id = scanner.nextInt();

		Course course = courseService.softDeleteCourse(course_id);

		if (course != null) {
			System.out.println("\n+-------------------------------------------------------------+");
			System.out.println("|                        COURSE RECORDS                       |");
			System.out.println("+-------------------------------------------------------------+");
			System.out.printf("| %-10s | %-20s | %-10s | %-10s |\n", "Course ID", "Course Name", "Fees", "Active");
			System.out.println("+-------------------------------------------------------------+");

			System.out.printf("| %-10d | %-20s | %-10.2f | %-10s |\n", course.getCourseId(), course.getCourseName(),
					course.getCourseFees(), course.isActive() ? "Yes" : "No");

			System.out.println("+-------------------------------------------------------------+");
			System.out.println("\nCourse was successfully marked as inactive.");
		} else {
			System.out.println("\nNo active course found with ID: " + course_id + " or it may already be inactive.");
		}
	}

	public List<Course> radAllActiveCourse() {
		List<Course> courses = courseService.readAllActiveCourses();

		System.out.println("\n+-------------------------------------------------------------+");
		System.out.println("|                        COURSE RECORDS                       |");
		System.out.println("+-------------------------------------------------------------+");
		System.out.printf("| %-10s | %-20s | %-10s | %-10s |\n", "Course ID", "Course Name", "Fees", "Active");
		System.out.println("+-------------------------------------------------------------+");

		for (Course course : courses) {
			System.out.printf("| %-10d | %-20s | %-10.2f | %-10s |\n", course.getCourseId(), course.getCourseName(),
					course.getCourseFees(), course.isActive() ? "Yes" : "No");
		}

		System.out.println("+-------------------------------------------------------------+");
		return courses;
	}

}
