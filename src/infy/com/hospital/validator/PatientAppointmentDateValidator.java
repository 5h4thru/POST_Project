package infy.com.hospital.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class PatientAppointmentDateValidator implements Validator {

	@SuppressWarnings("deprecation")
	public void validate(FacesContext arg0, UIComponent arg1, Object appDate) throws ValidatorException {
		if (arg0 == null || arg1 == null) {
			return;
		}
		if (!(arg1 instanceof UIInput)) {
			return;
		}
		
		Date today = new Date();
		Date date = (Date) appDate;
		
		long dateTime = date.getTime();
		long todayTime = today.getTime();
		
		int day = ((Long)((dateTime - todayTime)/(1000*60*60*24))).intValue();
		
		FacesMessage message = new FacesMessage();
		Date oneMonthDate = new Date();
		oneMonthDate.setMonth(oneMonthDate.getMonth() + 1);
		oneMonthDate.setDate(oneMonthDate.getDate() - 1);
		
		if(day < 0) {
			message.setSummary("Appointment Date cannot be less than present date");
			message.setDetail("Appointment Date cannot be less than present date");
			throw new ValidatorException(message);
		}
		
		if(date.after(oneMonthDate)) {
			message.setSummary("Appointment Date cannot be greater than one month after present date");
			message.setDetail("Appointment Date cannot be greater than one month after present date");
			throw new ValidatorException(message);
		}
	}
}
