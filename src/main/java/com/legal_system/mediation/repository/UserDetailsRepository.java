package com.legal_system.mediation.repository;

import org.springframework.stereotype.Repository;
import com.legal_system.mediation.model.UserDetails;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer>{
    
}
