package infy.com.hospital.to;

public class DoctorTO {
	private String userName;
	private String department;
	private int consultationFees;
	private String slot1;
	private String slot2;
	private String slot3;
	private char updateStatus;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getConsultationFees() {
		return consultationFees;
	}
	public void setConsultationFees(int consultationFees) {
		this.consultationFees = consultationFees;
	}
	public String getSlot1() {
		return slot1;
	}
	public void setSlot1(String slot1) {
		this.slot1 = slot1;
	}
	public String getSlot2() {
		return slot2;
	}
	public void setSlot2(String slot2) {
		this.slot2 = slot2;
	}
	public String getSlot3() {
		return slot3;
	}
	public void setSlot3(String slot3) {
		this.slot3 = slot3;
	}
	public char getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(char updateStatus) {
		this.updateStatus = updateStatus;
	}
	
	

}
