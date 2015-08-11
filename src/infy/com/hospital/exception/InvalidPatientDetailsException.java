package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class InvalidPatientDetailsException extends Exception 
{
public InvalidPatientDetailsException()
{
	super("Registration No and Patient Type do not match");
}
}
