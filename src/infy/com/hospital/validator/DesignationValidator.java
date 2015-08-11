package infy.com.hospital.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DesignationValidator implements Validator{

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
	throws ValidatorException {
		String designation = (String) arg2;
		if(!(designation.equals("DC"))){
			FacesMessage msg=new FacesMessage();
			msg.setDetail("Designation should be DC");
			msg.setSummary("Designation should be DC");
			throw new ValidatorException(msg);
		}

	}

}
