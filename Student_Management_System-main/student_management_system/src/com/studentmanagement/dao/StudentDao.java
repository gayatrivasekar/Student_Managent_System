package com.studentmanagement.dao;

import com.studentmanagement.model.Student;
import com.studentmanagement.database.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for performing CRUD operations on Student table.
 */


public class StudentDao {

    /**
     * Inserts a new student into the database.
     *
     * @param student the student to be added
     * @return true if insert is successful, false otherwise
     * @throws SQLException if SQL error occurs
     */
    public boolean addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, age, email, course, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = StudentDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getCourse());
            pstmt.setString(5, student.getPhone());
            return pstmt.executeUpdate() > 0;
        }
    }

    /**
     * Fetches all students from the database.
     *
     * @return list of all students
     * @throws SQLException if SQL error occurs
     */
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = StudentDatabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = mapResultSetToStudent(rs);
                students.add(student);
            }
        }
        return students;
    }

    /**
     * Fetches a student by ID.
     *
     * @param id the student ID
     * @return Student object if found, else null
     * @throws SQLException if SQL error occurs
     */
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = StudentDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
        }
        return null;
    }

    /**
     * Updates a student's information.
     *
     * @param student student object with updated data
     * @return true if update is successful, false otherwise
     * @throws SQLException if SQL error occurs
     */
    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, age = ?, email = ?, course = ?, phone = ? WHERE id = ?";
        try (Connection conn = StudentDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getCourse());
            pstmt.setString(5, student.getPhone());
            pstmt.setInt(6, student.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    /**
     * Deletes a student from the database by ID.
     *
     * @param id the student ID to delete
     * @return true if delete was successful, false otherwise
     * @throws SQLException if SQL error occurs
     */
    public boolean deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = StudentDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }



    /**
     * Maps a result set row to a Student object.
     *
     * @param rs ResultSet pointing to the current row
     * @return Student object
     * @throws SQLException if column not found
     */
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("email"),
                rs.getString("course"),
                rs.getString("phone")
        );
    }
}
