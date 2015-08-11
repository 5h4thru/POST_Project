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
@Table(name="sshm_biomedicalequipment")
public class BiomedicalEquipmentEntity {
	@Id
	@SequenceGenerator(name="sshm_equipmentNo",sequenceName="sshm_equipmentNo",initialValue=3000,allocationSize=1)
	@GeneratedValue(generator="sshm_equipmentNo",strategy=GenerationType.SEQUENCE)
	private String equipmentNo;
	private String equipmentName;
	@Temporal(TemporalType.DATE)
	private Date dateOfPurchase;
	private Integer cost;
	private Integer quantity;
	public String getEquipmentNo() {
		return equipmentNo;
	}
	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}	
