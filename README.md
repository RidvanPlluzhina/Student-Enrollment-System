# Student Enrollment Management (Ongoing Project)

## Overview

This project, "Student Enrollment Management," was developed as part of the coursework for the subject "Introduction to Databases." The primary goal is to store and manage data about students enrolled in various exams at our institution. The project employs Java for the application logic and PostgreSQL for the database management, using Eclipse as the development environment and pgAdmin4 for database administration.

## Entity-Relationship (ER) Diagram

![image](https://github.com/RidvanPlluzhina/Student-Enrollment-System/assets/127865601/4a22d07a-f4cc-4197-81d3-cfcb21b8d2b2)

## Textual Specification

### Student Information

We aim to store detailed information about each student, including:
- **StudentID:** A unique identifier for each student.
- **FirstName:** The first name of the student.
- **LastName:** The last name of the student.
- **DateOfBirth:** The student's date of birth.
- **Email:** The student's email address (optional).
- **Level:** The level of study, which can be Bachelor's, Master's, or PhD.

Additionally, for students who are employed, we also store:
- **Work:** Information about their employment (optional).

### Exam Information

Each student can enroll in multiple exams. For each exam a student takes, we store:
- **ExamID:** A unique identifier for each exam.
- **ExamDate:** The date when the exam is scheduled.

### Subject Information

Each exam is associated with a specific subject, and for each subject, we store:
- **SubjectID:** A unique identifier for each subject.
- **SubjectName:** The name of the subject.

### Classroom Information

Each exam is held in a specific classroom. For each classroom, we store:
- **ClassroomID:** A unique identifier for each classroom.
- **RoomNr:** The room number.
- **Building:** The building where the classroom is located.
- **Capacity:** The maximum number of students the classroom can accommodate.

### Professor Information

We also manage data about professors, including:
- **ProfessorID:** A unique identifier for each professor.
- **FirstName:** The first name of the professor.
- **LastName:** The last name of the professor.
- **Email:** The email address of the professor.

Professors are associated with specific subjects and can organize exams. Additionally, professors can mentor students, and these mentorship relationships are tracked.

## External Constraints

### Classroom Capacity Constraint

The number of students taking an exam in a specific classroom should not exceed the classroom's capacity.

### Unique Exam Schedule Constraint

No two exams should be scheduled in the same classroom at the same time.

### Professor's Subject Specialization

A professor can only organize or teach exams for subjects they are specialized in.

## Technologies Used

- **Programming Language:** Java
- **Database Management System:** PostgreSQL
- **Integrated Development Environment (IDE):** Eclipse
- **Database Administration Tool:** pgAdmin4
- **ER Diagram Tool:** Draw.io

## How to Run the Project

1. **Clone the repository:**

git clone https://github.com/RidvanPlluzhina/Student-Enrollment-System.git

## Project Setup Guide
1. Open the project in Eclipse:
2. Go to File > Open Projects from File System...
   
## Set up the PostgreSQL database:
1. Create a new database in PostgreSQL.
2. Use pgAdmin4 to execute the SQL scripts provided in the db folder to create the necessary tables and relationships.
   
## Configure the database connection:
1. Update the database connection settings in the src/db/DatabaseConnection.java file with your PostgreSQL credentials.
   
## Run the application:
1. Right-click on the src folder and select Run As > Java Application.


## Contributors
Ridvan Plluzhina
