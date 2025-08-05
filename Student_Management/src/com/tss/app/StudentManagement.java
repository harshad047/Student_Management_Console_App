package com.tss.app;

import com.tss.controller.CourseController;
import com.tss.controller.StudentController;
import com.tss.controller.StudentCourseController;
import com.tss.exception.ValidationException;
import com.tss.util.InputUtil;

public class StudentManagement implements MenuHandler {

	@Override
	public void showMenu() {
		System.out.println("\n+------------------------------+");
		System.out.println("|      STUDENT MANAGEMENT      |");
		System.out.println("+------------------------------+");
		System.out.println("| 1. View All Students         |");
		System.out.println("| 2. Add New Student           |");
		System.out.println("| 3. Assign A Course           |");
		System.out.println("| 4. View All Courses          |");
		System.out.println("| 5. Search A Student          |");
		System.out.println("| 6. Delete A Student          |");
		System.out.println("| 7. Pay Student Fees          |");
		System.out.println("| 8. View All Course Of Student|");
		System.out.println("| 9. Manage Notification       |");
		System.out.println("| 10. Restore Student          |");
		System.out.println("| 11. Go Back                  |");
		System.out.println("+------------------------------+");
	}

	@Override
	public void chooseMenu() {
		int choice = -1;
		StudentController controller = new StudentController();
		StudentCourseController SCController = new StudentCourseController();
		CourseController courseController = new CourseController();

		while (true) {
			showMenu();
			choice = InputUtil.readInt("Enter your choice: ");

			switch (choice) {
				case 1:
					controller.readAllRecords();
					break;
				case 2:
					try {
						controller.insertStudent();
					} catch (ValidationException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					SCController.AssignCourseToStudent(controller, courseController);
					break;
				case 4:
					courseController.readAllCourseRecords();
					break;
				case 5:
					controller.searchStudentById();
					break;
				case 6:
					controller.deleteStudentById();
					break;
				case 7:
					controller.readAllRecords();
					int studentId = InputUtil.readInt("Enter Student ID: ");
					controller.payStudentFees(studentId);
					break;
				case 8:
					try {
						controller.showAllCoursesById();
					} catch (ValidationException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 9:
					controller.manageNotification();
					break;
				case 10:
					controller.restoreStudent();
					break;
				case 11:
					System.out.println(">> Returning to main menu...");
					return;
				default:
					System.out.println("enter choice from 1 to 10.");
			}
		}
	}
}
