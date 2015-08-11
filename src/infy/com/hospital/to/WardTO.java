package infy.com.hospital.to;

public class WardTO {
	private String wardNo;
	private Integer noOfBeds;
	private String wType;
	private String wStatus;
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
