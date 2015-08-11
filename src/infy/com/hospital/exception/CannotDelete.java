package infy.com.hospital.exception;

@SuppressWarnings("serial")
public class CannotDelete extends Exception {
	public CannotDelete(){
		super("Cannot delete the selected doctor because he is in charge of a patient");
	}
}
