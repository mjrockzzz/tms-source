package com.ge.tms.controller;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ge.tms.audit.UserRevEntity;
import com.ge.tms.common.Manifest;
import com.ge.tms.common.ResultModel;
import com.ge.tms.dao.AdminAccountRepository;
import com.ge.tms.dao.UserRevisionEntityRepository;
import com.ge.tms.entity.AdminAccount;
import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.util.DateUtility;

/**
 * @author Nitin K.
 * Contains APIs for testing purpose.
 */
@RestController
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private AdminAccountRepository adminAccountRepository;
	
	@Autowired
	private UserRevisionEntityRepository  userRevisionEntityRepository;
	
	@PersistenceContext
	private EntityManager  entityManager;

	@RequestMapping(value = "/")
	public Object home() {
		return new Manifest();
	}

	/**
	 * 测试接口
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/testauth")
	@PreAuthorize("hasRole('TEST')")
	public ResultModel testAuth(@RequestBody JSONObject params) {
		return ResultModel.SUCCESS(params);
	}

	@RequestMapping(value = "/hello")
	// @PreAuthorize("hasRole('TEST')")
	public String hello(HttpSession session) {
		AdminAccount adminAccount = new AdminAccount();
		System.out.println("Admin name is" + session.getAttribute("adminName"));
		adminAccount.setAdminName("girish");
		adminAccount.setOriginPassword("girish");
		adminAccountRepository.save(adminAccount);
		return "Hello";
	}

	@RequestMapping(value = "/demo")
	// @PreAuthorize("hasRole('TEST')")
	public String demo() throws ParseException {
		/*java.sql.Date startdate = DateUtility.getStartingSqlDateOfCurrentMonth("20170804");

		logger.info("Starting date is" + startdate.toString());
		java.sql.Date newDate = DateUtility.getReducedSqlDatewithMonths("20170804", 6);
		logger.info("Month reducing new date is" + newDate.toString());
		java.sql.Date currentDate=DateUtility.getSqlDateFromStringDate("20170812");
		System.out.println("Current date is"+currentDate.toString());
		int previousYear=DateUtility.getPreviousYearFromSqlDate(currentDate);
		System.out.println("Previous Year is"+previousYear);*/
		
	    AuditReader reader = AuditReaderFactory.get(entityManager);
	    
		  /*AuditQuery query = reader.createQuery().forRevisionsOfEntity(CustomerAccount.class, false, true);
	        // query.add(AuditEntity.property("name").like("Romain%"));
	        List<Object[]> list = query.getResultList();
	        for (Object[] array : list) {
	            for (Object x : array) {
	                System.out.print(x + " | ");
	            }
	            System.out.println("");
	        }
*/
	   /* List<CustomerAccount> persons = reader.createQuery().forEntitiesAtRevision(CustomerAccount.class,39).getResultList();
        for (CustomerAccount p : persons) {
            System.out.println("--> " + p);
        }
		*/
	    
	    
	    List<Number> revisions = reader.getRevisions(CustomerAccount.class, 1);
        for (Number n : revisions) {
        	CustomerAccount p = reader.find(CustomerAccount.class,1, n);
            System.out.printf("\t[Rev #%1$s] > %2$s\n", n, p);
           UserRevEntity revEntity= userRevisionEntityRepository.findById(n.intValue());
          long time= revEntity.getTimestamp();
           System.out.println("Modified At"+new Date(time)+"Modified By"+revEntity.getUsername());
        }
	    
        
        
        
		return "Demo";
	}

}
