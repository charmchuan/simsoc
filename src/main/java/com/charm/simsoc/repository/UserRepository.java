package com.charm.simsoc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.charm.simsoc.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    public List<User> findByName(String name);

}
