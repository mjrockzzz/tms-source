package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.AdminAccount;

/**
 * @author Nitin K.
 * This interface contains method to get data from AdminAccount table in database.
 */
public interface AdminAccountRepository extends CrudRepository <AdminAccount, Long> {

	
    /**
     * Connects to database and returns admin details for given admin name.
     * @author Nitin K.
     * @param adminName String
     * @return AdminAccount
     */
    public AdminAccount findByAdminName(String adminName);

}
