package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class PatientDischargedAlreadyException extends Exception{
	public PatientDischargedAlreadyException(){
		super("Patient has already been discharged");
	}

}
