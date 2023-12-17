package dao;
import entity.Appointment;
import java.util.List;
import myexceptions.PatientNumberNotFoundException;

public interface IHospitalService {
	Appointment getAppointmentById(int appointmentId) throws PatientNumberNotFoundException;

	    List<Appointment> getAppointmentsForPatient(int patientId);

	    List<Appointment> getAppointmentsForDoctor(int doctorId);

	    boolean scheduleAppointment(Appointment appointment);

	    boolean updateAppointment(Appointment appointment);

	    boolean cancelAppointment(int appointmentId);
	}

