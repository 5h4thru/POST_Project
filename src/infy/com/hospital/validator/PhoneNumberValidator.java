package infy.com.hospital.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class PhoneNumberValidator implements Validator {

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
		String pNo=object.toString();
		int a=pNo.length();
                for(int i=0;i<pNo.length();i++){
			if(!((Character.isDigit(pNo.charAt(i))))){
				FacesMessage message=new FacesMessage();
				message.setSummary("Only Numbers are allowed");
				message.setDetail("Only Numbers are allowed");
				throw new ValidatorException(message);
			}
		if(a<10 || a>12)
		{
			/* if it is only spaces then throw an exception */
			FacesMessage message=new FacesMessage();
			message.setSummary("PhoneNo cannot be less than 10 or greater than 12");
			message.setDetail("PhoneNo cannot be less than 10 or greater than 12");
			throw new ValidatorException(message);
		}
		
	}

	}
}

