package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class BillListEmptyException extends Exception{
	public BillListEmptyException(){
		super("No Bills for the Patient");
	}
}
