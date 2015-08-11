package infy.com.hospital.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ReasonValidator implements Validator{

	public void validate(FacesContext facesContext, UIComponent component, Object object)
			throws ValidatorException {
		if(facesContext==null || component==null)
		{
			return ;
		}
		if(!(component instanceof UIInput))
		{
			return;
		}
		int countSpace=0;
		String value=(String)object;
		for(int count=0;count<value.length();count++)
		{
			if(value.charAt(count)==' ')
			{
				countSpace++;
			}
		}
		if(value.length()==countSpace)
		{
			FacesMessage message=new FacesMessage();
			message.setSummary("Only spaces not allowed");
			message.setDetail("Only spaces not allowed");
			throw new ValidatorException(message);
		}
		for(int i=0;i<value.length();i++){
			if(! (Character.isWhitespace(value.charAt(i)) || Character.isLetter(value.charAt(i)) || Character.isDigit(value.charAt(i)))){
				FacesMessage message=new FacesMessage();
				message.setSummary("Only Letters, Spaces and Numbers are allowed");
				message.setDetail("Only Letters, Spaces and Numbers are allowed");
				throw new ValidatorException(message);
			}
			
		}

	}
	}


