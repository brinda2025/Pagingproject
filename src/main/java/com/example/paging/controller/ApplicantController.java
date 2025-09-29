package com.example.paging.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.paging.exception.ApplicantNotFoundException;
import com.example.paging.exception.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.paging.entity.Applicant;
import com.example.paging.service.ApplicantService;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {
	
	@Autowired
	private ApplicantService applicantService;
	
	@GetMapping
	public List<Applicant> getAllApplicants(){
		System.out.println("all method");

		return applicantService.getAllApplicants();
	}

	@GetMapping("{id}")
	public ResponseEntity<?>getById(@PathVariable Long id){
        try {
			Applicant appi = applicantService.getById(id).orElseThrow(() -> new ApplicantNotFoundException("applicant not found"+id));

			System.out.println("id method");
			//return new ResponseEntity<>(appi, HttpStatus.OK);
			return ResponseEntity.ok(appi);
		} /*catch (ApplicantNotFoundException e) {
			ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),e.getMessage(),"product not found");
			return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		}*/

		catch (ApplicantNotFoundException e) {

			return handleApplicantNotFoundException(e) ;
		}
	}

     @PostMapping
     public Applicant saveApplicant(@RequestBody Applicant applicant) {
     return applicantService.saveApplicantCrud(applicant);
     
    }
	@ExceptionHandler(ApplicantNotFoundException.class)
	public ResponseEntity<?> handleApplicantNotFoundException(ApplicantNotFoundException exception){
		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),exception.getMessage(),"Applicant not found");
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
