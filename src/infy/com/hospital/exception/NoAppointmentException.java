package infy.com.hospital.exception;

@SuppressWarnings("serial")
public class NoAppointmentException extends Exception {
	
	public NoAppointmentException() {
		super("No Appointments are scheduled");
	}
}
