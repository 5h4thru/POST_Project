package infy.com.hospital.exception;

@SuppressWarnings("serial")
public class NoEmployees extends Exception {
	public NoEmployees(){
		super("No employees are available");
	}
}
