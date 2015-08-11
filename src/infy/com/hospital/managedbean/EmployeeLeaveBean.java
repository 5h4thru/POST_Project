package infy.com.hospital.managedbean;

import infy.com.hospital.exception.LeaveException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.EmployeeLeaveManager;
import infy.com.hospital.to.EmployeeLeaveTO;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class EmployeeLeaveBean {
	private String leaveId;
	private String userName;
	private String status;
	private Date toDate;
	private Date fromDate;
	private String message;
	private String role;
	private String leaveType;
	private String phoneNo;

	public EmployeeLeaveBean(){
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			this.userName = (String) session.getAttribute("uname");
			this.role = (String) session.getAttribute("srole");
		} catch (Exception e) {
			this.message = e.getMessage();
			ErrorLogger.logError(this.getClass().getName(), "EmployeeLeaveBean", e.getMessage());
		}


	}

	@SuppressWarnings("deprecation")
	public String leaveApplication() throws Exception,LeaveException{
		EmployeeLeaveManager manager = new EmployeeLeaveManager();
		try {
			boolean check = manager.checkSchedule(this.toDate, this.fromDate, this.userName);
			if(check){
				EmployeeLeaveTO eto = new EmployeeLeaveTO();

				eto.setUserName(this.userName);
				eto.setToDate(this.toDate);
				eto.setFromDate(this.fromDate);
				eto.setLeaveType(this.leaveType);
				eto.setPhoneNo(this.phoneNo);

				this.leaveId = manager.leaveApplication(eto);
				this.message = "Leave applied successfully from "+fromDate.getDate()+"-"+fromDate.getMonth()+"-"+(fromDate.getYear()+1900)+" Date \nTo\n "+toDate.getDate()+"-"+toDate.getMonth()+"-"+(toDate.getYear()+1900)+" Date";

			}
		} catch(LeaveException e){
			this.message = e.getMessage();
			ErrorLogger.logError(this.getClass().getName(), "leaveApplication", e.getMessage());
		} catch (Exception e) {
			this.message = e.getMessage();
			ErrorLogger.logError(this.getClass().getName(), "leaveApplication", e.getMessage());
		}
		return message;
	}


	public String getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}
