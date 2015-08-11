package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class CannotBeDischargedException extends Exception{
	public CannotBeDischargedException(){
		super("Patient Cannot be Discharged");
	}

}
