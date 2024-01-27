# Hospital Management System
The Hospital Management System is a Java-based application designed to streamline operations within a hospital setting. It allows for easy management of patient records, doctor details, and appointment bookings.


## Features

- Add a Patient:

The system provides a functionality to add new patient records with details such as name, age, gender, and contact information.

- View Patients:

Easily view the list of patients with their respective details. The patient list can be sorted and filtered based on different criteria.

- View Doctors:

Access information about the doctors associated with the hospital, including their names, specializations, and contact details.

- Book Appointments:

Patients can book appointments with doctors through the system. The application ensures a smooth process for scheduling and managing appointments.


## Database
The application utilizes a relational database to store and retrieve data. The database schema includes tables for patients, doctors, appointments, and other relevant information.
## Tables
- Patients:

Stores information about patients, including patient ID, name, age, gender, and contact details.
- Doctors:

Contains details about doctors, including doctor ID, name, specialization, and contact information.
- Appointments:

Records appointments made by patients, including appointment ID, patient ID, doctor ID, appointment date, and time.
## Database Configuration
The application is configured to connect to the database using JDBC (Java Database Connectivity). Ensure that the database connection details (URL, username, and password) are correctly specified in the application's configuration.


String url = "jdbc:mysql://localhost:3306/hospital_db";

String username = "your_username";

String password = "your_password";

## Getting Started
To run the Hospital Management System locally, follow these steps:

Clone the repository:


"git clone https://github.com/Anki2004/hospital-management.git
cd hospital-management".

Build and run the application using your preferred Java development environment.

Ensure that the database is set up with the required tables. You can use the provided SQL script (create_tables.sql) to create the necessary tables.

Configure the database connection details in the application code.
## Contributions
Contributions are welcome! If you have any suggestions, improvements, or bug fixes, feel free to open an issue or submit a pull request.
