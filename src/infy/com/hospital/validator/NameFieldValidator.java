package infy.com.hospital.validator;

import infy.com.hospital.managedbean.BioMedicalEquipmentBean;
import infy.com.hospital.managedbean.DrugBean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class NameFieldValidator implements Validator{

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
		int countSpace=0;
		String value=(String)object;
		
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
			message.setSummary("Only spaces not allowed in "+component.getId());
			message.setDetail("Only spaces not allowed in "+component.getId());
			throw new ValidatorException(message);
		}
		for(int count=0;count<value.length();count++)
		{
			if(Character.isDigit(value.charAt(count)))
			{
				FacesMessage message=new FacesMessage();
				message.setSummary("Number are not allowed in "+component.getId());
				message.setDetail("Number are not allowed in "+component.getId());
				throw new ValidatorException(message);
			}
			
			new DrugBean().mess();
			new BioMedicalEquipmentBean().mess();
			
		}
		for(int i=0;i<value.length();i++){
			if(!((Character.isWhitespace(value.charAt(i)))||Character.isLetter(value.charAt(i)))){
			
				FacesMessage message=new FacesMessage();
				message.setSummary("Special Characters are not allowed in "+component.getId());
				message.setDetail("Special Characters are not allowed in "+component.getId());
				throw new ValidatorException(message);
			}
			
		}
		

	}
	}

