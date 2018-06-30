package com.ge.tms.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ge.tms.common.consts.UrlPathConstants;
import com.ge.tms.dto.AuthenticateAdmin;
import com.ge.tms.entity.AdminAccount;
import com.ge.tms.service.LoginService;

/**
 * @author Nitin K.
 * Contains API for user login.
 */
@RestController
@RequestMapping(value = UrlPathConstants.LOGIN)
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	/**
	 * @author Nitin K.
	 * @purpose to login admin to tms portal
	 * @param adminAccount AdminAccount
	 * @param session HttpSession
	 * @return ResponseEntity<AuthenticateUser>
	 * @date 2017-07-05
	 */
	@RequestMapping(value = UrlPathConstants.AUTHENTICATE, method = RequestMethod.POST)
	public ResponseEntity<AuthenticateAdmin> login(@RequestBody AdminAccount adminAccount, HttpSession session) {
		logger.info("Entered into login controller.......");
		session.setAttribute("adminName", adminAccount.getAdminName());
		return loginService.login(adminAccount);
	}
}