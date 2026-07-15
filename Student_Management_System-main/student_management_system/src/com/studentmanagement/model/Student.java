package com.studentmanagement.model;

/**
 * Represents a student in the Student Management System.
 * This class follows the JavaBean conventions and contains
 * necessary validations for data integrity.
 * 
 * <p>Constraints (from DB schema):
 * - id: INT, Auto-Incremented, Positive
 * - name: VARCHAR(100), Alphabetic, Required
 * - age: INT, >0
 * - email: VARCHAR(100), Unique, Valid Format
 * - course: VARCHAR(50), Alphabetic, Required
 * - phone: VARCHAR(10), Exactly 10 Digits
 * 
 * @author Rohan Bansal
 */
public class Student {

   
    private int id;
    private String name;
    private int age;
    private String email;
    private String course;
    private String phone;

    

    /**
     * No-args constructor required for frameworks (JDBC, Reflection, etc.)
     */
    public Student() {
        
    }

    /**
     * All-args constructor to create a Student object with full data.
     * @param id student ID (positive integer)
     * @param name student's full name
     * @param age student's age (must be > 0)
     * @param email student's email (valid format)
     * @param course course name (only alphabets)
     * @param phone student's phone number (10 digits)
     */
    public Student(int id, String name, int age, String email, String course, String phone) {
        setId(id);
        setName(name);
        setAge(age);
        setEmail(email);
        setCourse(course);
        setPhone(phone);
    }


    /**
     * @return student ID
     */
    public int getId() {
        return id;
    }

    /**
     * @param id must be a positive number
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        this.id = id;
    }

    /**
     * @return student name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name must be alphabetic, 2-50 characters
     */
    public void setName(String name) {
        if (name == null || !name.matches("[A-Za-z ]{2,50}")) {
            throw new IllegalArgumentException("Name must be alphabetic and 2-50 characters long.");
        }
        this.name = name.trim();
    }

    /**
     * @return student age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age must be greater than 0
     */
    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be a positive integer.");
        }
        this.age = age;
    }

    /**
     * @return student email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email must be valid format
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email.trim();
    }

    /**
     * @return course name
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course must be alphabetic, 2-50 characters
     */
    public void setCourse(String course) {
        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        if (!course.matches("[A-Za-z ]{2,50}")) {
            throw new IllegalArgumentException("Course name must contain only letters and spaces, 2–50 characters.");
        }
        this.course = course.trim();
    }

    /**
     * @return student phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone must be exactly 10 digits
     */
    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        }
        this.phone = phone;
    }

    

    /**
     * String representation of the Student object.
     */
    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", Age=" + age + ", Email=" + email +
               ", Course=" + course + ", Phone=" + phone + "]";
    }
}
