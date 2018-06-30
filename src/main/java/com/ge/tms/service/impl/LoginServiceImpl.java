package com.ge.tms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ge.tms.common.consts.CommonMessages;
import com.ge.tms.dao.AdminAccountRepository;
import com.ge.tms.dto.AuthenticateAdmin;
import com.ge.tms.entity.AdminAccount;
import com.ge.tms.service.LoginService;

/**
 * Service class containing method for login.
 * @author Nitin K.
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private AdminAccountRepository adminAccountRepository;

	/**
	 * @author Nitin K.
	 * @purpose to login to admin to tms portal
	 * @param adminAccount AdminAccount
	 * @return ResponseEntity<AuthenticateAdmin>
	 * @date 2017-07-27
	 */
	@Override
	public ResponseEntity<AuthenticateAdmin> login(AdminAccount adminAccount) {
		// TODO Auto-generated method stub
		logger.info("Entered into login service method.....");
		AuthenticateAdmin authenticateAdmin = new AuthenticateAdmin();

		AdminAccount existedAdminAccount = adminAccountRepository.findByAdminName(adminAccount.getAdminName());

		if (existedAdminAccount == null) {
			logger.debug("user not found with name " + adminAccount.getAdminName());
			authenticateAdmin.setMessage(CommonMessages.INVALID_USERNAME);
			return new ResponseEntity<AuthenticateAdmin>(authenticateAdmin, HttpStatus.NOT_FOUND);
		} else {
			if (existedAdminAccount.getPassword().equals(adminAccount.getPassword())) {
				logger.debug("user found with name " + adminAccount.getAdminName());
				authenticateAdmin.setMessage(CommonMessages.VALID_USER);
				authenticateAdmin.setAdminName(existedAdminAccount.getAdminName());
				return new ResponseEntity<AuthenticateAdmin>(authenticateAdmin, HttpStatus.OK);

			}
		}
		logger.debug("Password is wrong for user " + adminAccount.getAdminName());
		authenticateAdmin.setMessage(CommonMessages.INVALID_PASSWORD);
		return new ResponseEntity<AuthenticateAdmin>(authenticateAdmin, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
