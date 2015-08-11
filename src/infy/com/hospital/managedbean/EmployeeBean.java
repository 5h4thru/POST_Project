package infy.com.hospital.managedbean;

import infy.com.hospital.exception.NoEmployees;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.EmployeeManager;
import infy.com.hospital.to.EmployeeTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;



public class EmployeeBean {
	private String message;
	private EmployeeTO employeeTO = new EmployeeTO();
	private List<SelectItem> namesList = new ArrayList<SelectItem>();
	private List<SelectItem> docList = new ArrayList<SelectItem>();
	private String userName;
	private String select;
	private List<EmployeeTO> toList = new ArrayList<EmployeeTO>();

	public EmployeeBean(){
		try {
			EmployeeManager manager = new EmployeeManager();
			this.namesList = manager.getNames();	
			this.docList = manager.getDocs();
			this.message = null;
		} catch (NoEmployees e) {
			ErrorLogger.logError(this.getClass().getName(), "EmployeeBean", e.getMessage());
			this.message = e.getMessage();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "EmployeeBean", e.getMessage());
			this.message = e.getMessage();
		}
	}

	public String addReceptionist(){
		try {
			EmployeeManager manager = new EmployeeManager();
			userName = manager.addReceptionist(this.employeeTO);
			this.message = "Successfully added receptionist with Id: "+userName;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addReceptionist", e.getMessage());
			this.message = e.getMessage();
		}
		return "";
	}

	public String addDoctor(){
		try {
			EmployeeManager manager = new EmployeeManager();
			userName = manager.addDoctor(this.employeeTO);
			this.message = "Successfully added doctor with Id: "+userName;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addDoctor", e.getMessage());
			this.message = e.getMessage();
		}
		return "";
	}

	public String updateEmployee(){
		try {
			EmployeeManager manager = new EmployeeManager();
			for (int j = 0; j < toList.size(); j++) {
				employeeTO.setEmployeeName(toList.get(j).getEmployeeName());
				employeeTO.setDateOfJoining(toList.get(j).getDateOfJoining());
				employeeTO.setQualification(toList.get(j).getQualification());
				employeeTO.setAddress(toList.get(j).getAddress());
				employeeTO.setPhoneNo(toList.get(j).getPhoneNo());
				employeeTO.setDesignation(toList.get(j).getDesignation());
				employeeTO.setConsultationFees(toList.get(j).getConsultationFees());
				employeeTO.setDepartment(toList.get(j).getDepartment());
				employeeTO.setSlot1(toList.get(j).getSlot1());
				employeeTO.setSlot2(toList.get(j).getSlot2());
				employeeTO.setSlot3(toList.get(j).getSlot3());
				employeeTO.setUserName(userName);
				employeeTO.setEmployeeNo(toList.get(j).getEmployeeNo());
			}
			manager.updateDoctor(employeeTO);
			this.message = "Employee "+userName+" updated successfully";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateEmployee", e.getMessage());
			this.message = e.getMessage();
		}
		return "";	
	}

	public String delEmployee(){
		try {
			EmployeeManager manager = new EmployeeManager();
			manager.delEmployee(this.userName);
			namesList.clear();
			namesList = manager.getNames();
			this.message = "Deleted successfully";
		} catch (NoEmployees e) {
			ErrorLogger.logError(this.getClass().getName(), "delEmployee", e.getMessage());
			this.message = e.getMessage();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "delEmployee", e.getMessage());
			this.message = e.getMessage();
		}
		return "";
	}

	public void storeUserName(ValueChangeEvent e){
		userName = (String) e.getNewValue();
		EmployeeManager manager = new EmployeeManager();
		try {
			toList = manager.getTO(userName);
			for (int j = 0; j < toList.size(); j++) {
				employeeTO.setEmployeeName(toList.get(j).getEmployeeName());
				employeeTO.setDateOfJoining(toList.get(j).getDateOfJoining());
				employeeTO.setQualification(toList.get(j).getQualification());
				employeeTO.setAddress(toList.get(j).getAddress());
				employeeTO.setPhoneNo(toList.get(j).getPhoneNo());
				employeeTO.setDesignation(toList.get(j).getDesignation());
				employeeTO.setConsultationFees(toList.get(j).getConsultationFees());
				employeeTO.setDepartment(toList.get(j).getDepartment());
				employeeTO.setSlot1(toList.get(j).getSlot1());
				employeeTO.setSlot2(toList.get(j).getSlot2());
				employeeTO.setSlot3(toList.get(j).getSlot3());
				employeeTO.setUserName(userName);
				employeeTO.setEmployeeNo(toList.get(j).getEmployeeNo());
			}

		} catch (Exception e1) {
			ErrorLogger.logError(this.getClass().getName(), "storeUserName", e1.getMessage());
			this.message = e1.getMessage();
		}
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public EmployeeTO getEmployeeTO() {
		return employeeTO;
	}
	public void setEmployeeTO(EmployeeTO employeeTO) {
		this.employeeTO = employeeTO;
	}
	public List<SelectItem> getNamesList() {
		return namesList;
	}
	public void setNamesList(List<SelectItem> namesList) {
		this.namesList = namesList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<EmployeeTO> getToList() {
		return toList;
	}
	public void setToList(List<EmployeeTO> toList) {
		this.toList = toList;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public List<SelectItem> getDocList() {
		return docList;
	}

	public void setDocList(List<SelectItem> docList) {
		this.docList = docList;
	}
	
}