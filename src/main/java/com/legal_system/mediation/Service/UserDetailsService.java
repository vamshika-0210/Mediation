package com.legal_system.mediation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.legal_system.mediation.model.UserDetails;
import com.legal_system.mediation.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    
    @Autowired
    private UserDetailsRepository UserDetailsRepo;    
    
    public List<UserDetails> getuserDetails(){
        return UserDetailsRepo.findAll();
    }
}
