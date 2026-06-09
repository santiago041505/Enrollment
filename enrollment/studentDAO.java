/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enrollment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author easterPC
 */
public class studentDAO {
    public void addstudent(student Student) {
        String sql = "INSERT INTO students (first_name, last_name, age, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, Student.getFirstName());
            stmt.setString(2, Student.getLastName());
            stmt.setInt(3, Student.getAge());
            stmt.setString(4, Student.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<student> getAllStudents() {
        List<student> Students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                student Student = new student();
                Student.setStudentId(rs.getInt("student_id"));
                Student.setFirstName(rs.getString("first_name"));
                Student.setLastName(rs.getString("last_name"));
                Student.setAge(rs.getInt("age"));
                Student.setEmail(rs.getString("email"));
                Students.add(Student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Students;
    }

    public void updateStudent(student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, age = ?, email = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getEmail());
            stmt.setInt(5, student.getStudentId()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId); // Tells SQL exactly which student to delete
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
