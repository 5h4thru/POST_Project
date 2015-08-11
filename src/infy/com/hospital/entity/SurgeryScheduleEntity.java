package infy.com.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sshm_surgeryschedule")
public class SurgeryScheduleEntity 
{
	
	@Id
	@SequenceGenerator(name="sshm_bid",sequenceName="sshm_bid",initialValue=120,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sshm_bid")
	private String bookingId;
	private String doctorId;
	private String patientId;
	@Temporal(TemporalType.DATE)
	private Date dateOfSurgery;
	private String slot;
	private String surgeryName;
	private int otNo;
	private char bookingStatus;
	
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
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
