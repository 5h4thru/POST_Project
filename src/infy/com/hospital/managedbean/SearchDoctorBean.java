package infy.com.hospital.managedbean;

import infy.com.hospital.exception.NoDoctorFoundException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.DoctorManager;
import infy.com.hospital.to.DoctorTO;
import infy.com.hospital.to.OpdAppointmentTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

public class SearchDoctorBean {
	private String userName;
	private String role;
	private String message;
	private String department;
	private String doctorId;
	private String slot;
	private List<OpdAppointmentTO> scheduleList;
	private List<SelectItem> listOfDepartment;
	private List<DoctorTO> listDoctorTO;
	private List<DoctorTO> details;
	
	public SearchDoctorBean() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			this.userName = (String) session.getAttribute("uname");
			this.role = (String) session.getAttribute("srole");

			DoctorManager manager = new DoctorManager();
			this.listDoctorTO = manager.getDepartmentList();
			this.listOfDepartment = new ArrayList<SelectItem>();
			
			for (DoctorTO doctorTO : this.listDoctorTO) {
				this.listOfDepartment.add(new SelectItem(doctorTO.getDepartment()));
			}
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "SearchDoctorBean", e.getMessage());
			this.message = e.getMessage();
		}
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public List<OpdAppointmentTO> getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(List<OpdAppointmentTO> scheduleList) {
		this.scheduleList = scheduleList;
	}
	public List<SelectItem> getListOfDepartment() {
		return listOfDepartment;
	}
	public void setListOfDepartment(List<SelectItem> listOfDepartment) {
		this.listOfDepartment = listOfDepartment;
	}
	public List<DoctorTO> getListDoctorTO() {
		return listDoctorTO;
	}
	public void setListDoctorTO(List<DoctorTO> listDoctorTO) {
		this.listDoctorTO = listDoctorTO;
	}
	public List<DoctorTO> getDetails() {
		return details;
	}
	public void setDetails(List<DoctorTO> details) {
		this.details = details;
	}
	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	public String getDoctor() {
		try {
			DoctorManager manager = new DoctorManager();
			if(this.doctorId == null && this.department == null && this.slot == null) {
				this.message = "Please select a value in atleast one of the search criteria";
				return "failure";
			}
			this.details = manager.getDoctor(this.doctorId, this.department, this.slot);
			return "success";
		}
		
		catch(NoDoctorFoundException e) {
			ErrorLogger.logError(this.getClass().getName(), "getDoctor", e.getMessage());
			this.message = e.getMessage();
			return "failure";
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDoctor", e.getMessage());
			this.message = e.getMessage();
			return "failure";
		}
	}
}
