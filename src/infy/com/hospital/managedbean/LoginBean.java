package infy.com.hospital.managedbean;


import infy.com.hospital.exception.InvalidUserException;
import infy.com.hospital.exception.WrongPasswordException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.LoginManager;
import infy.com.hospital.to.LoginTO;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class LoginBean {
	private String userName;
	private String password;
	private String message;
	private String role;
	private String confirmedPassword;
	private boolean visibleConfirm;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
	public boolean isVisibleConfirm() {
		return visibleConfirm;
	}
	public void setVisibleConfirm(boolean visibleConfirm) {
		this.visibleConfirm = visibleConfirm;
	}
	
	public String validateLogin()
	{
		try
		{
		LoginTO to=new LoginTO();
		to.setUserName(this.userName);
		to.setPassword(this.password);
		LoginManager manager=new LoginManager();
		LoginTO to1=manager.validateLogin(to);
		
		FacesContext c=FacesContext.getCurrentInstance();
		HttpSession session=(HttpSession)c.getExternalContext().getSession(true);
		session.setAttribute("uname", to1.getUserName());
		session.setAttribute("srole", to1.getRole());
		this.message="";
		if(to1.getLastLoginDate()==null)
		{
			return "changepassword";
		}
		manager.updateLoginDate(to1.getUserName());
		return "success";
		}
		
		catch(InvalidUserException iue)
		{
			ErrorLogger.logError(this.getClass().getName(),"validateLogin",iue.getMessage());
			this.message=iue.getMessage();
			return "fail";
		}
		
		catch(WrongPasswordException wpe)
		{
			ErrorLogger.logError(this.getClass().getName(),"validateLogin",wpe.getMessage());
			this.message=wpe.getMessage();
			return "fail";
		}
		
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"validateLogin",e.getMessage());
			this.message=e.getMessage();
			return "fail";
		}
	}
	
	/*public void showConfirmPassword(ValueChangeEvent event)
	{
		
	}
	*/
	
	public String updateLogin()
	{
		try
		{
			this.message="";
			FacesContext cxt=FacesContext.getCurrentInstance();
			ExternalContext ext=cxt.getExternalContext();
			HttpSession s=(HttpSession)ext.getSession(false);
			this.userName=(String) s.getAttribute("uname");
			this.role=(String) s.getAttribute("srole");
			LoginManager lm=new LoginManager();
			LoginTO lto1=new LoginTO();
			lto1=lm.getLoginDetails(this.userName);
			if(lto1.getPassword().equals(this.password))
			{
				this.message="Old Password and New Password should not be same";
				return "fail";
			}
			else
			{
		if(this.confirmedPassword.equals(this.password))
		{
			LoginTO lto=new LoginTO();
			lto.setUserName(this.userName);
			lto.setPassword(this.confirmedPassword);
			lto.setRole(this.role);
			
			if(lm.updateLogin(lto))
			{
				lm.updateLoginDate(this.userName);
				this.message="Your Password is changed successfully";
				//s.invalidate();	
			}
			return "success";
		}
		else
		{
			this.message="New Password and Re enter Password do not match";
			return "fail";
		}
		}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"updateLogin",e.getMessage());
			this.message=e.getMessage();
			return "fail";
		}
	}
	public String logOut()
	{
		FacesContext c=FacesContext.getCurrentInstance();
		HttpSession session=(HttpSession)c.getExternalContext().getSession(true);
		session.invalidate();
		return "logout";
		
	}

}
