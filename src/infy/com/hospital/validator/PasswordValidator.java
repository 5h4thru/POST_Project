package infy.com.hospital.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class PasswordValidator implements Validator 

{


	public void validate(FacesContext facesContext, UIComponent component, Object arg2)
			throws ValidatorException 
	
	{
		if(facesContext==null || component==null)
		{
			return ;
		}
		if(!(component instanceof UIInput))
		{
			return;
		}
		int spl=0;
		int countSpace=0;
		int digit=0;
		int alpha=0;
		String pwd=(String)arg2;
		for(int i=0;i<pwd.length();i++)
		{
			if(pwd.charAt(i)==' ')
				countSpace++;
		}
		if(countSpace==pwd.length())
		{
			FacesMessage message=new FacesMessage();
			message.setSummary("Password cannot contain all spaces");
			message.setDetail("Password cannot contain all spaces");
			throw new ValidatorException(message);
		}
		if(pwd.length()<6 || pwd.length()>8)
		{
			FacesMessage message=new FacesMessage();
			message.setSummary("Number of characters for Password should be minimum 6 and maximum 8 characters");
			message.setDetail("Number of characters for Password should be minimum 6 and maximum 8 characters");
			throw new ValidatorException(message);
		}
		for(int i=0;i<pwd.length();i++)
		{
		if(!( Character.isWhitespace(pwd.charAt(i)) || Character.isLetter(pwd.charAt(i)) || Character.isDigit(pwd.charAt(i)) ))
		{
			spl++;
		}
		if(Character.isDigit(pwd.charAt(i)))
		{
			digit++;
		}
		if(Character.isLetter(pwd.charAt(i)))
		{
			alpha++;
		}
		}
		if(spl==0)
		{
			FacesMessage message=new FacesMessage();
			message.setSummary("Password should contain atleast one special character");
			message.setDetail("Password should contain atleast one special character");
			throw new ValidatorException(message);
		}
		if(digit==0 || alpha==0)
		{
			{
				FacesMessage message=new FacesMessage();
				message.setSummary("Password should be alphanumeric");
				message.setDetail("Password should be alpahanumeric");
				throw new ValidatorException(message);
			}
		}
	}

}
