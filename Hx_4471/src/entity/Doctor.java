package entity;

public class Doctor {
	    private int doctorId;
	    private String firstName;
	    private String lastName;
	    private String specialization;
	    private String contactNumber;
	    
	    public void printDoctorDetails() {
	        System.out.println("Doctor ID: " + doctorId);
	        System.out.println("First Name: " + firstName);
	        System.out.println("Last Name: " + lastName);
	        System.out.println("Specialization: " + specialization);
	        System.out.println("Contact Number: " + contactNumber);
	    }

	}

