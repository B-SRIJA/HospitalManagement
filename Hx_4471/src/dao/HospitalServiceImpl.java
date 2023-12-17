package dao;

import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalServiceImpl implements IHospitalService {
	
	@Override
	public Appointment getAppointmentById(int appointmentId) throws PatientNumberNotFoundException {
	    try (Connection connection = DBConnection.getConnection()) {
	        String sql = "SELECT * FROM Appointment WHERE appointmentId = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, appointmentId);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return mapAppointment(resultSet);
	                } else {
	                    throw new PatientNumberNotFoundException("Appointment not found with ID: " + appointmentId);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error retrieving appointment by ID", e);
	    }
	}

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Appointment WHERE doctorId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, doctorId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        appointments.add(mapAppointment(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM Appointment WHERE appointmentId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, appointmentId);

                int rowsDeleted = statement.executeUpdate();
                return rowsDeleted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Appointment WHERE patientId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, patientId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        appointments.add(mapAppointment(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO Appointment (patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, appointment.getPatientId());
                statement.setInt(2, appointment.getDoctorId());
                statement.setDate(3, new Date(appointment.getAppointmentDate().getTime()));
                statement.setString(4, appointment.getDescription());

                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, appointment.getPatientId());
                statement.setInt(2, appointment.getDoctorId());
                statement.setDate(3, new Date(appointment.getAppointmentDate().getTime()));
                statement.setString(4, appointment.getDescription());
                statement.setInt(5, appointment.getAppointmentId());

                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private Appointment mapAppointment(ResultSet resultSet) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(resultSet.getInt("appointmentId"));
        appointment.setPatientId(resultSet.getInt("patientId"));
        appointment.setDoctorId(resultSet.getInt("doctorId"));
        appointment.setAppointmentDate(resultSet.getDate("appointmentDate"));
        appointment.setDescription(resultSet.getString("description"));
        return appointment;
    }


}
