package infy.com.hospital.managertest;

import infy.com.hospital.manager.ViewScheduleManager;

import org.junit.Test;


public class ViewScheduleManagerTest {

	@Test
	public void getNames() throws Exception{
		ViewScheduleManager manager = new ViewScheduleManager();
		manager.getNames();
	}
	
//	@Test(expected=NoEmployees.class)
//	public void failGetNames() throws Exception{
//		ViewScheduleManager manager = new ViewScheduleManager();
//		manager.getNames();
//	}
	
	@Test
	public void getSpecialization() throws Exception{
		ViewScheduleManager manager = new ViewScheduleManager();
		manager.getSpecialization();
	}
	
//	@Test(expected=NoEmployees.class)
//	public void failGetSpecialization() throws Exception{
//		ViewScheduleManager manager = new ViewScheduleManager();
//		manager.getSpecialization();
//	}
	
	@Test
	public void getDoctorSchedule() throws Exception{
		ViewScheduleManager manager = new ViewScheduleManager();
		manager.getDoctorSchedule("Rahul");
	}
	
	@Test
	public void getSpecialSchedule() throws Exception{
		ViewScheduleManager manager = new ViewScheduleManager();
		manager.getSpecialSchedule("oncology");
	}
}