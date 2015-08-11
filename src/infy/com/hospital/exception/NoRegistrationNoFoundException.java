package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class NoRegistrationNoFoundException extends Exception{
	public NoRegistrationNoFoundException(){
		super("No Records with the Registration No found!!");
	}

}
