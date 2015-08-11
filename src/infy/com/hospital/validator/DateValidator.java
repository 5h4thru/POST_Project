package infy.com.hospital.validator;





import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DateValidator implements Validator {
	public void validate(FacesContext facesContext, UIComponent component,
			Object object) throws ValidatorException {
		
		
		if (facesContext == null || component == null) {
			/* throw new NullPointerException(); */
			return;
		}
		if (!(component instanceof UIInput)) {
			return;
		}
		FacesMessage message = new FacesMessage();
		Date d = (Date) object;
		Date d1 = new Date();
		
		if(component.getId()=="dateofpurchase")
		{
			if (d.after(d1)) {
				message.setSummary("Date cannot not be greater than current date");
				message.setDetail("Date cannot not be greater than current date");
				throw new ValidatorException(message);
			}
		}
		if(component.getId()=="date")
		{
		
		if (d.before(d1)) {
			message.setSummary("Date cannot not be lesser than current date");
			message.setDetail("Date cannot not be lesser than current date");
			throw new ValidatorException(message);
		}
		}
	}
}