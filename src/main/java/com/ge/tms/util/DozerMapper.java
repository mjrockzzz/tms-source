package com.ge.tms.util;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ge.tms.dto.WSCustomerAccount;
import com.ge.tms.entity.CustomerAccount;


//@Component
/**
 * Configuration class for Dozer mapper
 * @author Nitin K.
 */
public class DozerMapper extends BeanMappingBuilder{

	@Autowired(required = true)
	@Qualifier(value = "mapper")
	private DozerBeanMapper mapper;
	
	public DozerMapper() {
	}
	
	@PostConstruct
	public void initIt() throws Exception {
		mapper.addMapping(this);
	}
	
	@Override
	protected void configure() {
		//mapping(Agreement.class, WSAgreement.class);
		
		mapping(CustomerAccount.class, WSCustomerAccount.class);
	}

}
