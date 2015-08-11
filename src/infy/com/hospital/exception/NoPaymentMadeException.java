package infy.com.hospital.exception;

public class NoPaymentMadeException  extends Exception{
	public NoPaymentMadeException()
	{
		super("No Payment Was Made By Patient");
	}
}
