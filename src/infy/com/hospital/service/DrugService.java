package infy.com.hospital.service;

import infy.com.hospital.entity.DrugEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.DrugTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DrugService {
	
	public String addDrug(DrugTO drugTO) throws Exception
	{
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
		emf=Persistence.createEntityManagerFactory("Post_project");
		em = emf.createEntityManager();
		DrugEntity drugEntity=new DrugEntity();
		
		em.getTransaction().begin();
		drugEntity.setCompany(drugTO.getCompany());
		drugEntity.setDrugName(drugTO.getDrugName());
		drugEntity.setQuantity(drugTO.getQuantity());
		drugEntity.setCostPerUnit(drugTO.getCostPerUnit());
		drugEntity.setDateOfExpiry(drugTO.getDateOfExpiry());
		em.persist(drugEntity);
		em.getTransaction().commit();
		
		return drugEntity.getDrugNo();
		
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addDrug", e.getMessage());
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
	}
	
	
	
	public List<SelectItem> getIds() throws Exception
	{
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			
			Query query=em.createQuery("select de from DrugEntity de");
			
			List<DrugEntity> entityList=query.getResultList();
			List<SelectItem> idList=new ArrayList<SelectItem>();
			
			for(DrugEntity drugEntity:entityList)
			{
				idList.add(new SelectItem(drugEntity.getDrugNo(),drugEntity.getDrugName()));
			}
			
			return idList;
			
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getIds", e.getMessage());
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
		
	}
	
	public String delDrug(String drugNo) throws Exception
	{
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			DrugEntity drugEntity=em.find(DrugEntity.class, drugNo);
			if(drugEntity==null)
			{
				return null;
			}
			em.remove(drugEntity);
			em.getTransaction().commit();
			return drugNo;
			
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "delDrug", e.getMessage());
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
	}
	
	public List<DrugTO> getTO(String drugNo) throws Exception
	{
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			List<DrugTO> toList=new ArrayList<DrugTO>();
			DrugTO drugTO=new DrugTO();
			DrugEntity drugEntity=em.find(DrugEntity.class, drugNo);
			if(drugEntity==null)
			{
				toList=null;
				return toList;
			}
			
			drugTO.setDrugName(drugEntity.getDrugName());
			drugTO.setDrugNo(drugEntity.getDrugNo());
			drugTO.setDateOfExpiry(drugEntity.getDateOfExpiry());
			drugTO.setQuantity(drugEntity.getQuantity());
			drugTO.setCostPerUnit(drugEntity.getCostPerUnit());
			drugTO.setCompany(drugEntity.getCompany());
			
			toList.add(drugTO);
			return toList;
			
			
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getTO", e.getMessage());
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
	}

	public String updateDrug(DrugTO drugTO) throws Exception
	{
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
		emf=Persistence.createEntityManagerFactory("Post_project");
		em = emf.createEntityManager();
		DrugEntity drugEntity=new DrugEntity();
		
		em.getTransaction().begin();
		drugEntity.setCompany(drugTO.getCompany());
		drugEntity.setDrugName(drugTO.getDrugName());
		drugEntity.setQuantity(drugTO.getQuantity());
		drugEntity.setDateOfExpiry(drugTO.getDateOfExpiry());
		drugEntity.setCostPerUnit(drugTO.getCostPerUnit());
		drugEntity.setDrugNo(drugTO.getDrugNo());
		em.merge(drugEntity);
		em.getTransaction().commit();
		
		return drugEntity.getDrugNo();
		
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateDrug", e.getMessage());
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
	}
}



























