package infy.com.hospital.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="sshm_opd")
public class OpdAppointmentEntity {
	@Id
	@SequenceGenerator(name="sshm_rrgNo",sequenceName="sshm_rrgNo",initialValue=7020,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sshm_rrgNo")
	private String registrationNo;
	private String patientId;
	private String doctorId;
	@Temporal(TemporalType.DATE)
	@Column(name="dateofRegistration")
	private Date dateOfAdmission;
	@Column(name="reasonForAdmission")
	private String reasonForAppointment;
	private String slot;
	private String status;
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	public String getReasonForAppointment() {
		return reasonForAppointment;
	}
	public void setReasonForAppointment(String reasonForAppointment) {
		this.reasonForAppointment = reasonForAppointment;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
