package infy.com.hospital.exception;

@SuppressWarnings("serial")
public class NoDoctorFoundException extends Exception {

		public NoDoctorFoundException() {
			super("No doctors available for the given search criteria");
		}
}
