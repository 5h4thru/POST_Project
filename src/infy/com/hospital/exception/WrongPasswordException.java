package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class WrongPasswordException extends Exception{
	public WrongPasswordException()
	{
		super("Wrong password");
	}

}
