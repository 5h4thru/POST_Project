package infy.com.hospital.managertest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import infy.com.hospital.manager.WardManager;
import infy.com.hospital.to.WardTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.junit.Test;

public class WardManagerTest {

	@Test
	public  void testAddWard() throws Exception
	{
		WardTO wto=new WardTO();
		WardManager wm=new WardManager();
		wto.setNoOfBeds(12);
		wto.setWPrice(200.0);
		wto.setWStatus("FR");
		wto.setWType("GEN");
		String wardno=wm.addWard(wto);
		assertTrue("Invalid",wardno!=null);	
	}

	@Test(expected=Exception.class)
	public  void failtestAddWard() throws Exception
	{
		WardTO wto=new WardTO();
		WardManager wm=new WardManager();
		String wardno=wm.addWard(wto);
		assertTrue("Valid",wardno==null);	
	}

	@Test
	public  void testGetids() throws Exception {
		List<SelectItem> list1=new ArrayList<SelectItem>();
		WardManager wm=new WardManager();
		list1=wm.getids();
		assertTrue("false",list1.size()!=0);	
	}

	@Test
	public void failtestGetids() throws Exception {
		List<SelectItem> list1=new ArrayList<SelectItem>();
		WardManager wm=new WardManager();
		list1=wm.getids();
		assertFalse("false",list1.size()==0);	
	}
	@Test
	public void testDelWard() throws Exception {
		WardManager wm=new WardManager();
		String wardNumber="2026";
		String wardno=wm.delWard(wardNumber);
		assertTrue("Valid",wardno!=null);	
	}
	
	@Test(expected=Exception.class)
	public void failtestDelWard() throws Exception {
		WardManager wm=new WardManager();
		String wardNumber="802";
		String wardno=wm.delWard(wardNumber);
		assertTrue("Valid",wardno==null);	
	}
	
	@Test
	public void testGetTO() throws Exception {
		List<WardTO> list1=new ArrayList<WardTO>();
		WardManager wm=new WardManager();
		String wardNo="8001";
		list1=wm.getTO(wardNo);
		assertTrue("false",list1.size()!=0);	
	}

	@Test(expected=Exception.class)
	public void failtestGetTO() throws Exception {
		List<WardTO> list1=new ArrayList<WardTO>();
		WardManager wm=new WardManager();
		String wardNo="jhhj";
		wm.getTO(wardNo);
	}

	@Test
	public void testUpdateWard() throws Exception {
		WardTO wto=new WardTO();
		WardManager wm=new WardManager();
		wto.setNoOfBeds(12);
		wto.setWPrice(200.0);
		wto.setWStatus("FR");
		wto.setWType("GEN");
		String wardno=wm.updateWard(wto);
	}

	@Test(expected=Exception.class)
	public void failtestUpdateWard() throws Exception {

		WardTO wto=new WardTO();
		WardManager wm=new WardManager();
		wto.setNoOfBeds(-12);
		wto.setWPrice(-200.0);
		wto.setWStatus("R");
		wto.setWType("EN");
		String wardno=wm.updateWard(wto);
		assertTrue("Invalid",wardno!=null);	
	}
}
