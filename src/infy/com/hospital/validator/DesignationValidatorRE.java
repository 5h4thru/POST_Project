package infy.com.hospital.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DesignationValidatorRE implements Validator{

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
	throws ValidatorException {
		String designation = (String) arg2;
		if(!(designation.equals("RE"))){
			FacesMessage msg=new FacesMessage();
			msg.setDetail("Designation should be RE");
			msg.setSummary("Designation should be RE");
			throw new ValidatorException(msg);
		}

	}

}
