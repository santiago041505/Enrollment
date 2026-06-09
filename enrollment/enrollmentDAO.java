/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enrollment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author easterPC
 */
public class enrollmentDAO {
     public void enrollStudent(enrollment Enrollment) {
      
        String sql = "INSERT INTO enrolled_subjects (student_id, course_id, enrollment_date) VALUES (?, ?, CURRENT_DATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Enrollment.getStudentId());
            pstmt.setInt(2, Enrollment.getCourseId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    public List<enrollment> getAllEnrollments() {
        List<enrollment> Enrollments = new ArrayList<>();
        
        
        String sql = "SELECT e.enrollment_id, e.student_id, e.course_id, e.enrollment_date, " +
                     "s.first_name, s.last_name, c.course_name " +
                     "FROM enrolled_subjects e " +
                     "JOIN students s ON e.student_id = s.student_id " +
                     "JOIN courses c ON e.course_id = c.course_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                enrollment en = new enrollment();
                en.setEnrollmentId(rs.getInt("enrollment_id"));
                en.setStudentId(rs.getInt("student_id"));
                en.setCourseId(rs.getInt("course_id"));
                en.setEnrollmentDate(rs.getDate("enrollment_date"));

               
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                en.setStudentName(fullName);
                en.setCourseName(rs.getString("course_name"));

                Enrollments.add(en);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching enrollments: " + e.getMessage());
        }
        return Enrollments;
    }
    
    
    public void deleteEnrollment(int enrollmentId) {
        String sql = "DELETE FROM enrolled_subjects WHERE enrollment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, enrollmentId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error deleting enrollment: " + e.getMessage());
        }
    }
}
