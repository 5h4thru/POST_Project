package infy.com.hospital.service;

import infy.com.hospital.entity.BiomedicalEquipmentEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.BiomedicalEquipmentTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class BioMedicalService {
	
	public String addEquipment(BiomedicalEquipmentTO biomedicalEquipmentTO) throws Exception
	{
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
		emf=Persistence.createEntityManagerFactory("Post_project");
		em = emf.createEntityManager();
		BiomedicalEquipmentEntity biomedicalEquipmentEntity=new BiomedicalEquipmentEntity();
		
		em.getTransaction().begin();
		biomedicalEquipmentEntity.setEquipmentName(biomedicalEquipmentTO.getEquipmentName());
		biomedicalEquipmentEntity.setDateOfPurchase(biomedicalEquipmentTO.getDateOfPurchase());
		biomedicalEquipmentEntity.setCost(biomedicalEquipmentTO.getCost());
		biomedicalEquipmentEntity.setQuantity(biomedicalEquipmentTO.getQuantity());
		em.persist(biomedicalEquipmentEntity);
		em.getTransaction().commit();
		
		return biomedicalEquipmentEntity.getEquipmentNo();
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addEquipment", e.getMessage());
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
			
			Query query=em.createQuery("select be from BiomedicalEquipmentEntity be");
			
			List<BiomedicalEquipmentEntity> entityList=query.getResultList();
			List<SelectItem> idList=new ArrayList<SelectItem>();
			
			for(BiomedicalEquipmentEntity biomedicalEquipmentEntity:entityList)
			{
				idList.add(new SelectItem(biomedicalEquipmentEntity.getEquipmentNo(),biomedicalEquipmentEntity.getEquipmentName()));
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
	
	public String delEquipment(String equipmentNo) throws Exception
	{
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			BiomedicalEquipmentEntity biomedicalEquipmentEntity=em.find(BiomedicalEquipmentEntity.class,equipmentNo);
			if(biomedicalEquipmentEntity==null)
			{
				return null;
			}
			em.remove(biomedicalEquipmentEntity);
			em.getTransaction().commit();
			return equipmentNo;
			
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "delEquipment", e.getMessage());
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
	
	public List<BiomedicalEquipmentTO> getTO(String equipmentNo) throws Exception
	{
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			List<BiomedicalEquipmentTO> toList=new ArrayList<BiomedicalEquipmentTO>();
			BiomedicalEquipmentTO biomedicalEquipmentTO=new BiomedicalEquipmentTO();
			BiomedicalEquipmentEntity biomedicalEquipmentEntity=em.find(BiomedicalEquipmentEntity.class, equipmentNo);
			if(biomedicalEquipmentEntity==null)
			{
				return null;
			}
			biomedicalEquipmentTO.setEquipmentName(biomedicalEquipmentEntity.getEquipmentName());
			biomedicalEquipmentTO.setEquipmentNo(biomedicalEquipmentEntity.getEquipmentNo());
			biomedicalEquipmentTO.setCost(biomedicalEquipmentEntity.getCost());
			biomedicalEquipmentTO.setQuantity(biomedicalEquipmentEntity.getQuantity());
			biomedicalEquipmentTO.setDateOfPurchase(biomedicalEquipmentEntity.getDateOfPurchase());
			
			toList.add(biomedicalEquipmentTO);
			
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
	public String updateEqp(BiomedicalEquipmentTO biomedicalEquipmentTO) throws Exception
	{
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
		emf=Persistence.createEntityManagerFactory("Post_project");
		em = emf.createEntityManager();
		BiomedicalEquipmentEntity biomedicalEquipmentEntity=new BiomedicalEquipmentEntity();
		
		em.getTransaction().begin();
		biomedicalEquipmentEntity.setEquipmentName(biomedicalEquipmentTO.getEquipmentName());
		biomedicalEquipmentEntity.setDateOfPurchase(biomedicalEquipmentTO.getDateOfPurchase());
		biomedicalEquipmentEntity.setCost(biomedicalEquipmentTO.getCost());
		biomedicalEquipmentEntity.setQuantity(biomedicalEquipmentTO.getQuantity());
		biomedicalEquipmentEntity.setEquipmentNo(biomedicalEquipmentTO.getEquipmentNo());
		em.merge(biomedicalEquipmentEntity);
		em.getTransaction().commit();
		
		return biomedicalEquipmentEntity.getEquipmentNo();
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateEqp", e.getMessage());
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
