package infy.com.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="sshm_patient")
public class PatientEntity {
                
                @Id
                @SequenceGenerator(name="sshm_patientNo",sequenceName="sshm_patientNo",initialValue=103,allocationSize=1)
                @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sshm_patientNo")
                private String patientId;
                private String userName;
                private String firstName;
                private String lastName;
                private String address;
                private Long phoneNo;
                private Integer age;
                private String contactPerson;
                private String nationality;
                
                
                public String getPatientId() {
                                return patientId;
                }
                public void setPatientId(String patientId) {
                                this.patientId = patientId;
                }
                public String getUserName() {
                                return userName;
                }
                public void setUserName(String userName) {
                                this.userName = userName;
                }
                public String getFirstName() {
                                return firstName;
                }
                public void setFirstName(String firstName) {
                                this.firstName = firstName;
                }
                public String getLastName() {
                                return lastName;
                }
                public void setLastName(String lastName) {
                                this.lastName = lastName;
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
                public Integer getAge() {
                                return age;
                }
                public void setAge(Integer age) {
                                this.age = age;
                }
                
                public String getContactPerson() {
					return contactPerson;
				}
				public void setContactPerson(String contactPerson) {
					this.contactPerson = contactPerson;
				}
				
				public String getNationality() {
                                return nationality;
                }
                public void setNationality(String nationality) {
                                this.nationality = nationality;
                }
}

