package infy.com.hospital.to;

import java.util.Date;

public class SurgeryScheduleTO 
{

	private String doctorId;
	private String patientId;
	private Date dateOfSurgery;
	private String slot;
	private String surgeryName;
	private int otNo;
	private char bookingStatus;
	
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
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
	public char getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(char bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	
	
	
}
