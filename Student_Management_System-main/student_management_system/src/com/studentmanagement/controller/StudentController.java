package com.studentmanagement.controller;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class to handle user interaction (CLI) for Student Management System.
 * Displays menu, handles user inputs, and communicates with the service layer.
 * 
 * 
 * Author: Rohan Bansal
 */
public class StudentController {

    private final StudentService service;
    private final Scanner scanner;

    /**
     * Constructor to initialize dependencies.
     */
    public StudentController() {
        this.service = new StudentService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Entry point for the controller. Displays menu and handles user actions.
     * @throws SQLException 
     */
    public void start() throws SQLException {
        System.out.println("===== Welcome to Student Management System =====");

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudentById();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Collects student data from user and adds to the database.
     * @throws SQLException 
     */
    private void addStudent() throws SQLException {
        System.out.println("Enter student details:");

        Student student = getStudentFromUser(false);

        if (service.addStudent(student)) {
            System.out.println(" Student added successfully!");
        } else {
            System.out.println(" Failed to add student. Check logs or constraints.");
        }
    }

    /**
     * Displays all students.
     * @throws SQLException 
     */
    private void viewAllStudents() throws SQLException {
        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println(" No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    /**
     * Searches for a student by ID.
     * @throws SQLException 
     */
    private void searchStudentById() throws SQLException {
        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = service.getStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println(" Student not found.");
        }
    }

    /**
     * Updates a student's details.
     * @throws SQLException 
     */
    private void updateStudent() throws SQLException {
        System.out.print("Enter Student ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student existing = service.getStudentById(id);
        if (existing == null) {
            System.out.println(" Student not found.");
            return;
        }
        System.out.println("Enter new details (leave blank to keep existing):");
        Student updated = getStudentFromUser(true);
        updated.setId(id);

        if (service.updateStudent(updated)) {
            System.out.println(" Student updated.");
        } else {
            System.out.println(" Update failed.");
        }
    }

    /**
     * Deletes a student by ID.
     * @throws SQLException 
     */
    private void deleteStudent() throws SQLException {
        System.out.print("Enter Student ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (service.deleteStudent(id)) {
            System.out.println(" Student deleted.");
        } else {
            System.out.println(" Deletion failed.");
        }
    }

    /**
     * Utility to create a student object from CLI input.
     *
     * @param isUpdate true if for update (optional fields)
     * @return Student object
     */
    private Student getStudentFromUser(boolean isUpdate) {
        String name = prompt("Name", isUpdate);
        String email = prompt("Email", isUpdate);
        String course = prompt("Course", isUpdate);
        String phone = prompt("Phone (10 digits)", isUpdate);
        String ageStr = prompt("Age", isUpdate);

        Student student = new Student();
        if (!name.isEmpty()) student.setName(name);
        if (!email.isEmpty()) student.setEmail(email);
        if (!course.isEmpty()) student.setCourse(course);
        if (!phone.isEmpty()) student.setPhone(phone);
        if (!ageStr.isEmpty()) student.setAge(Integer.parseInt(ageStr));

        return student;
    }

    /**
     * Utility to prompt user input with optional behavior.
     */
    private String prompt(String fieldName, boolean isUpdate) {
        System.out.print(fieldName + ": ");
        String input = scanner.nextLine().trim();
        return isUpdate ? input : input.isEmpty() ? "" : input;
    }

    /**
     * Main method to start the application.
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
        new StudentController().start();
    }
}
