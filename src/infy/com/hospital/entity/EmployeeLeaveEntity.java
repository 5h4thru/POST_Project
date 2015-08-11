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
@Table(name="sshm_EmployeeLeave")
public class EmployeeLeaveEntity {
	@Id
	@SequenceGenerator(name="sshm_leaveid",sequenceName="sshm_leaveid",initialValue=500,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sshm_leaveid")
	private String leaveId;
	private String userName;
	@Temporal(TemporalType.DATE)
	private Date toDate;
	@Temporal(TemporalType.DATE)
	private Date fromDate;
	private String leaveType;
	private String phoneNo;
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
