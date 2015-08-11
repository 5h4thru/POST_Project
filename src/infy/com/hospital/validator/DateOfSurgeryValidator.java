package infy.com.hospital.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DateOfSurgeryValidator implements Validator 

{

	public void validate(FacesContext arg0, UIComponent arg1, Object date)
			throws ValidatorException 
			
	{
//		if(arg0==null || arg1==null)
//		{
//			throw new NullPointerException();
//		}
		if(!(arg1 instanceof UIInput))
		{
			return;
		}
		Date d=(Date) date;
		Date today=new Date();
		if(d.getDate()==today.getDate() && d.getMonth()==today.getMonth() && d.getYear()==today.getYear())
		{
			
		}
		else
		{
		if(d.before(today))
		{
			FacesMessage message=new FacesMessage();
			message.setSummary("Date of surgery should be greater than today's date");
			message.setDetail("Date of surgery should be greater than today's date");
			throw new ValidatorException(message);
		}
		}
	}

}
