package com.legal_system.mediation.Service;

import com.legal_system.mediation.model.Cases;
import com.legal_system.mediation.repository.CasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CasesService {
    @Autowired
    private com.legal_system.mediation.repository.CasesRepository CasesRepository;

    public void addCases(Cases Cases){
        CasesRepository.save(Cases);
    }

    public List<Cases> getAllCases(){
        return CasesRepository.findAll();
    }

    public Cases findMediator(int id){
        Optional<Cases> theCases =  CasesRepository.findById(id);
        return theCases.orElse(null);
    }
}
