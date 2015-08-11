package infy.com.hospital.manager;

import infy.com.hospital.exception.InvalidUserException;
import infy.com.hospital.exception.WrongPasswordException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.LoginService;
import infy.com.hospital.to.LoginTO;

public class LoginManager {
	
	public LoginTO validateLogin(LoginTO loginTO) throws InvalidUserException,WrongPasswordException,Exception
	{
		try
		{
			//String s1=loginTO.getPassword();
		LoginService ser=new LoginService();
		LoginTO to=ser.validateLogin(loginTO);
		//String s2=to.getPassword();
		if(to==null)
		{
			throw new InvalidUserException();
		}
		if(!to.getPassword().equals(loginTO.getPassword()))
		{
			throw new WrongPasswordException();
		}
		return to;
		}
		
		catch(InvalidUserException iue)
		{
			ErrorLogger.logError(this.getClass().getName(),"validateLogin",iue.getMessage());
			throw iue;
		}
		
		catch(WrongPasswordException wpe)
		{
			ErrorLogger.logError(this.getClass().getName(),"validateLogin",wpe.getMessage());
			throw wpe;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"validateLogin",e.getMessage());
			throw e;
		}
	}
	
	public Boolean updateLogin(LoginTO loginTO) throws Exception
	{
		try
		{
		LoginService ls=new LoginService();
		return ls.updateLogin(loginTO);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"updateLogin",e.getMessage());
			throw e;		
		}
	}
	
	public void updateLoginDate(String userName) throws Exception
	{
		try
		{
			LoginService ser=new LoginService();
			ser.updateLoginDate(userName);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"updateLoginDate",e.getMessage());
			throw e;
		}
	}
	

	public LoginTO getLoginDetails(String userName) throws Exception
	{
		try
		{
		LoginService ls=new LoginService();
		return ls.getLoginDetails(userName);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getLoginDetails",e.getMessage());
			throw e;
		}
		
	}

}
