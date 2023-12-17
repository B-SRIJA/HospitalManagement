package mainmod;

import dao.HospitalServiceImpl;
import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;

import java.util.List;
import java.util.Scanner;

public class MainModule {

    private static HospitalServiceImpl hospitalService = new HospitalServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Get Appointment by ID");
            System.out.println("2. Get Appointments for Patient");
            System.out.println("3. Get Appointments for Doctor");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. Update Appointment");
            System.out.println("6. Cancel Appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    getAppointmentById();
                    break;
                case 2:
                    getAppointmentsForPatient();
                    break;
                case 3:
                    getAppointmentsForDoctor();
                    break;
                case 4:
                    scheduleAppointment();
                    break;
                case 5:
                    updateAppointment();
                    break;
                case 6:
                    cancelAppointment();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        // Close resources if needed
        scanner.close();
    }

    private static void getAppointmentById() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        try {
            Appointment appointment = hospitalService.getAppointmentById(appointmentId);
            System.out.println("Appointment details:\n" + appointment);
        } catch (PatientNumberNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAppointmentsForPatient() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        List<Appointment> patientAppointments = hospitalService.getAppointmentsForPatient(patientId);

        if (!patientAppointments.isEmpty()) {
            System.out.println("Appointments for Patient ID " + patientId + ":");
            for (Appointment appointment : patientAppointments) {
                System.out.println(appointment);
            }
        } else {
            System.out.println("No appointments found for Patient ID " + patientId);
        }
    }

    private static void getAppointmentsForDoctor() {
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        List<Appointment> doctorAppointments = hospitalService.getAppointmentsForDoctor(doctorId);

        if (!doctorAppointments.isEmpty()) {
            System.out.println("Appointments for Doctor ID " + doctorId + ":");
            for (Appointment appointment : doctorAppointments) {
                System.out.println(appointment);
            }
        } else {
            System.out.println("No appointments found for Doctor ID " + doctorId);
        }
    }

    private static void scheduleAppointment() {
        // Example: Input appointment details from the user and call scheduleAppointment
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        System.out.print("Enter Description: ");
        String description = scanner.next();

        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(java.sql.Date.valueOf(appointmentDate));
        appointment.setDescription(description);

        boolean success = hospitalService.scheduleAppointment(appointment);
        if (success) {
            System.out.println("Appointment scheduled successfully!");
        } else {
            System.out.println("Failed to schedule the appointment.");
        }
    }

    private static void updateAppointment() {
        // Example: Input appointment details from the user and call updateAppointment
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter Updated Appointment Date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        System.out.print("Enter Updated Description: ");
        String description = scanner.next();

        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setAppointmentId(appointmentId);
        updatedAppointment.setPatientId(patientId);
        updatedAppointment.setDoctorId(doctorId);
        updatedAppointment.setAppointmentDate(java.sql.Date.valueOf(appointmentDate));
        updatedAppointment.setDescription(description);

        boolean success = hospitalService.updateAppointment(updatedAppointment);
        if (success) {
            System.out.println("Appointment updated successfully!");
        } else {
            System.out.println("Failed to update the appointment.");
        }
    }

    private static void cancelAppointment() {
        // Example: Input appointment ID from the user and call cancelAppointment
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();

        boolean success = hospitalService.cancelAppointment(appointmentId);
        if (success) {
            System.out.println("Appointment canceled successfully!");
        } else {
            System.out.println("Failed to cancel the appointment.");
        }
    }
}
