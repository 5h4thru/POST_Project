package infy.com.hospital.managedbean;

import infy.com.hospital.exception.BusyScheduleException;
import infy.com.hospital.exception.InvalidDoctorIdException;
import infy.com.hospital.exception.MoreNoOfAppointmentsException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.PatientManager;
import infy.com.hospital.to.DoctorTO;
import infy.com.hospital.to.OpdAppointmentTO;
import infy.com.hospital.to.PatientTO;
import infy.com.hospital.to.PaymentTO;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

public class PatientBean {
	private String patientId;
	private String firstName;
	private String lastName;
	private String contactPerson;
	private String address;
	private String message;
	private String role;
	private Long phoneNo;
	private String department;
	private List<PaymentTO> paymentList;
	private String doctorId;
	private Date date;
	private String slot;
	private double consultationFees;
	private String payNo;
	private List<SelectItem> listOfDepartment;
	private String reasonForAppointment;
	private String status;



	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public List<PaymentTO> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<PaymentTO> paymentList) {
		this.paymentList = paymentList;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public double getConsultationFees() {
		return consultationFees;
	}
	public void setConsultationFees(double consultationFees) {
		this.consultationFees = consultationFees;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public List<SelectItem> getListOfDepartment() {
		return listOfDepartment;
	}
	public void setListOfDepartment(List<SelectItem> listOfDepartment) {
		this.listOfDepartment = listOfDepartment;
	}
	public String getReasonForAppointment() {
		return reasonForAppointment;
	}
	public void setReasonForAppointment(String reasonForAppointment) {
		this.reasonForAppointment = reasonForAppointment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public PatientBean()
	{
		PatientManager patientManager=new PatientManager();
		try
		{
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			this.patientId = (String) session.getAttribute("uname");
			this.role = (String) session.getAttribute("srole");
			
		
			
			
		//this.patientId="PA102";
		PatientTO patientTO=patientManager.findPatient(patientId);
		this.firstName=patientTO.getFirstName();
		this.lastName=patientTO.getLastName();
		this.contactPerson=patientTO.getContactPerson();
		this.address=patientTO.getAddress();
		this.phoneNo=patientTO.getPhoneNo();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "patientBean", e.getMessage());
			this.message=e.getMessage();
		}
		
	}

	public String checkAppointment() {
		try {
			this.consultationFees = 0;
			this.reasonForAppointment = null;
			
			OpdAppointmentTO oTO = new OpdAppointmentTO();
			oTO.setDoctorId(this.doctorId);
			oTO.setPatientId(this.patientId);
			oTO.setDateOfAdmission(this.date);
			oTO.setSlot(this.slot);
			oTO.setReasonForAppointment(this.reasonForAppointment);

			PatientManager manager = new PatientManager();
			DoctorTO docTO = manager.checkAppointment(oTO);
			
			this.consultationFees = docTO.getConsultationFees();
			return "success";
		}
		
		catch(InvalidDoctorIdException e) {
			ErrorLogger.logError(this.getClass().getName(), "checkAppointment", e.getMessage());
			this.message = e.getMessage();
			return "failure";
		}
		
		catch(MoreNoOfAppointmentsException e) {
			ErrorLogger.logError(this.getClass().getName(), "checkAppointment", e.getMessage());
			this.message = e.getMessage();
			return "failure";
		}
		
		catch(BusyScheduleException e) {
			ErrorLogger.logError(this.getClass().getName(), "checkAppointment", e.getMessage());
			this.message = e.getMessage();
			return "failure";
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "checkAppointment", e.getMessage());
			this.message = e.getMessage();
			return "failure";
		}
	}
	
	public String makePayment() {
		try {
			OpdAppointmentTO oTO = new OpdAppointmentTO();
			oTO.setDoctorId(this.doctorId);
			oTO.setPatientId(this.patientId);
			oTO.setDateOfAdmission(this.date);
			oTO.setSlot(this.slot);
			oTO.setReasonForAppointment(this.reasonForAppointment);
			
			PaymentTO pTO = new PaymentTO();
			pTO.setDoctorId(this.doctorId);
			pTO.setPatientId(this.patientId);
			pTO.setDateOfPayment(this.date);
			pTO.setAmount(this.consultationFees);
			
			PatientManager manager = new PatientManager();
			this.payNo = manager.makePayment(pTO, oTO);
			this.message = "Appointment successfully made: your payment no is :" + this.payNo;
			return "success";
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "makePayment", e.getMessage());
			this.message = e.getMessage();
			return "failure";
		}
	}
	

	public String viewPayments()
	{
		try
		{
		PatientManager patientManager=new PatientManager();
		this.paymentList=patientManager.viewPayments(patientId);
		return "success";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "viewPayments", e.getMessage());
			this.message=e.getMessage();
			return "failure";
		}
	}
	
}
