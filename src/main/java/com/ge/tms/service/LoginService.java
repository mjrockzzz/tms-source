package com.ge.tms.service;

import org.springframework.http.ResponseEntity;

import com.ge.tms.dto.AuthenticateAdmin;
import com.ge.tms.entity.AdminAccount;

/**
 * Service class containing method for login.
 * @author Nitin K.
 */
public interface LoginService {

	/**
	 * @author Nitin K.
	 * @purpose to login to admin to tms portal
	 * @param adminAccount AdminAccount
	 * @return ResponseEntity<AuthenticateAdmin>
	 * @date 2017-07-27
	 */
	public ResponseEntity<AuthenticateAdmin> login(AdminAccount adminAccount);
	


}
