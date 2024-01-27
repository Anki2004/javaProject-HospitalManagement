package HospitalManagementSystem;


import java.sql.*;
import java.util.Scanner;


public class hospitalManagementSystem {
    private  static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "Anki2004@#";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Patient patient = new Patient(connection, scanner);
            Doctors doctor = new Doctors(connection);
            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. view Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter your preference: ");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        //add patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        //view patients
                        patient.viewPatient();
                        System.out.println();
                        break;
                    case 3:
                        //view doctors
                        doctor.viewDoctor();
                        System.out.println();
                        break;
                    case 4:
                        //Book appointment
                        bookAppointments(patient, doctor, connection, scanner);
                        System.out.println();
                        break;
                    case 5:
                        //exit
                        return;
                    default:
                        System.out.println("Enter valid choice!!");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void bookAppointments(Patient patient, Doctors doctors, Connection connection, Scanner scanner){
        System.out.print("Enter patient Id: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD)");
        String appointmentDate = scanner.next();
        if(patient.getPatientById(patientId) && doctors.getDoctorById(doctorId)){
            if(checkDoctorAvailibility(doctorId, appointmentDate,connection)){
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected > 0){
                        System.out.println("Appointment Booked!");
                    }else{
                        System.out.println("Failed to book appointment with the doctor!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }else{
                System.out.println("Doctor not available on this date!");
            }
        }else{
            System.out.println("Either doctor and patient does not exists! ");
        }

    }
    public static boolean checkDoctorAvailibility(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count == 0){
                    return true;

                }else{
                    return false;
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }return false;
    }
}
