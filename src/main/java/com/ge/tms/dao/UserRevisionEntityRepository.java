package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.audit.UserRevEntity;

/**
 * @author Nitin K.
 * This interface contains methods to get data from UserRevEntity table in database.
 */
public interface UserRevisionEntityRepository extends CrudRepository <UserRevEntity, Integer> {

    /**
	 * Connects to database and returns a UserRevEntity for given id. 
	 * @author Nitin K.
     * @param id int
     * @return UserRevEntity
     */
    public UserRevEntity findById(int id);
}