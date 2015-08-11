package infy.com.hospital.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class FnameValidator  implements Validator{

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		if(!(arg1 instanceof UIInput))
		{
			return;
		}
		int countSpace=0;
		String value=(String)arg2;
		/* the value entered  should not be only spaces */
		for(int count=0;count<value.length();count++)
		{
			if(value.charAt(count)==' ')
			{
				countSpace++;
			}
		}
		if(value.length()==countSpace)
		{
			/* if it is only spaces then throw an exception */
			FacesMessage message=new FacesMessage();
			message.setSummary("Only spaces not allowed in First Name");
			message.setDetail("Only spaces not allowed in First Name");
			throw new ValidatorException(message);
		}
		
		if(value.length()<3 || value.length()>20)
		{
			FacesMessage message=new FacesMessage();
			message.setSummary("First Name should be minimum 3 and maximum 20");
			message.setDetail("First Name should be minimum 3 and maximum 20");
			throw new ValidatorException(message);
		}
		
		for(int i=0;i<value.length();i++)
		{
			if(!(Character.isLetter(value.charAt(i))))
			{
				
				FacesMessage msg=new FacesMessage();
				msg.setSummary("User Name can have only alphabets");
				msg.setDetail("User Name can have only alphabets");
				throw new ValidatorException(msg);
					
			}
		}
	}
}
