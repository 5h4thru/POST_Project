package infy.com.hospital.managertest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import infy.com.hospital.exception.AmountRequiredException;
import infy.com.hospital.exception.CannotBeDischargedException;
import infy.com.hospital.exception.NoRegistrationNoFoundException;
import infy.com.hospital.exception.PatientIDPatternException;
import infy.com.hospital.exception.PatientIdNotPresentException;
import infy.com.hospital.manager.BillingManager;
import infy.com.hospital.to.BillingTO;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BillingManagerTest {
	BillingManager billingManager=null;
    @Before
    public void setUp() throws Exception{
          billingManager = new BillingManager();
    }

    @Test
    public void testPayIPD5() throws Exception{
    	String registrationNo = "6001";
    	String patientId = "PA101";
    	Double amount =100.00;
    	BillingTO bto=billingManager.payIPD(registrationNo, patientId, amount);
    	assertTrue(bto.getBillNo()!=null);

    }
    @Test(expected=AmountRequiredException.class)
	public void testPayIPD() throws Exception{
        String registrationNo = "6001";
        String patientId = "PA101";
        Double amount = null;
        billingManager.payIPD(registrationNo, patientId, amount);

	}
    @Test(expected=NoRegistrationNoFoundException.class)
	public void testPayIPD1() throws Exception{
        String registrationNo = "XXX";
        String patientId = "PA101";
        Double amount = 100.00;
        billingManager.payIPD(registrationNo, patientId, amount);

	}
    
    @Test(expected=PatientIDPatternException.class)
	public void testPayIPD2() throws Exception{
        String registrationNo = "6001";
        String patientId = "101";
        Double amount = 100.00;
        billingManager.payIPD(registrationNo, patientId, amount);

	}
    @Test(expected=PatientIdNotPresentException.class)
	public void testPayIPD4() throws Exception{
        String registrationNo = "6001";
        String patientId = "PA1";
        Double amount = 100.00;
        billingManager.payIPD(registrationNo, patientId, amount);
    }

    @Test(expected=CannotBeDischargedException.class)
	public void testDischarge1() throws Exception{
        String registrationNo = "6001";
        String patientId = "PA101";
        billingManager.discharge(registrationNo, patientId);

	}

    @Test
	public void testGenerateBill() throws Exception{
    	String registrationNo = "6001";
        String patientId = "PA101";
		List<BillingTO> bto=billingManager.generateBill(registrationNo, patientId);
		assertFalse(bto.isEmpty());
	}

}
