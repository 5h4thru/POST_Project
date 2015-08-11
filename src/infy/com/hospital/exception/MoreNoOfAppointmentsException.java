package infy.com.hospital.exception;

@SuppressWarnings("serial")
public class MoreNoOfAppointmentsException extends Exception {

	public MoreNoOfAppointmentsException() {
		super("More than two appointments cannot be made for the same date" );
	}
}
