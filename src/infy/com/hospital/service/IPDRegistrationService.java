package infy.com.hospital.service;

import infy.com.hospital.entity.DoctorEntity;
import infy.com.hospital.entity.EmployeeEntity;
import infy.com.hospital.entity.IpdAppointmentEntity;
import infy.com.hospital.entity.PatientEntity;
import infy.com.hospital.entity.WardEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.IpdAppointmentTO;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class IPDRegistrationService {
public List<SelectItem> getDoctors1() throws Exception
{
	EntityManager em=null;
	try{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
		em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();	
	Query q=em.createQuery("select k from DoctorEntity k");
	List<DoctorEntity> doctorList=(List<DoctorEntity>)q.getResultList();
	List<EmployeeEntity> emplList=new ArrayList<EmployeeEntity>();
	List<SelectItem> doctors=new ArrayList<SelectItem>();
	
	for(DoctorEntity d:doctorList)
	{
		Query q1=em.createQuery("select c from EmployeeEntity c where c.userName=?1");
		q1.setParameter(1,d.getUserName());
		emplList.add((EmployeeEntity) q1.getSingleResult());	
	}
	et.commit();
	for(EmployeeEntity e:emplList)
	{
		doctors.add(new SelectItem(e.getUserName(),e.getEmployeeName()));
	}
	
	return doctors;
	}
	catch (Exception e) {
//		System.out.println("am here");
//		e.printStackTrace();
		ErrorLogger.logError(this.getClass().getName(),"getDoctors1", e.getMessage());
		throw e;
	}	
}
public String getDepartmentName(String doctorId) throws Exception
{
	EntityManager em=null;
	try{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
		em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();	
		Query q=em.createQuery("select d from DoctorEntity d where d.userName=?1");
		q.setParameter(1,doctorId);
		DoctorEntity d=(DoctorEntity) q.getSingleResult();
		et.commit();
		if(d!=null){
		return d.getDepartment();
		}
		else
		{
			return null;
		}
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"getDepartmentName", e.getMessage());
		throw e;
	}
}
public List<SelectItem> getWards(String wardType) throws Exception
{
	List<SelectItem> wards=new ArrayList<SelectItem>();
	EntityManager em=null;
	try{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
		em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();	
	Query q=em.createQuery("select k from WardEntity k where k.wType=?1");
	q.setParameter(1,wardType);
	List<WardEntity> wList=q.getResultList();
	et.commit();
	if(wList.isEmpty()==false){
	for(WardEntity w:wList){
		wards.add(new SelectItem(w.getWardNo(),"Ward"+w.getWardNo()));
	}
	}
	return wards;
	}
	catch (Exception e) {
//		System.out.println("am here");
//		e.printStackTrace();
		ErrorLogger.logError(this.getClass().getName(),"getWards", e.getMessage());
		throw e;
	}
 	
}
public List<Object> getBeds(String wardNo) throws Exception
{
	List<Object> beds=new ArrayList<Object>();
	Set<Object> bedSet=new LinkedHashSet<Object>(); 
	List<Integer> bNo=new ArrayList<Integer>();
	EntityManager em=null;
	try{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
		em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();		
		et.begin();	
	Query q=em.createQuery("select k from WardEntity k where k.wardNo=?1");
	q.setParameter(1,wardNo);
	WardEntity wardEntity= (WardEntity) q.getSingleResult();
	Query q1=em.createQuery("select k from IpdAppointmentEntity k where k.wardNo=?1");
	q1.setParameter(1,wardNo);
	List<IpdAppointmentEntity> ipList=q1.getResultList();
	et.commit();	
	if(wardEntity!=null)
	{
	if(ipList.isEmpty()==false)
	{
		for(int i=1;i<=wardEntity.getNoOfBeds();i++)
		{		
			beds.add(i);
		}
						
		for(IpdAppointmentEntity ipd:ipList)
		{
	   beds.remove(ipd.getBedNo());
		}		
	}
	else
	{
		for(int i=1;i<=wardEntity.getNoOfBeds();i++)
		{		
			beds.add(i);
		}
	}
	}
	return beds;
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"getBeds", e.getMessage());
		throw e;
	}
}
public String ipdRegisterPatient(IpdAppointmentTO ipdAppointmentTO) throws Exception
{
	List<IpdAppointmentEntity> ipList=new ArrayList<IpdAppointmentEntity>();
	IpdAppointmentEntity ipdAppointmentEntity=new IpdAppointmentEntity();
	ipdAppointmentEntity.setWardNo(ipdAppointmentTO.getWardNo());
	ipdAppointmentEntity.setBedNo(ipdAppointmentTO.getBedNo());
	ipdAppointmentEntity.setAdmissionStatus(ipdAppointmentTO.getAdmissionStatus());
	ipdAppointmentEntity.setDepartment(ipdAppointmentTO.getDepartment());
	ipdAppointmentEntity.setDateOfAdmission(ipdAppointmentTO.getDateOfAdmission());
	ipdAppointmentEntity.setDateOfDischarge(ipdAppointmentTO.getDateOfDischarge());
	ipdAppointmentEntity.setDoctorId(ipdAppointmentTO.getDoctorId());
	//ipdAppointmentEntity.setDoctorName(ipdAppointmentTO.getDoctorName());
	//ipdAppointmentEntity.setPatientId(ipdAppointmentTO.getPatientId());
	ipdAppointmentEntity.setReasonForAdmission(ipdAppointmentTO.getReasonForAdmission());
	ipdAppointmentEntity.setUsername(ipdAppointmentTO.getUsername());
	EntityManager em=null;
	try{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
		em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();	
		em.persist(ipdAppointmentEntity);
		String regNo=ipdAppointmentEntity.getRegistrationNo();	
			Query q=em.createQuery("select w from WardEntity w where w.wardNo=?1");
		q.setParameter(1,ipdAppointmentEntity.getWardNo());
		WardEntity w=(WardEntity) q.getSingleResult();
		Query q1=em.createQuery("select i from IpdAppointmentEntity i where i.wardNo=?1");
		q1.setParameter(1,ipdAppointmentEntity.getWardNo());
		ipList=q1.getResultList();
		//System.out.println(ipList.size()+""+w.getNoOfBeds());
		if(ipList.size()==w.getNoOfBeds())
		{
			WardEntity w1=em.find(WardEntity.class,ipdAppointmentEntity.getWardNo());
			w1.setWStatus("OC");
		}
		et.commit();
		return regNo;		
	}
	catch (Exception e) {
//		System.out.println("am here");
//		e.printStackTrace();
		ErrorLogger.logError(this.getClass().getName(),"ipdRegisterPatient", e.getMessage());
		throw e;
	}
}
public boolean checkPatientId(String userName)
{
	EntityManager em=null;
	try{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
		em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();	
		Query q=em.createQuery("select k from PatientEntity k where k.userName=?1");
		q.setParameter(1,userName);
		PatientEntity patientEntity=(PatientEntity) q.getSingleResult();
		et.commit();
	if(patientEntity!=null)
	{
		return true;
	}
	else
	{
		return false;
	}
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"ipdRegisterPatient", e.getMessage());
		return false;
	}
}
}
