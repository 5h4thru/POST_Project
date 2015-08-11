package infy.com.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="sshm_ward")
public class WardEntity {
	@Id
	@Column(length=5)
	@GeneratedValue(generator="wardno",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="wardno",sequenceName="sshm_wardNo",initialValue=2000,allocationSize=1)
	private String wardNo;
	@Column(length=5)
	private Integer noOfBeds;
	@Column(length=3)
	private String wType;
	@Column(length=2)
	private String wStatus;
	@Column(length=10)
	private Double wPrice;
	public String getWardNo() {
		return wardNo;
	}
	public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}
	public Integer getNoOfBeds() {
		return noOfBeds;
	}
	public void setNoOfBeds(Integer noOfBeds) {
		this.noOfBeds = noOfBeds;
	}
	public String getWType() {
		return wType;
	}
	public void setWType(String type) {
		wType = type;
	}
	public String getWStatus() {
		return wStatus;
	}
	public void setWStatus(String status) {
		wStatus = status;
	}
	public Double getWPrice() {
		return wPrice;
	}
	public void setWPrice(Double price) {
		wPrice = price;
	}
	
	

}
