package infy.com.hospital.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class NameValidator  implements Validator{

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
			message.setSummary("Only spaces not allowed");
			message.setDetail("Only spaces not allowed");
			throw new ValidatorException(message);
		}
		for(int i=0;i<value.length();i++)
		{
			if(!((Character.isDigit(value.charAt(i)))||Character.isLetter(value.charAt(i))))
			{
				
				FacesMessage msg=new FacesMessage();
				msg.setSummary("User Name can have only alphabets and numbers");
				msg.setDetail("User Name can have only alphabets and numbers");
				throw new ValidatorException(msg);
					
			}
		}
	}
}
