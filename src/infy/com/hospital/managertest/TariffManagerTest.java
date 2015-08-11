package infy.com.hospital.managertest;

import static org.junit.Assert.assertTrue;
import infy.com.hospital.manager.TariffManager;

import org.junit.Before;
import org.junit.Test;

public class TariffManagerTest {
	TariffManager tariffManager;
	@Before
    public void setUp() throws Exception{
         tariffManager = new TariffManager();
    }
    @Test
    public void testGetDoctors() throws Exception {
         assertTrue("list is empty",tariffManager.getDoctors().size()!=0);
                    
    }

    @Test
    public void testGetWards() throws Exception  {
         assertTrue("list is empty",tariffManager.getWards().size()!=0);
    }

    @Test
    public void testGetOts() throws Exception  {
    	assertTrue("list is empty", tariffManager.getOts().size()!=0);
    }

    @Test
    public void testDocCost() throws Exception
    {
     assertTrue("Incorrect consultation fee", tariffManager.docCost("DC101")==400);
    }

    @Test
    public void testWardCost() throws Exception  {
       assertTrue("Incorrect ward cost", tariffManager.wardCost(8001)==100);
    }

    @Test
    public void testOtCost() throws Exception  {
    	assertTrue("Incorrect OT cost", tariffManager.otCost(1)==330);
    }

}
