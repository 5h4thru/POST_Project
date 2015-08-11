package infy.com.hospital.managedbean;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.DoctorManager;
import infy.com.hospital.to.DoctorTO;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.EmployeeTO;
import infy.com.hospital.to.OTTO;
import infy.com.hospital.to.SurgeryScheduleTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

public class DoctorBean {
	private int employeeNo;
	private String employeeName;
	private String address;
	private Long phoneNo;
	private String department;
	private String qualification;
	private String message;
	private String userName;
	private String role;
	private int consultationFees;
	private String drugName;
	private int quantity;
	private String successMsg;
	private String patientId;
	private Date dateOfSurgery;
	private String slot;
	private String surgeryName;
	private int otNo;
	private String succSurMsg;
	private String urgent;
	private String manufacturer;
	private List<SelectItem> otList=new ArrayList<SelectItem>();;
	private List<OTTO> list1=new ArrayList<OTTO>();
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public int getConsultationFees() {
		return consultationFees;
	}
	public void setConsultationFees(int consultationFees) {
		this.consultationFees = consultationFees;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public Date getDateOfSurgery() {
		return dateOfSurgery;
	}
	public void setDateOfSurgery(Date dateOfSurgery) {
		this.dateOfSurgery = dateOfSurgery;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	public int getOtNo() {
		return otNo;
	}
	public void setOtNo(int otNo) {
		this.otNo = otNo;
	}
	public String getSuccSurMsg() {
		return succSurMsg;
	}
	public void setSuccSurMsg(String succSurMsg) {
		this.succSurMsg = succSurMsg;
	}
	public String getUrgent() {
		return urgent;
	}
	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public List<SelectItem> getOtList() {
		return otList;
	}
	public void setOtList(List<SelectItem> otList) {
		this.otList = otList;
	}
	public List<OTTO> getList1() {
		return list1;
	}
	public void setList1(List<OTTO> list1) {
		this.list1 = list1;
	}
	public DoctorBean() 
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);
		this.userName=(String)session.getAttribute("uname");
		this.role=(String)session.getAttribute("srole");
		try{
			DoctorManager dm=new DoctorManager();
			EmployeeTO eto=dm.getEmployeeDetails(userName);
			this.employeeName=eto.getEmployeeName();
			this.qualification=eto.getQualification();
			this.address=eto.getAddress();
			this.phoneNo=eto.getPhoneNo();
			DoctorTO dto=dm.getDoctorDetails(userName);
			this.consultationFees=dto.getConsultationFees();
			this.department=dto.getDepartment();
			
			this.list1=dm.listOfOT();
			for(OTTO t:this.list1)
			{
				
				SelectItem s=new SelectItem(t.getOtNo(),t.getOtNo().toString());
				this.otList.add(s);
			}
			
			this.message="";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"DoctorBean Constructor",e.getMessage());
			this.message=e.getMessage();
		}
	}
public String scheduleSurgery()
	
	{
		DoctorManager dm=new DoctorManager();
		SurgeryScheduleTO sst=new SurgeryScheduleTO();
		sst.setDateOfSurgery(this.dateOfSurgery);
		sst.setOtNo(this.otNo);
		sst.setPatientId(this.patientId);
		sst.setSlot(this.slot);
		sst.setSurgeryName(this.surgeryName);
		sst.setDoctorId(this.userName);
		sst.setBookingStatus('B');
		try 
		{
			
			if(dm.validatePatientId(this.patientId))
			{
			String bId=dm.scheduleSurgery(sst);
			int m=this.dateOfSurgery.getMonth()+1;
			String d[]=this.dateOfSurgery.toString().split(" ");
			String date=d[2]+"-"+m+"-"+d[5];
			this.succSurMsg="OT number "+this.otNo+" has been booked for surgery "+this.surgeryName+" on date "+date+" and booking Id is "+bId;
			this.message="";
			return "success";
			}
			else
			{
				this.message="Invalid Patient Id";
				return "fail";
			}
			
		} 
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(),"scheduleSurgery",e.getMessage());
			this.message=e.getMessage();
			return "fail";
		}
		
	}
	/*
	public String leaveApplication(){
		return null;
	}
	public void showConfirmPassword(ValueChangeEvent event){
		
	}*/
	public String updateDoctorDetails(){
		EmployeeTO eto=new EmployeeTO();
		DoctorTO dto=new DoctorTO();
		eto.setEmployeeName(this.employeeName);
		eto.setQualification(this.qualification);
		eto.setAddress(this.address);
		eto.setPhoneNo(this.phoneNo);
		dto.setConsultationFees(this.consultationFees);
		dto.setDepartment(this.department);
		dto.setUserName(this.userName);
		try{
			DoctorManager dm=new DoctorManager();
			Boolean b=dm.updateDoctorDetails(dto, eto);
			if(b==true){
				this.message="Your details are sent for approval";
				return "success";
			}
			else
				return "fail";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"DoctorBean Constructor",e.getMessage());
			this.message=e.getMessage();
			return "fail";
		}
		
	}
	public String requestMedicine(){
		try
		{  
			DrugRequestTO drugRequestTO=new DrugRequestTO();
			drugRequestTO.setManufacturer(manufacturer);
			drugRequestTO.setDate(new Date());
			drugRequestTO.setDoctorId("DC101");
			drugRequestTO.setDrugName(drugName);
			drugRequestTO.setQuantity(quantity);
			drugRequestTO.setUrgent(urgent);
			drugRequestTO.setReqStatus('W');
	
			new DoctorManager().requestMedicine(drugRequestTO);
			this.message="Your Request for "+drugName+" with quantity "+quantity+" has been successfully sent for approval";
			return "success";
		}
		catch(Exception e)
		{
			this.message=e.getMessage();
			ErrorLogger.logError(e.getClass().getName(),"requestMedicine", e.getMessage());
			return "failure";
		}
	}
}
