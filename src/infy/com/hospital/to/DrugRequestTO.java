package infy.com.hospital.to;

import java.util.Date;

public class DrugRequestTO 


{

	private String drugReqNo;
	private String doctorId;
	private String drugName;
	private int quantity;
	private Date date;
	private char reqStatus;
	private String urgent;
	private String manufacturer;
	private Date dateOfExpiry;
	private Integer costPerUnit;
	private boolean selected;
	private String patientType;
	private String patientId;
	private String registrationNo;
	private double amount;
	
	public String getDrugReqNo() {
		return drugReqNo;
	}
	public void setDrugReqNo(String drugReqNo) {
		this.drugReqNo = drugReqNo;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public char getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(char reqStatus) {
		this.reqStatus = reqStatus;
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
	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public Integer getCostPerUnit() {
		return costPerUnit;
	}
	public void setCostPerUnit(Integer costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
}
