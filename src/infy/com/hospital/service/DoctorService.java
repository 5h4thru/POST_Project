package infy.com.hospital.service;

import infy.com.hospital.entity.DoctorEntity;
import infy.com.hospital.entity.DrugRequestEntity;
import infy.com.hospital.entity.EmployeeEntity;
import infy.com.hospital.entity.OTEntity;
import infy.com.hospital.entity.PatientEntity;
import infy.com.hospital.entity.ProfileChangeRequestEntity;
import infy.com.hospital.entity.SurgeryScheduleEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.DoctorTO;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.EmployeeTO;
import infy.com.hospital.to.OTTO;
import infy.com.hospital.to.SurgeryScheduleTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class DoctorService {
	EntityManagerFactory emf=null;
	EntityManager em=null;
	EntityTransaction et=null;
	/*public void requestMedicine(DrugRequestTO dto){
		
	}
	*/
	
	@SuppressWarnings("unchecked")
	public List<DoctorTO> getDoctor(String docId, String department, String slot) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			
			List<DoctorEntity> docIdList = new ArrayList<DoctorEntity>();
			List<DoctorEntity> deptList = new ArrayList<DoctorEntity>();
			List<DoctorEntity> slotList = new ArrayList<DoctorEntity>();
			
			if(docId!=null) {
				Query docIdQuery = em.createQuery("select de from DoctorEntity de where de.userName=?1");
				docIdQuery.setParameter(1, docId);
				docIdList = docIdQuery.getResultList();
			}
			
			if(department!=null) {
				Query deptQuery = em.createQuery("select de from DoctorEntity de where de.department=?1");
				deptQuery.setParameter(1, department);
				deptList = deptQuery.getResultList();
			}
			
			String colSlot = null;
			if(slot != null) {
				if(slot.equals("S1")) {

					colSlot = "de.slot1";
				}
				if(slot.equals("S2")) {
					colSlot = "de.slot2";
				}
				if(slot.equals("S3")) {
					colSlot = "de.slot3";
				}

				Query slotQuery = em.createQuery("select de from DoctorEntity de where "+colSlot+"=?1");
				slotQuery.setParameter(1, "AV");
				slotList = slotQuery.getResultList();
			}
			List<DoctorEntity> tempList = new ArrayList<DoctorEntity>();
			List<DoctorEntity> newList = new ArrayList<DoctorEntity>();
			List<DoctorTO> newTOList = new ArrayList<DoctorTO>();
			int count=0;
			
			if(!docIdList.isEmpty()) {
				count++;
				for (DoctorEntity doctor : docIdList) {
					tempList.add(doctor);
				}
			}
			
			if(!deptList.isEmpty()) {
				count++;
				for (DoctorEntity doctor : deptList) {
					tempList.add(doctor);
				}
			}
			
			if(!(slotList.isEmpty())) {
				count++;
				for (DoctorEntity doctor : slotList) {
					tempList.add(doctor);
				}
			}

			int counter = 0;

			for (int i = 0;i < tempList.size();i++) {
				counter = 0;
				for (int j = i+1;j < tempList.size();j++) {
					if(tempList.get(i).getUserName() == tempList.get(j).getUserName()) {
						counter++;
					}
				}
				if(counter == count-1) {
					newList.add(tempList.get(i));
				}
			}
			
			for (DoctorEntity doctorEntity : newList) {
				DoctorTO dTO = new DoctorTO();
				dTO.setUserName(doctorEntity.getUserName());
				dTO.setConsultationFees(doctorEntity.getConsultationFees());
				dTO.setDepartment(doctorEntity.getDepartment());
				dTO.setSlot1(doctorEntity.getSlot1());
				dTO.setSlot2(doctorEntity.getSlot2());
				dTO.setSlot3(doctorEntity.getSlot3());
				newTOList.add(dTO);
			}
			
			return newTOList;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDoctor", e.getMessage());
			throw e;
		}
		
		finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DoctorTO> getDepartmentList() throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			
			Query query = em.createQuery("select distinct de from DoctorEntity de");
			
			List<DoctorEntity> departmentList = (List<DoctorEntity>) query.getResultList();
			List<DoctorTO> doctorTOList = new ArrayList<DoctorTO>();
			
			for (DoctorEntity department : departmentList) {
				DoctorTO dTO = new DoctorTO();
				dTO.setUserName(department.getUserName());
				dTO.setConsultationFees(department.getConsultationFees());
				dTO.setDepartment(department.getDepartment());
				dTO.setSlot1(department.getSlot1());
				dTO.setSlot2(department.getSlot2());
				dTO.setSlot3(department.getSlot3());
				doctorTOList.add(dTO);
			}
			
			return doctorTOList;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDepartmentList", e.getMessage());
			throw e;
		}
		
		finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	public String scheduleSurgery(SurgeryScheduleTO sst) throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		EntityTransaction et=null;
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			et=em.getTransaction();
			et.begin();
			Query q=em.createQuery("select s from SurgeryScheduleEntity s where s.dateOfSurgery=?1 and s.otNo=?2 and s.slot=?3");
			q.setParameter(1, sst.getDateOfSurgery());
			q.setParameter(2, sst.getOtNo());
			q.setParameter(3, sst.getSlot());
			List<SurgeryScheduleEntity> l=q.getResultList();
			
			if(l.size()==0)
			{
				SurgeryScheduleEntity sse=new SurgeryScheduleEntity();
				sse.setBookingStatus(sst.getBookingStatus());
				sse.setDateOfSurgery(sst.getDateOfSurgery());
				sse.setDoctorId(sst.getDoctorId());
				sse.setOtNo(sst.getOtNo());
				sse.setPatientId(sst.getPatientId());
				sse.setSlot(sst.getSlot());
				sse.setSurgeryName(sst.getSurgeryName());
				em.persist(sse);
				et.commit();
				return sse.getBookingId();
			}
			else
			{
				return null;
			}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"scheduleSurgery",e.getMessage());
			throw e;
		}
		finally{
			if(et!=null){
				em.close();
			}
		}
	}
	public Boolean updateDoctorDetails(DoctorTO doctorto,EmployeeTO eto) throws Exception{
		
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			et=em.getTransaction();
			et.begin();
			Query q=em.createQuery("select p from ProfileChangeRequestEntity p where p.doctorId=?1 and p.requestStatus=?2");
			q.setParameter(1,doctorto.getUserName());
			q.setParameter(2,'W');
			List<ProfileChangeRequestEntity> li=q.getResultList();
			if(li.size()==0)
			{
			ProfileChangeRequestEntity pe=new ProfileChangeRequestEntity();
			pe.setDoctorId(doctorto.getUserName());
			pe.setDepartment(doctorto.getDepartment());
			pe.setConsultationFees(doctorto.getConsultationFees());
			pe.setEmployeeName(eto.getEmployeeName());
			pe.setQualification(eto.getQualification());
			pe.setAddress(eto.getAddress());
			pe.setPhoneNo(eto.getPhoneNo());
			pe.setRequestStatus('W');
			em.persist(pe);
			et.commit();
			return true;
			}
			else
			{
				ProfileChangeRequestEntity pe1=li.get(0);
				pe1.setDoctorId(doctorto.getUserName());
				pe1.setDepartment(doctorto.getDepartment());
				pe1.setConsultationFees(doctorto.getConsultationFees());
				pe1.setEmployeeName(eto.getEmployeeName());
				pe1.setQualification(eto.getQualification());
				pe1.setAddress(eto.getAddress());
				pe1.setPhoneNo(eto.getPhoneNo());
				em.merge(pe1);
				et.commit();
				return true;
			}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"updateDoctorDetails",e.getMessage());
			throw e;
		}
		finally{
			if(et!=null){
				em.close();
			}
		}
		
	}
	public DoctorTO getDoctorDetails(String userName) throws Exception{
		DoctorEntity de=new DoctorEntity();
		DoctorTO dto=new DoctorTO();
	
		try{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			et=em.getTransaction();
			et.begin();
			Query q=em.createQuery("select e from DoctorEntity e where e.userName=?1");
			q.setParameter(1, userName);
			List<DoctorEntity> li1=q.getResultList();
			
			if(li1.size()!=0){
				de=li1.get(0);
				dto.setUserName(de.getUserName());
				dto.setDepartment(de.getDepartment());
				dto.setConsultationFees(de.getConsultationFees());
				dto.setSlot1(de.getSlot1());
				dto.setSlot2(de.getSlot2());
				dto.setSlot3(dto.getSlot3());
				return dto;
			}
			else{
				return null;
			}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getDoctorDetails",e.getMessage());
			throw e;
		}
		finally{
			if(et!=null){
				em.close();
			}
		}
	}
	public EmployeeTO getEmployeeDetails(String userName) throws Exception{
		
		EmployeeEntity ee=new EmployeeEntity();
		EmployeeTO eto=new EmployeeTO();
		try{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			et=em.getTransaction();
			et.begin();
			
			Query q=em.createQuery("select e from EmployeeEntity e where e.userName=?1");
			q.setParameter(1, userName);
			List<EmployeeEntity> li=q.getResultList();
			
			if(li.size()!=0){
				ee=li.get(0);
				eto.setEmployeeNo(ee.getEmployeeNo());
				eto.setEmployeeName(ee.getEmployeeName());
				eto.setDateOfJoining(ee.getDateOfJoining());
				eto.setDesignation(ee.getDesignation());
				eto.setUserName(ee.getUserName());
				eto.setAddress(ee.getAddress());
				eto.setPhoneNo(ee.getPhoneNo());
				eto.setQualification(ee.getQualification());
				return eto;
			}
			else{
				return null;
			}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getEmployeeDetails",e.getMessage());
			throw e;
		}
		finally{
			if(et!=null){
				em.close();
			}
		}
		
	}
	/*public List<DoctorTO> getDoctor(String department){
	
	}*/
	@SuppressWarnings("unchecked")
	public List<OTTO> listOfOT() throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		EntityTransaction et=null;
		
		List<OTEntity> lote=new ArrayList<OTEntity>();
		List<OTTO> lotto=new ArrayList<OTTO>();
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			Query q=em.createQuery("Select oe from OTEntity oe");
			lote=q.getResultList();
			
			for(OTEntity e:lote)
			{
				OTTO oto=new OTTO();
				oto.setOtNo(e.getOtNo());
				//oto.setPrice(e.getPrice());
				lotto.add(oto);
			}
			return lotto;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"listOfOT",e.getMessage());
			throw e;
		}
		finally{
			if(em!=null){
				em.close();
			}
		}
	}
	public Boolean validatePatientId(String patientId) throws Exception 
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		EntityTransaction et=null;
		try{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			 et=em.getTransaction();
			et.begin();
			Query q=em.createQuery("select p from PatientEntity p where p.userName=?1");
			q.setParameter(1, patientId);
			List<PatientEntity> l=q.getResultList();
			if(!l.isEmpty())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"validatePatientId",e.getMessage());
			throw e;
		}
		finally{
			if(em!=null){
				em.close();
			}
		}
	}
	/*public List<DoctorTO> getDepartmentList(){
	
	}*/
	public void requestMedicine(DrugRequestTO drugRequestTO) throws Exception
	{
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			DrugRequestEntity drugRequestEntity=new DrugRequestEntity();
			drugRequestEntity.setCompany(drugRequestTO.getManufacturer());
			drugRequestEntity.setRequestDate(drugRequestTO.getDate());
			drugRequestEntity.setDoctorId(drugRequestTO.getDoctorId());
			drugRequestEntity.setDrugName(drugRequestTO.getDrugName());
			drugRequestEntity.setReqStatus(drugRequestTO.getReqStatus());
			drugRequestEntity.setRequestType(drugRequestTO.getUrgent());
			em.persist(drugRequestEntity);
			em.getTransaction().commit();
			drugRequestTO.setDrugReqNo(drugRequestEntity.getDrugReqNo());
			
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "requestMedicine",e.getMessage());
			throw e;
		}
		
	}
}
