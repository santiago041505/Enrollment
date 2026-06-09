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
public class courseDAO {
     public void addcourse(course Course) {
        String sql = "INSERT INTO courses (course_name, course_description, credits) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, Course.getCourseName());
            pstmt.setString(2, Course.getCourseDescription());
            pstmt.setInt(3, Course.getCredits());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } public List<course> getAllCourses() {
        List<course> Courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                course Course = new course();
                Course.setCourseId(rs.getInt("course_id"));
                Course.setCourseName(rs.getString("course_name"));
                Course.setCourseDescription(rs.getString("course_description"));
                Course.setCredits(rs.getInt("credits"));
                Courses.add(Course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Courses;}
    
    public void updateCourse(course Course) {
        String sql = "UPDATE courses SET course_name = ?, course_description = ?, credits = ? WHERE course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, Course.getCourseName());
            pstmt.setString(2, Course.getCourseDescription());
            pstmt.setInt(3, Course.getCredits());
            pstmt.setInt(4, Course.getCourseId());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
