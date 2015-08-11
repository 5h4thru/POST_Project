package infy.com.hospital.service;

import infy.com.hospital.entity.LoginEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.LoginTO;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LoginService {
	EntityManagerFactory emf= null;
	EntityManager em = null;
	EntityTransaction et=null; 
	
	public LoginTO validateLogin(LoginTO loginTO) throws Exception
	{
		try
		{
			
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			et=em.getTransaction();
			et.begin();
			LoginEntity le=em.find(LoginEntity.class,loginTO.getUserName());			
			et.commit();
			if(le!=null)
			{
				LoginTO lto=new LoginTO();
				lto.setLastLoginDate(le.getLastLoginDate());
				lto.setPassword(le.getPassword());
				lto.setRole(le.getRole());
				lto.setUserName(le.getUserName());
				return lto;
			}
			else
			{
				return null;
			}
			
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"validateLogin",e.getMessage());
			throw e;
		}
		finally
		{
			if(et!=null)
			{
				em.close();
			}
		}
		
	}
	
	public Boolean updateLogin(LoginTO loginTO) throws Exception
	{
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			et=em.getTransaction();
			et.begin();
			LoginEntity le=em.find(LoginEntity.class,loginTO.getUserName());
			le.setPassword(loginTO.getPassword());
			le.setLastLoginDate(loginTO.getLastLoginDate());
			em.merge(le);
			et.commit();
			return true;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"updateLogin",e.getMessage());
			throw e;
		}
		finally
		{
			if(et!=null)
			{
				em.close();
			}
		}
	}
	
	public void updateLoginDate(String userName) throws Exception
	{
		try
		{
			
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			et=em.getTransaction();
			et.begin();
			Date d=new Date();
			Query q=em.createQuery("update LoginEntity l set l.lastLoginDate=?1 where l.userName=?2");
			q.setParameter(1, d);
			q.setParameter(2, userName);
			q.executeUpdate();
			et.commit();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"updateLoginDate",e.getMessage());
			throw e;
		}
		finally
		{
			if(et!=null)
			{
				em.close();
			}
		}
		
	}
	
	public LoginTO getLoginDetails(String userName) throws Exception
	{
		try
		{
			
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			et=em.getTransaction();
			et.begin();
			LoginEntity le=em.find(LoginEntity.class, userName);
			LoginTO lto=new LoginTO();
			lto.setPassword(le.getPassword());
			return lto;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getLoginDetails",e.getMessage());
			throw e;
		}
		finally
		{
			if(et!=null)
			{
				em.close();
			}
		}
	}
//	public Boolean patientIdAvailability(String patientId)
//	{
//		
//	}*/

}
