package infy.com.hospital.exception;

@SuppressWarnings("serial")
public class LeaveException extends Exception {
	public LeaveException(){
		super("You cannot apply for a leave in the specified dates");
	}
}
