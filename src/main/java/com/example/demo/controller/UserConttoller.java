package com.example.demo.controller;

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

import com.example.demo.model.Anime;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@ComponentScan
public class UserConttoller {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@RequestBody UserModel body){
		try {
			UserModel user =	userRepository.save(body);
			
			return new ResponseEntity<>(user,HttpStatus.CREATED);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<Object> getAllUser() {
		try {
			List<UserModel> listModel = userRepository.findAll();
			return new ResponseEntity<>(listModel, HttpStatus.OK);

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<Object> getAnimeById(@PathVariable("userId")Integer userId) {
		try {
			Optional<UserModel>foundUserModel =userRepository.findById(userId);
			if (foundUserModel.isPresent()) {
				
				UserModel userModel = foundUserModel.get();
				return new ResponseEntity<>(userModel, HttpStatus.OK);
			
			
			}else {
				return new ResponseEntity<>("User not found",HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<Object> deleteUserModelById(@PathVariable("userId")Integer userId) {
		try {
			Optional<UserModel>foundUserModel =userRepository.findById(userId);
			if (foundUserModel.isPresent()) {
				
				UserModel userModel = foundUserModel.get();
				
				userRepository.delete(userModel);
				
				return new ResponseEntity<>("Delete User success", HttpStatus.OK);
			
		
			}else {
				return new ResponseEntity<>("User not found",HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/searchuser")
	public ResponseEntity<Object> getProductBySearch(
	    @RequestParam("userId") Integer userId
	    //@RequestParam(value = "gameId", required = false) String title
	   // @RequestParam(value = "productDetail", required = false) String productDetail
	) {
	    try {
	        Optional<UserModel> foundUserModel = userRepository.findById(userId);
	        if (foundUserModel.isPresent()) {
	            UserModel user = foundUserModel.get();
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@PutMapping("/user/{userId}")
	public ResponseEntity<Object> updateUserModel(@PathVariable("userId") Integer userId, @RequestBody UserModel body) {
	    try {
	        Optional<UserModel> foundUserModel = userRepository.findById(userId);
	        
	        if (foundUserModel.isPresent()) {
	            UserModel userEdit = foundUserModel.get();
	            userEdit.setUserId(body.getUserId());
	            userEdit.setUsername(body.getUsername());
	            userEdit.setPassword(body.getPassword());
	            userEdit.setUserType(body.getUserType());
				
				
	     
	            userRepository.save(userEdit);
	            
	            return ResponseEntity.ok("Update User Success");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
	    }
	}

	

}
