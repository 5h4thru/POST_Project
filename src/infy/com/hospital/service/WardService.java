package infy.com.hospital.service;

import infy.com.hospital.entity.WardEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.WardTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;




public class WardService{


	public String addWard(WardTO wardTO)throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Post_project");	
			em=emf.createEntityManager();			
			EntityTransaction et=em.getTransaction();
			WardEntity we=new WardEntity();
			we.setWType(wardTO.getWType());
			we.setNoOfBeds(wardTO.getNoOfBeds());
			we.setWPrice(wardTO.getWPrice());
			we.setWStatus(wardTO.getWStatus());
			et.begin();	
			em.persist(we);
			et.commit();
			String wardno=we.getWardNo();
			return wardno;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"addWard",e.getMessage());
			throw e;
		}
		finally
		{
			em.close();
		}


	}
	public List<SelectItem> getids() throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Post_project");	
			em=emf.createEntityManager();			
			EntityTransaction et=em.getTransaction();
			et.begin();		
			Query query1=em.createQuery("select p from WardEntity p");
			List l=query1.getResultList();
			et.commit();
			List<SelectItem> list=new ArrayList<SelectItem>();
			for (int i=0;i<l.size();i++) 
			{
				WardEntity we=(WardEntity) l.get(i);
				list.add(new SelectItem(we.getWardNo(),we.getWardNo()));				
			}

			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getids",e.getMessage());
			throw e;
		}
		finally{
			em.close();
		}

	}

	public String delWard(String wardNumber)throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			et.begin();
			WardEntity we=em.find(WardEntity.class,wardNumber);
			if(we.getWStatus().equalsIgnoreCase("OC"))
			{				
				return "1";			
			}
			em.remove(we);
			et.commit();
			return wardNumber;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"delWard",e.getMessage());
			throw e;
		}
		finally{
			em.close();
		}

	}

	public List<WardTO> getTO(String wardNo)throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			et.begin();
			WardEntity we=em.find(WardEntity.class,wardNo);
			List<WardTO> list=new ArrayList<WardTO>();
			WardTO wto=new WardTO();
			if(we!=null)
			{
				wto.setWardNo(we.getWardNo());
				wto.setNoOfBeds(we.getNoOfBeds());
				wto.setWPrice(we.getWPrice());
				wto.setWStatus(we.getWStatus());
				wto.setWType(we.getWType());
				list.add(wto);
			}
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getTO",e.getMessage());
			throw e;
		}
		finally{
			em.close();
		}

	}

	public String updateWard(WardTO wardTO)throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Post_project");	
			em=emf.createEntityManager();			
			EntityTransaction et=em.getTransaction();
			WardEntity we=new WardEntity();
			we.setWardNo(wardTO.getWardNo());
			we.setWType(wardTO.getWType());
			we.setWStatus(wardTO.getWStatus());
			we.setNoOfBeds(wardTO.getNoOfBeds());
			we.setWPrice(wardTO.getWPrice());
			et.begin();	
			em.merge(we);	
			String wardno=we.getWardNo();
			et.commit();
			return wardno;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"updateWard",e.getMessage());
			throw e;	
		}
		finally{
			em.close();
		}
	}
}
