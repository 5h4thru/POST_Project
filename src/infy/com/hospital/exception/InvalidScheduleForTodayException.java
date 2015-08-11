package infy.com.hospital.exception;
@SuppressWarnings("serial")
public class InvalidScheduleForTodayException extends Exception 
{
public InvalidScheduleForTodayException()
{
	super("Cannot book this slot for today");
}
}
