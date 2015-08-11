package infy.com.hospital.managedbean;

import infy.com.hospital.exception.InvalidPatientIdException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.InPatientEnquiryManager;
import infy.com.hospital.to.IpdAppointmentTO;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

public class InPatientEnquiryMB {
	private String patientUsername;
	private IpdAppointmentTO appointmentTO;
	private String message;

	public String getPatientUsername() {
		return patientUsername;
	}
	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}
	public IpdAppointmentTO getAppointmentTO() {
		return appointmentTO;
	}
	public void setAppointmentTO(IpdAppointmentTO appointmentTO) {
		this.appointmentTO = appointmentTO;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public void validate(FacesContext arg0, UIComponent arg1, Object patientname)
	throws ValidatorException {
		this.appointmentTO=null;
		this.message=null;
		String name=patientname.toString();
		if(name.length()<=15)
		{
		if(name.charAt(0)=='P' && name.length()!=1)
		{
			this.message=null;
			if(name.charAt(1)=='A')
			{
				this.message=null;
				if(name.length()==2)
				{
					this.message=null;
					FacesMessage fm=new FacesMessage();
					fm.setSummary("InvalidPatientId.Patient Username should start with PA followed by digits");
					fm.setDetail("InvalidPatientId.Patient Username should start with PA followed by digits");
					throw new ValidatorException(fm);	
				}
			}
			else
			{
				this.message=null;
				FacesMessage fm=new FacesMessage();
				fm.setSummary("InvalidPatientId.Patient Username should start with PA followed by digits");
				fm.setDetail("InvalidPatientId.Patient Username should start with PA followed by digits");
				throw new ValidatorException(fm);	
			}
		}		
		else
		{
			this.message=null;
			FacesMessage fm=new FacesMessage();
			fm.setSummary("InvalidPatientId.Patient Username should start with PA followed by digits");
			fm.setDetail("InvalidPatientId.Patient Username should start with PA followed by digits");
			throw new ValidatorException(fm);
		}
		}
		else
		{
			this.message=null;
			FacesMessage fm=new FacesMessage();
			fm.setSummary("Patient's Username cannot exceed the size 5");
			fm.setDetail("Patient's Username cannot exceed the size 5");
			throw new ValidatorException(fm);	
		}
	}



	public void inpatientEnquiry(ValueChangeEvent e) 
	{
		try {
			appointmentTO=new IpdAppointmentTO();
			appointmentTO=null;
			this.message=null;
			patientUsername=e.getNewValue().toString();
			InPatientEnquiryManager ipm=new InPatientEnquiryManager();		
			appointmentTO=ipm.inpatientEnquiry(patientUsername);		
		}
		
		catch(InvalidPatientIdException ipe)
		{
			this.message=ipe.getMessage();
			ErrorLogger.logError(this.getClass().getName(),"InPatientEnquiry",ipe.getMessage());
		}
		catch (Exception e1)
		{
			this.message=e1.getMessage();
			ErrorLogger.logError(this.getClass().getName(),"InPatientEnquiry",e1.getMessage());
		}

	}


}
