package infy.com.hospital.managertest;

import static org.junit.Assert.assertTrue;
import infy.com.hospital.exception.InvalidUserException;
import infy.com.hospital.exception.WrongPasswordException;
import infy.com.hospital.manager.LoginManager;
import infy.com.hospital.to.LoginTO;

import org.junit.Test;

public class LoginManagerTest {

	@Test
	public void testValidateLogin() throws InvalidUserException, WrongPasswordException, Exception {
		LoginTO to=new LoginTO();
		to.setUserName("DC109");
		to.setPassword("DC@125");
		LoginManager m=new LoginManager();
		assertTrue("Login failed", m.validateLogin(to)!=null);
	}
	
	@Test(expected=InvalidUserException.class)
	public void testValidateLoginUserName() throws InvalidUserException,WrongPasswordException, Exception {
		LoginTO lto=new LoginTO();
		lto.setUserName("DC101232");
		lto.setPassword("DC@123");
		
		LoginManager lm=new LoginManager();
		lm.validateLogin(lto);
		//assertTrue("validate failed",lm.validateLogin(lto)!=null);
		}

	@Test(expected=WrongPasswordException.class)
	public void testValidateLoginPassword() throws InvalidUserException,WrongPasswordException, Exception {
		LoginTO lto=new LoginTO();
		lto.setUserName("DC101");
		lto.setPassword("fghdg");
		
		LoginManager lm=new LoginManager();
		lm.validateLogin(lto);
		//assertTrue("validate failed",lm.validateLogin(lto)!=null);
		}
	@Test
	public void testUpdateLogin() throws Exception 
	{
		LoginManager lm=new LoginManager();
		LoginTO lto=new LoginTO();
		lto.setUserName("DC101");
		lto.setPassword("DA@123");
		lto.setRole("DA");
		assertTrue(lm.updateLogin(lto));
		//fail("Not yet implemented");
	}
	
	@Test(expected=NullPointerException.class)
	public void testInvalidUserNameUpdateLogin() throws Exception 
	{
		LoginManager lm=new LoginManager();
		LoginTO lto=new LoginTO();
		lto.setUserName("DA101");
		lto.setPassword("DA@123");
		lto.setRole("DA");
		assertTrue(lm.updateLogin(lto));
		//fail("Not yet implemented");
	}


	@Test
	public void testUpdateLoginDate() throws Exception 
	{
		LoginManager lm=new LoginManager();
		lm.updateLoginDate("AD101");
		//fail("Not yet implemented");
	}

	
	@Test
	public void testGetLoginDetails() throws Exception 
	{
		LoginManager lm=new LoginManager();
		assertTrue(lm.getLoginDetails("DC101")!=null);
		//fail("Not yet implemented");
	}
	
	@Test(expected=NullPointerException.class)
	public void testInvalidGetLoginDetails() throws Exception 
	{
		LoginManager lm=new LoginManager();
		lm.getLoginDetails("DA101");
		//fail("Not yet implemented");
	}

	/*@Test
	public void testUpdateLoginDate() {
		fail("Not yet implemented");
	}*/

}
