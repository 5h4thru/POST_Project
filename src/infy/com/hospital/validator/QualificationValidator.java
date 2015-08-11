package infy.com.hospital.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class QualificationValidator implements Validator{

	public void validate(FacesContext facesContext, UIComponent component, Object object)
	throws ValidatorException {
		// TODO Auto-generated method stub
		if(facesContext==null || component==null)
		{
			return ;
		}
		if(!(component instanceof UIInput))
		{
			return;
		}
		String value=(String)object;	

		int countSpace=0;

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
		for(int i=0;i<value.length();i++){
			if(!((Character.isWhitespace(value.charAt(i)))||Character.isLetter(value.charAt(i)))){
				FacesMessage message=new FacesMessage();
				message.setSummary("Special Characters are not allowed");
				message.setDetail("Special Characters are not allowed");
				throw new ValidatorException(message);
			}

		}

	}
}
