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
@Table(name="sshm_ipd")
public class IpdAppointmentEntity {
	@Id
	@Column(length=15)
	@GeneratedValue(generator="ipdregno",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="ipdregno",sequenceName="sshm_ipdregNo",initialValue=10000,allocationSize=1)
	private String registrationNo;	
	@Column(length=15)
	private String doctorId;
	@Column(length=5)
	private int bedNo;
	@Column(length=5)
	private String wardNo;
	@Temporal(TemporalType.DATE)
	private Date dateOfAdmission;
	@Column(length=80)
	private String reasonForAdmission;
	@Column(length=20)
	private String department;
	@Column(length=2)
	private String admissionStatus;
	@Column(length=15)
	private String username;
	@Temporal(TemporalType.DATE)
	private Date dateOfDischarge;
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public int getBedNo() {
		return bedNo;
	}
	public void setBedNo(int bedNo) {
		this.bedNo = bedNo;
	}
	public String getWardNo() {
		return wardNo;
	}
	public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}
	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	public String getReasonForAdmission() {
		return reasonForAdmission;
	}
	public void setReasonForAdmission(String reasonForAdmission) {
		this.reasonForAdmission = reasonForAdmission;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAdmissionStatus() {
		return admissionStatus;
	}
	public void setAdmissionStatus(String admissionStatus) {
		this.admissionStatus = admissionStatus;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDateOfDischarge() {
		return dateOfDischarge;
	}
	public void setDateOfDischarge(Date dateOfDischarge) {
		this.dateOfDischarge = dateOfDischarge;
	}
	

}
