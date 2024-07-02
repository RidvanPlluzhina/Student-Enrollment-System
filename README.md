# Student Enrollment Management (Ongoing Project)

## Overview

This project, "Student Enrollment Management," was developed as part of the coursework for the subject "Introduction to Databases." The primary goal is to store and manage data about students enrolled in various exams at our institution. The project employs Java for the application logic and PostgreSQL for the database management, using Eclipse as the development environment and pgAdmin4 for database administration.

## Textual Specification
We want to store data about students enrolled in our institution for certain exams. For each student, we want to remember their StudentID, first name, last name, date of birth, email (optional), and their level of study (Bachelor's, Master's, or PhD). Some students might also be employed, and for these students, we want to store information about their work.
Each student can take multiple exams. For each exam a student takes, we want to record the ExamID, Date of the exam, ExamStartTime, and ExamEndTime. The exam is associated with a specific subject and organized by a professor. The exam is also held in a specific classroom.
For each exam, we want to know the subject it covers. Each subject has a unique SubjectID and name and is taught by a professor. We also need to manage classroom information, such as its room number, building, and capacity.
We also need to keep track of the professors who organize and teach various subjects. For each professor, we want to remember their ProfessorID, first name, last name, email, and phone numbers. For University Professors, we want to store the Department (e.g., Computer Science…). For External Professors, we want to store their Affiliation (e.g., ABC Institute…). Professors can also mentor students, and we want to keep track of these mentorship relationships.
Additionally, we want to manage the information about classrooms. Each classroom must host at least one subject, and each subject is hosted in exactly one classroom. Each classroom can host multiple subjects, and we want to record which subjects are taught in each classroom.

## Entity-Relationship (ER) Diagram
![ER Diagram](https://github.com/RidvanPlluzhina/Student-Enrollment-System/assets/127865601/80a73cac-ca11-4228-b18e-f63d9059c314)
## Restructured version of Conceptual Schema
![Restructuring Concteptual Schema](https://github.com/RidvanPlluzhina/Student-Enrollment-System/assets/127865601/17068bb3-20b8-4d46-87ce-cc7dcdf308de)

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

The number of students taking an exam in a specific classroom should not exceed the classroom's capacity.
No two exams should be scheduled in the same classroom at the same time.
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
