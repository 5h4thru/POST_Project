package infy.com.hospital.service;

import infy.com.hospital.entity.DoctorEntity;
import infy.com.hospital.entity.OTEntity;
import infy.com.hospital.entity.WardEntity;
import infy.com.hospital.logger.ErrorLogger;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class TariffService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
	EntityManager em=emf.createEntityManager();
	public List<SelectItem> getDoctors() throws Exception
	{
		List <SelectItem> docs=new ArrayList<SelectItem>();
		try
		{
			Query query=em.createQuery("select de from DoctorEntity de");
			List <?>doctors=query.getResultList();
			SelectItem selectItem=new SelectItem();
			
			for(int i=0;i<doctors.size();i++)
			{
				DoctorEntity doctorEntity=(DoctorEntity)doctors.get(i);
				Query query1=em.createQuery("select ee.employeeName from EmployeeEntity ee where ee.userName=?1");
				query1.setParameter(1, doctorEntity.getUserName());
				String employeeName=(String)query1.getSingleResult();
				selectItem=new SelectItem(doctorEntity.getUserName(),employeeName);
				docs.add(selectItem);
			}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "getDoctorsService", e.getMessage());
			throw e;
		}
		return docs;
	}
	public List<SelectItem> getWards() throws Exception
	{
		List <SelectItem> wardSeLectItemList=new ArrayList<SelectItem>();
		try
		{
			Query query=em.createQuery("select we from WardEntity we");
			List <?>wardList=query.getResultList();
			SelectItem selectItem=new SelectItem();
			for(int i=0;i<wardList.size();i++)
			{
				WardEntity wardEntity=(WardEntity)wardList.get(i);
				selectItem=new SelectItem(wardEntity.getWardNo(),wardEntity.getWType());
				wardSeLectItemList.add(selectItem);
			}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "getWards", e.getMessage());
			throw e;
		}
		return wardSeLectItemList;
		
	}
	public List<SelectItem> getOts() throws Exception
	{
		List <SelectItem> otSelectItemList=new ArrayList<SelectItem>();
		try
		{
			Query query=em.createQuery("select ot from OTEntity ot");
			List <?> otList=query.getResultList();
			SelectItem selectItem=new SelectItem();

			for(int i=0;i<otList.size();i++)
			{
				OTEntity otEntity=(OTEntity)otList.get(i);
				selectItem=new SelectItem(otEntity.getOtNo(),String.valueOf(otEntity.getOtNo()));
				otSelectItemList.add(selectItem);
			}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "getWards", e.getMessage());
			throw e;
		}
		return otSelectItemList;
		
	}
	
	public Integer docCost(String doctorId) throws Exception
	{
		DoctorEntity doctorEntity=null;
		try
		{
			Query query=em.createQuery("select de from DoctorEntity de where de.userName=?1 ");
			query.setParameter(1, doctorId);
			doctorEntity=(DoctorEntity)query.getSingleResult();
			
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "getOts", e.getMessage());
			throw e;
		}
		return doctorEntity.getConsultationFees();
	}
	
	public double wardCost(int wardNo) throws Exception
	{
		WardEntity wardEntity=null;
		try
		{
			Query query=em.createQuery("select we from WardEntity we where we.wardNo=?1 ");
			query.setParameter(1, String.valueOf(wardNo));
			wardEntity=(WardEntity)query.getSingleResult();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "wardCost", e.getMessage());
			throw e;
		}
		return wardEntity.getWPrice();
		
	}
	
	public double otCost(int otNo) throws Exception
	{
		OTEntity otEntity=null;
		try
		{
			Query query=em.createQuery("select ot from  OTEntity ot where ot.otNo=?1 ");
			query.setParameter(1, otNo);
			otEntity=(OTEntity)query.getSingleResult();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "wardCost", e.getMessage());
			throw e;
		}
		return otEntity.getPrice();
	}

}
