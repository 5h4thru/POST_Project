package infy.com.hospital.managertest;

import static org.junit.Assert.assertTrue;
import infy.com.hospital.manager.OPDRegistrationManager;
import infy.com.hospital.to.BillingTO;
import infy.com.hospital.to.OpdAppointmentTO;

import java.util.Date;

import org.junit.Test;

public class OPDRegistrationManagerTest {

	@Test
	public void testGetDoctors1() throws Exception {
		assertTrue("Doctor details failed", new OPDRegistrationManager().getDoctors1()!=null);
	}

	@Test
	public void testGetSlots() throws Exception {
		assertTrue("Slot details failed", new OPDRegistrationManager().getSlots("DC101")!=null);
	}
	@Test
	public void testcheckPatientId() throws Exception {
		OPDRegistrationManager op=new OPDRegistrationManager();
		assertTrue("checkPatientId",op.checkPatientId("PA101")!=false);

	}
	@Test
	public void testcheckPatientIdInvalid() throws Exception {
		OPDRegistrationManager op=new OPDRegistrationManager();
		assertTrue("checkPatientId",op.checkPatientId("Pere")!=true);

	}
	@Test
	public void testRegisterOpd() throws Exception {
		OpdAppointmentTO op=new OpdAppointmentTO();
		Date d=new Date();
		op.setDateOfAdmission(d);
		op.setDoctorId("DC101");
		op.setPatientId("PA101");
		op.setReasonForAppointment("Fever");
		op.setSlot("S2");
		op.setStatus("C");
		
		OPDRegistrationManager opd=new OPDRegistrationManager();
		assertTrue("Registration Failed",opd.registerOPD(op)!=null);
	}

	@Test
	public void testAddBillDetails() throws Exception {
		BillingTO bto=new BillingTO();
		bto.setAmount(400);
		Date d=new Date();
		bto.setBillingDate(d);
		bto.setDescription("Doctor Fees");
		bto.setRegistrationNo("1001");
		
		OPDRegistrationManager op=new OPDRegistrationManager();
		assertTrue("Billing failed",op.addBillDetails(bto)!=null);
	}

}
