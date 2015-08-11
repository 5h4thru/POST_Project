package infy.com.hospital.managertest;

import static org.junit.Assert.assertTrue;
import infy.com.hospital.exception.BusyScheduleException;
import infy.com.hospital.exception.InvalidDoctorIdException;
import infy.com.hospital.exception.MoreNoOfAppointmentsException;
import infy.com.hospital.exception.NoAppointmentException;
import infy.com.hospital.exception.NoPaymentMadeException;
import infy.com.hospital.manager.PatientManager;
import infy.com.hospital.to.OpdAppointmentTO;
import infy.com.hospital.to.PaymentTO;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PatientManagerTest {

	PatientManager manager=null;
	@Before
	public void setUp() throws Exception {
		this.manager=new PatientManager();
	}

	@After
	public void tearDown() throws Exception {
	}
	@SuppressWarnings("deprecation")
	@Test
	public final void testCheckAppointment() throws InvalidDoctorIdException, MoreNoOfAppointmentsException, BusyScheduleException, Exception {
		OpdAppointmentTO opd = new OpdAppointmentTO();
		Date date = new Date("26-Jan-2013");
		opd.setDoctorId("DC102");
		opd.setPatientId("PA101");
		opd.setDateOfAdmission(date);
		opd.setReasonForAppointment("High fever");
		opd.setSlot("S1");
		
		PatientManager manager = new PatientManager();
		manager.checkAppointment(opd);
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected = InvalidDoctorIdException.class)
	public final void testCheckAppointmentInvalidDoctorId() throws InvalidDoctorIdException, MoreNoOfAppointmentsException, BusyScheduleException, Exception {
		OpdAppointmentTO opd = new OpdAppointmentTO();
		Date date = new Date("23-Jan-2013");
		opd.setDoctorId("DC105");
		opd.setPatientId("PA101");
		opd.setDateOfAdmission(date);
		opd.setReasonForAppointment("High fever");
		opd.setSlot("S1");
		
		PatientManager manager = new PatientManager();
		manager.checkAppointment(opd);
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected = MoreNoOfAppointmentsException.class)
	public final void testCheckAppointmentMoreAppointments() throws InvalidDoctorIdException, MoreNoOfAppointmentsException, BusyScheduleException, Exception {
		OpdAppointmentTO opd = new OpdAppointmentTO();
		Date date = new Date("23-Jan-2013");
		opd.setDoctorId("DC102");
		opd.setPatientId("PA101");
		opd.setDateOfAdmission(date);
		opd.setReasonForAppointment("High fever");
		opd.setSlot("S1");
		
		PatientManager manager = new PatientManager();
		manager.checkAppointment(opd);
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected = BusyScheduleException.class)
	public final void testCheckAppointmentBusySchedule() throws InvalidDoctorIdException, MoreNoOfAppointmentsException, BusyScheduleException, Exception {
		OpdAppointmentTO opd = new OpdAppointmentTO();
		Date date = new Date("28-Jan-2013");
		opd.setDoctorId("DC101");
		opd.setPatientId("PA101");
		opd.setDateOfAdmission(date);
		opd.setReasonForAppointment("High fever");
		opd.setSlot("S1");
		
		PatientManager manager = new PatientManager();
		manager.checkAppointment(opd);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public final void testMakePayment() throws Exception {
		PatientManager manager = new PatientManager();
		PaymentTO pt = new PaymentTO();
		Date date = new Date("23-Jan-2013");
		pt.setAmount(400.0);
		pt.setDateOfPayment(date);
		pt.setDoctorId("DC102");
		pt.setPatientId("PA101");
		
		OpdAppointmentTO opd = new OpdAppointmentTO();
		opd.setDoctorId("DC102");
		opd.setPatientId("PA101");
		opd.setDateOfAdmission(date);
		opd.setReasonForAppointment("High fever");
		opd.setSlot("S1");
		
		assertTrue("Pay Number is null", manager.makePayment(pt, opd) != null);
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected = NullPointerException.class)
	public final void testMakePaymentInvalid() throws Exception {
		PatientManager manager = new PatientManager();
		Date date = new Date("23-Jan-2013");
		
		OpdAppointmentTO opd = new OpdAppointmentTO();
		opd.setDoctorId("DC102");
		opd.setPatientId("PA101");
		opd.setDateOfAdmission(date);
		opd.setReasonForAppointment("High fever");
		opd.setSlot("S1");
		
		assertTrue("Pay Number is not null", manager.makePayment(null, opd) == null);
	}

	@Test
	public final void testViewAppointmentMade() throws NoAppointmentException, Exception {
		PatientManager manager = new PatientManager();
		
		assertTrue("Patient has appointments scheduled", manager.viewAppointmentMade("PA101") != null);
	}
	
	@Test(expected = NoAppointmentException.class)
	public final void testViewAppointmentMadeNoAppointment() throws NoAppointmentException, Exception {
		PatientManager manager = new PatientManager();
		
		manager.viewAppointmentMade("PA103");
	}

	@Test
	public final void testCancelAppointment() throws Exception {
		PatientManager manager = new PatientManager();
		manager.cancelAppointment("81");
	}
	@Test
	public void testViewPaymentsValidData() throws Exception  {
		assertTrue("Not a Valid User",manager.viewPayments("PA101").size()!=0);
		
	}
	@Test(expected=NoPaymentMadeException.class)
	public void testViewPaymentsNoPayment() throws Exception  {
		manager.viewPayments("PA104");
		
	}
	
	
	@Test
	public void testFindPatientValidData()throws Exception {
		assertTrue("P",manager.findPatient("PA102")!=null);
	}
}
