package com.studentmanagement.service;

import com.studentmanagement.model.Student;

import com.studentmanagement.dao.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class for managing students.
 * Applies business rules and delegates data persistence to the database layer.
 *
 * @author
 */
public class StudentService {

    private final StudentDao database;

    /**
     * Constructor to inject the StudentDatabase dependency.
     */
    public StudentService() {
        this.database = new StudentDao();
    }

    /**
     * Adds a new student after applying business rules.
     * 
     * @param student the student to add
     * @return true if added successfully, false otherwise
     * @throws SQLException 
     */
    public boolean addStudent(Student student) throws SQLException {
        // You can add additional business validations here
        return database.addStudent(student);
    }

    /**
     * Retrieves all students.
     * 
     * @return list of students
     * @throws SQLException 
     */
    public List<Student> getAllStudents() throws SQLException {
        return database.getAllStudents();
    }

    /**
     * Retrieves a student by ID.
     * 
     * @param id student ID
     * @return the student object if found, null otherwise
     * @throws SQLException 
     */
    public Student getStudentById(int id) throws SQLException {
        return database.getStudentById(id);
    }

    /**
     * Updates a student by ID.
     * 
     * @param student updated student object
     * @return true if update was successful
     * @throws SQLException 
     */
    public boolean updateStudent(Student student) throws SQLException {
        return database.updateStudent(student);
    }

    /**
     * Deletes a student by ID.
     * 
     * @param id student ID to delete
     * @return true if deleted successfully
     * @throws SQLException 
     */
    public boolean deleteStudent(int id) throws SQLException {
        return database.deleteStudent(id);
    }
}
