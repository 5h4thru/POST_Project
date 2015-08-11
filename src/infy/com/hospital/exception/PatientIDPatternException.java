package infy.com.hospital.exception;

@SuppressWarnings("serial")
public class PatientIDPatternException extends Exception{
	public PatientIDPatternException(){
		super("Patient Id should start with PA");
	}
}
