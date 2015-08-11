package infy.com.hospital.managedbean;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.LoginManager;
import infy.com.hospital.to.LoginTO;

import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
public class PasswordRecoveryBean 

{

	private String userName;
	private String password;
	private String newPassword;
	private boolean check;
	private Character accountStatus;
	private String role;
	private String confirmPassword;
	private boolean visibleConfirm;
	private String message;
	private String pass;
	
	public String updatePassword()
	{
		try
		{
		if(this.confirmPassword.equals(this.newPassword))
		{
			LoginTO lto=new LoginTO();
			FacesContext cxt=FacesContext.getCurrentInstance();
			ExternalContext ext=cxt.getExternalContext();
			HttpSession s=(HttpSession)ext.getSession(false);
			this.userName=(String)s.getAttribute("uname");
			lto.setUserName(this.userName);
			lto.setPassword(this.newPassword);
			lto.setLastLoginDate(new Date());
			LoginManager lm=new LoginManager();
			lm.updateLogin(lto);
			this.message="Your Password is changed successfully";
			return "success";
		}
		else
		{
			this.message="New Password and Re enter Password do not match";
			return "fail";
		}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"updatePassword",e.getMessage());
			this.message=e.getMessage();
			return "fail";
		}
		
	}
	
	public void showConfirmPassword(ValueChangeEvent e)
	{
		try
		{
		if(e.getNewValue()==null)
		{
			this.message="";
			this.visibleConfirm=false;
		}
		else
		{
			if(this.password.equals(e.getNewValue()))
			{
				this.message="Old and New passwords should not be same";
				this.visibleConfirm=false;
			}
			else
			{
				this.message="";
			this.visibleConfirm=true;
			}
		}
		}
		catch(Exception e1)
		{
			ErrorLogger.logError(this.getClass().getName(),"showConfirmPassword",e1.getMessage());
			this.message=e1.getMessage();
		}
	}
	
	public String validateLogin()
	{
		try
		{
			FacesContext cxt=FacesContext.getCurrentInstance();
			ExternalContext ext=cxt.getExternalContext();
			HttpSession s=(HttpSession)ext.getSession(true);
			s.setAttribute("uname", this.userName);
			
		LoginTO lto=new LoginTO();
		lto.setUserName(this.userName);
		lto.setPassword(this.password);
		LoginManager lm=new LoginManager();
		lto=lm.validateLogin(lto);
		this.check=true;
		this.message="";
		return "success";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"validateLogin",e.getMessage());
			this.check=false;
			this.message=e.getMessage();
			return "fail";
		}
	}

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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Character getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Character accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isVisibleConfirm() {
		return visibleConfirm;
	}

	public void setVisibleConfirm(boolean visibleConfirm) {
		this.visibleConfirm = visibleConfirm;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
