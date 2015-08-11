package infy.com.hospital.exception;

@SuppressWarnings("serial")
public class BusyScheduleException extends Exception {
	
	public BusyScheduleException() {
		super("Doctor not available for the selected slot");
	}
}
