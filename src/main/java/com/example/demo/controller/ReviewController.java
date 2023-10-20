package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;


//@CrossOrigin(origins = "http://localhost:3000")

@ComponentScan
@RestController
public class ReviewController {
	@Autowired
	ReviewRepository reviewRepository;
	
	

	@PostMapping("/review")
	public ResponseEntity<Object> addUser(@RequestBody Review body){
		try {
			Review newReview =	reviewRepository.save(body);
			
			return new ResponseEntity<>(newReview,HttpStatus.CREATED);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	

	
	@GetMapping("/review")
	public ResponseEntity<Object> getAllReviweId() {
		try {
			List<Review> listreviwe = reviewRepository.findAll();
			return new ResponseEntity<>(listreviwe, HttpStatus.OK);

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/review/{reviweId}")
	public ResponseEntity<Object> getReviweById(@PathVariable("reviweId")Integer reviweId) {
		try {
			Optional<Review>foundreviwe =reviewRepository.findById(reviweId);
			if (foundreviwe.isPresent()) {
				
				Review reviwe = foundreviwe.get();
				return new ResponseEntity<>(reviwe, HttpStatus.OK);
			
			
			}else {
				return new ResponseEntity<>("reviwe not found",HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/review/{reviweId}")
	public ResponseEntity<Object> deleteReviweById(@PathVariable("reviweId")Integer reviweId) {
		try {
			Optional<Review>foundreviwe =reviewRepository.findById(reviweId);
			if (foundreviwe.isPresent()) {
				
				Review reviwe = foundreviwe.get();
				
				reviewRepository.delete(reviwe);
				
				return new ResponseEntity<>("Delete reviwe success", HttpStatus.OK);
			
		
			}else {
				return new ResponseEntity<>("reviwe not found",HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/searchreview")
	public ResponseEntity<Object> getProductBySearch(
	    @RequestParam("reviweId") Integer reviweId
	    //@RequestParam(value = "gameId", required = false) String title
	   // @RequestParam(value = "productDetail", required = false) String productDetail
	) {
	    try {
	        Optional<Review> foundreviwe = reviewRepository.findById(reviweId);
	        if (foundreviwe.isPresent()) {
	            Review reviwe = foundreviwe.get();
	            return new ResponseEntity<>(reviwe, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("reviwe not found", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@PutMapping("/review/{reviweId}")
	public ResponseEntity<Object> updateReviwe(@PathVariable("reviweId") Integer reviweId, @RequestBody Review body) {
	    try {
	        Optional<Review> foundreviwe = reviewRepository.findById(reviweId);
	        
	        if (foundreviwe.isPresent()) {
	            Review reviweEdit = foundreviwe.get();
	            reviweEdit.setReviweId(body.getReviweId());
	            reviweEdit.setRating(body.getRating());
	            reviweEdit.setComment(body.getComment());
	            reviweEdit.setReviweDate(body.getReviweDate());
				
				
				
		
	     
	            reviewRepository.save(reviweEdit);
	            
	            return ResponseEntity.ok("Update reviwe Success");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("reviwe not found");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
	    }
	}

	

  }


