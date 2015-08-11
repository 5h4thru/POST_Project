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
@Table(name="sshm_drugrequest")
public class DrugRequestEntity {
	@Id
	@GeneratedValue(generator="drugreqno",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="drugreqno",sequenceName="sshm_drugReqNo",initialValue=10000,allocationSize=1)
	private String drugReqNo;
	private String doctorId;
	private String drugName;
	private int quantity;
	@Temporal(TemporalType.DATE)
	private Date requestDate;
	private char reqStatus;
	private String company;
	private String requestType;
	
	
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
	
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public char getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(char reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}


}
