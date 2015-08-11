package infy.com.hospital.to;

import java.util.Date;

public class DrugTO 
{
	private String drugNo;
	private String drugName;
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
