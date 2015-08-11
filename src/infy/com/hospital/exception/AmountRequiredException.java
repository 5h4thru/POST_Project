package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class AmountRequiredException extends Exception{
	public AmountRequiredException(){
		super("Please enter amount");
	}
}
