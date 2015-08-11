package infy.com.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="sshm_ot")
public class OTEntity {
	@Id
	@SequenceGenerator(name="sshm_ot",sequenceName="sshm_ot",initialValue=1000,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sshm_ot")
	private Integer otNo;
	@Column(name="otprice")
	private double price;
	public Integer getOtNo() {
		return otNo;
	}
	public void setOtNo(Integer otNo) {
		this.otNo = otNo;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
