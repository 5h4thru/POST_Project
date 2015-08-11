package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class InvalidScheduleException extends Exception 

{

	public InvalidScheduleException()
	{
		super("Invalid Schedule.Select different slot or OT or Date of surgery");
	}
}
