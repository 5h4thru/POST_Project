package infy.com.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="sshm_profilechangerequest")
public class ProfileChangeRequestEntity {
	@Id
    @SequenceGenerator(name="seq_preqNo",sequenceName="sshm_preqNo",initialValue=30000,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_preqNo")
    private String reqNo;
    private String employeeName;
    private String address;
    private Long phoneNo;
    private String qualification;
    private int consultationFees;
    private String doctorId;
    private char requestStatus;
    private String department;
    
    public String getReqNo() {
          return reqNo;
    }
    public void setReqNo(String reqNo) {
          this.reqNo = reqNo;
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
    public String getQualification() {
          return qualification;
    }
    public void setQualification(String qualification) {
          this.qualification = qualification;
    }
    public int getConsultationFees() {
          return consultationFees;
    }
    public void setConsultationFees(int consultationFees) {
          this.consultationFees = consultationFees;
    }
    public String getDoctorId() {
          return doctorId;
    }
    public void setDoctorId(String doctorId) {
          this.doctorId = doctorId;
    }
    public char getRequestStatus() {
          return requestStatus;
    }
    public void setRequestStatus(char requestStatus) {
          this.requestStatus = requestStatus;
    }
    public String getDepartment() {
          return department;
    }
    public void setDepartment(String department) {
          this.department = department;
    }

}
