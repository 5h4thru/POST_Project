package infy.com.hospital.managedbean;

import infy.com.hospital.exception.NoAppointmentException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.AppointmentManager;
import infy.com.hospital.to.AppointmentTO;
import infy.com.hospital.to.OpdAppointmentTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;



public class AppointmentBean {
private Date date;
private String message;
private List<SelectItem> customerList;
private String userName;
private List<AppointmentTO> ato;
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public List<SelectItem> getCustomerList() {
	return customerList;
}
public void setCustomerList(List<SelectItem> customerList) {
	this.customerList = customerList;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public List<AppointmentTO> getAto() {
	return ato;
}
public void setAto(List<AppointmentTO> ato) {
	this.ato = ato;
}
public AppointmentBean()
{
	FacesContext ctx=FacesContext.getCurrentInstance();
	ExternalContext etx=ctx.getExternalContext();
	HttpSession session=(HttpSession) etx.getSession(true);
	this.userName=(String) session.getAttribute("suser");
	ato=new ArrayList<AppointmentTO>();
}
public String viewAppointment()
{
	try {
		this.message=null;
	List<OpdAppointmentTO> opList=new ArrayList<OpdAppointmentTO>();
	opList=new AppointmentManager().viewAppointment(userName,date);
	if(opList.isEmpty()==true)
	{
		 throw new NoAppointmentException();	
	}
	else
	{
		for(OpdAppointmentTO o:opList)
		{
			AppointmentTO ap=new AppointmentTO();
			//ato=new ArrayList<AppointmentTO>();
			ap.setPatientId(o.getPatientId());
			ap.setReasonOfAppointment(o.getReasonForAppointment());
			ap.setSlot(o.getSlot());
ap.setDoctorId(this.userName);
//System.out.println(ap.getDoctorId());
ato.add(ap);
		}
		return "success";
	}
	}
	catch (NoAppointmentException e) {
		ErrorLogger.logError(this.getClass().getName(),"viewAppointment", e.getMessage());
		this.message=e.getMessage();
		return "failure";
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"viewAppointment", e.getMessage());
		this.message=e.getMessage();
		return "failure";
	}
	
	
}

}
