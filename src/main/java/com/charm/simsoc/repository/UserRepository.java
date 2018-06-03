package com.charm.simsoc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.charm.simsoc.domain.User;

/**
 * The data access object who provides operations on User
 * 
 * @author charm
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
    /**
     * Find list of User by user name
     * 
     * @param name
     * @return list of User
     */
    public List<User> findByName(String name);

}
