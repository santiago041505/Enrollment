# Enrollment

Before running this application, make sure you have installed:

1. Java Development Kit (JDK): Version 8 or newer.

2. Integrated Development Environment (IDE): NetBeans IDE (recommended), Eclipse, or IntelliJ IDEA.

3. Database Server: MySQL Server running locally (via standalone installation or tools like XAMPP/WAMP).

4. MySQL JDBC Driver: The MySQL Connector/J extension library (JAR format) to allow Java to speak to the MySQL database.

## MySQL database setup
1. Open your MySQL client (MySQL Workbench, phpMyAdmin, or the command line terminal).

2. Execute the following script to create the database schema and matching tables used by the application:

CREATE DATABASE School_db;
USE School_db;

-- 1. Create the students database table
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- 2. Create the courses database table
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    course_description TEXT,
    credits INT NOT NULL
);

-- 3. Create the enrolled_subjects linkage table
CREATE TABLE enrolled_subjects (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    enrollment_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

## How to Load and Run the Application
1. Open the ProjectLaunch NetBeans.Choose File > Open Project.Select the root directory containing the project source folder and open it.
2. Linking the MySQL JDBC Driver DependencyIf you experience a ClassNotFoundException or SQL connection crashes on launch, the JDBC driver library must be explicitly referenced in your IDE project properties:Locate the Projects panel on the left side of NetBeans.Expand your enrollment project tree node and right-click on the Libraries directory node.Choose Add JAR/Folder.Find your downloaded mysql-connector-j-8.x.x.jar file on your filesystem and click Open.
3. ExecutionEnsure your local MySQL instance is active.Within the file hierarchy window, locate Source Packages $\rightarrow$ enrollment.Right-click on EnrollmentFrame.java or CourseFrame.java and choose Run File.The desktop interface windows will populate automatically with interactive live database rows.
 
