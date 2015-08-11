package infy.com.hospital.to;

public class AppointmentTO {
	private String doctorId;
	private String patientId;
	private String reasonOfAppointment;
	private String slot;
	
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getReasonOfAppointment() {
		return reasonOfAppointment;
	}
	public void setReasonOfAppointment(String reasonOfAppointment) {
		this.reasonOfAppointment = reasonOfAppointment;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
}
