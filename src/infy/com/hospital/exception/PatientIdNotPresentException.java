package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class PatientIdNotPresentException extends Exception{
	public PatientIdNotPresentException(){
		super("No patient with the specified registration No");
	}
}
