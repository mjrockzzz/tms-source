package com.ge.tms.audit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.envers.RevisionListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Nitin K.
 * This class is used in auditing to get the current user from the session.
 */
public class UserRevisionListener implements RevisionListener {

	// public final static String USERNAME = "Suay";

	public String adminname;

	@Override
	public void newRevision(Object revisionEntity) {
		UserRevEntity exampleRevEntity = (UserRevEntity) revisionEntity;
		System.out.println("Audit is called..Admin name is" + getAdminName());
		exampleRevEntity.setUsername(getAdminName());
	}

	/**
	 * Method to get the http session for the current http request.
	 * @author Nitin K.
	 * @param request HttpServletRequest
	 * @return the current http session object
	 */
	public HttpSession getHttpSession(HttpServletRequest request) {
		return request.getSession(false);
	}

	/**
	 * Method to get the user in the current session for the http request.
	 * @return the current user in the session
	 * @author Nitin K.
	 */
	public String getAdminName() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = sra.getRequest();
		HttpSession session = getHttpSession(req);
		if (session != null) {
			adminname = (String) session.getAttribute("adminName");
		}
		return adminname;
	}

}