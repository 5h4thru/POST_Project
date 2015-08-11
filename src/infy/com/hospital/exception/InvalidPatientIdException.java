package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class InvalidPatientIdException extends Exception 
{
public InvalidPatientIdException()
{
	super("No Records Found");
}
}
