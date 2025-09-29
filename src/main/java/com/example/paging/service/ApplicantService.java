package com.example.paging.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paging.entity.Applicant;
import com.example.paging.repository.ApplicantCrudRepository;
@Service
public class ApplicantService {
	
	@Autowired
	private ApplicantCrudRepository applicantCrudRepository;
	
	public List<Applicant> getAllApplicants(){
		Iterable <Applicant> applicant = applicantCrudRepository.findAll();
		
		List<Applicant> applist = new ArrayList<>();
		applicant.forEach(applist::add);
		 
		 return applist;
		
	}

	public Optional<Applicant> getById(Long id){
		//Optional<Applicant> applicant = applicantCrudRepository.findById(id);


		//return applicant.orElse(null);
		return applicantCrudRepository.findById(id);

	}
    
	public Applicant saveApplicantCrud(Applicant applicant) {
		
		return applicantCrudRepository.save(applicant);
	}
}
