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
@Table(name="sshm_drug")
public class DrugEntity 

{
	@Id
	@SequenceGenerator(name="sshm_drugNo",sequenceName="sshm_drugNo")
	@GeneratedValue(generator="sshm_drugNo",strategy=GenerationType.SEQUENCE)
	private String drugNo;
	private String drugName;
	@Temporal(TemporalType.DATE)
	private Date dateOfExpiry;
	private Integer costPerUnit;
	private Integer quantity;
	private String company;
	
	
	public String getDrugNo() {
		return drugNo;
	}
	public void setDrugNo(String drugNo) {
		this.drugNo = drugNo;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}
	
	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public Integer getCostPerUnit() {
		return costPerUnit;
	}
	public void setCostPerUnit(Integer costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	
	
}
