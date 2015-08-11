package infy.com.hospital.managedbean;

import infy.com.hospital.exception.NoAppointmentException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.PatientManager;
import infy.com.hospital.to.OpdAppointmentTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class PatientAppointmentBean {
	List<OpdAppointmentTO> scheduleList;
	private String patientId;
	private String message;
	private List<OpdAppointmentTO> removeThese;
	
	public PatientAppointmentBean() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			this.patientId = (String) session.getAttribute("uname");
			PatientManager manager = new PatientManager();

			scheduleList = manager.viewAppointmentMade(this.patientId);
		}
		
		catch(NoAppointmentException e) {
			ErrorLogger.logError(this.getClass().getName(), "PatientAppointmentBean", e.getMessage());
			this.message = e.getMessage();
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "PatientAppointmentBean", e.getMessage());
			this.message = e.getMessage();
		}
	}
	
	public List<OpdAppointmentTO> getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(List<OpdAppointmentTO> scheduleList) {
		this.scheduleList = scheduleList;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<OpdAppointmentTO> getRemoveThese() {
		return removeThese;
	}
	public void setRemoveThese(List<OpdAppointmentTO> removeThese) {
		this.removeThese = removeThese;
	}
	
	@SuppressWarnings("deprecation")
	public String cancelAppointment() {
		try {
			PatientManager manager = new PatientManager();
			this.removeThese = new ArrayList<OpdAppointmentTO>();
			List<OpdAppointmentTO> temp = new ArrayList<OpdAppointmentTO>();
			
			for (OpdAppointmentTO oTO : this.scheduleList) {
				if(oTO.isSelected()) {
					this.removeThese.add(oTO);
					Date admission = oTO.getDateOfAdmission();
					Date today = new Date();
					
					if(admission.getDate() == today.getDate() && admission.getMonth() == admission.getMonth() && admission.getYear() == today.getYear()) {
						this.message = "Today's Appointments cannot be cancelled";
						return "failure";
					}
				}
				else {
					temp.add(oTO);
				}
			}
			
			if(this.removeThese.size() == 0) {
				this.message = "Please select atleast one appointment to cancel";
				return "failure";
			}
			
			for (OpdAppointmentTO oTO : this.removeThese) {
				manager.cancelAppointment(oTO.getRegistrationNo());
			}
			
			this.scheduleList = temp;
			return "success";
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "cancelAppointment", e.getMessage());
			this.message = e.getMessage();
			return "failure";
		}
	}
}
