package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.TOURates;

/**
 * Interface for generic CRUD operations on TOURates table in database.
 * @author Nitin K.
 */
public interface TOURatesRepository extends CrudRepository<TOURates,Integer>{

}
