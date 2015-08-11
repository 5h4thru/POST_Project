package infy.com.hospital.exception;

@SuppressWarnings("serial")
public class NoProfileRequests extends Exception{
	public NoProfileRequests(){
		super("No Profile Change Requests");
	}
}