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
import com.example.demo.repository.AnimeRepository;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;



//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@ComponentScan
public class AnimeController {
	@Autowired
	AnimeRepository animeRepository;

	@PostMapping("/anime")
	public ResponseEntity<Object> addUser(@RequestBody Anime body) {
		try {
			Anime newAnime = animeRepository.save(body);

			return new ResponseEntity<>(newAnime, HttpStatus.CREATED);

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/anime")
	public ResponseEntity<Object> getAllBoardgame() {
		try {
			List<Anime> listAnimes = animeRepository.findAll();
			return new ResponseEntity<>(listAnimes, HttpStatus.OK);

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/anime/{animeID}")
	public ResponseEntity<Object> getAnimeById(@PathVariable("animeID")Integer animeID) {
		try {
			Optional<Anime>foundAnime =animeRepository.findById(animeID);
			if (foundAnime.isPresent()) {
				
				Anime anime = foundAnime.get();
				return new ResponseEntity<>(anime, HttpStatus.OK);
			
			
			}else {
				return new ResponseEntity<>("Anime not found",HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/anime/{animeID}")
	public ResponseEntity<Object> deleteAnimeById(@PathVariable("animeID")Integer animeID) {
		try {
			Optional<Anime>foundAnime =animeRepository.findById(animeID);
			if (foundAnime.isPresent()) {
				
				Anime anime = foundAnime.get();
				
				animeRepository.delete(anime);
				
				return new ResponseEntity<>("Delete Anime success", HttpStatus.OK);
			
		
			}else {
				return new ResponseEntity<>("Anime not found",HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/searchanime")
	public ResponseEntity<Object> getProductBySearch(
	    @RequestParam("animeID") Integer animeID
	    //@RequestParam(value = "gameId", required = false) String title
	   // @RequestParam(value = "productDetail", required = false) String productDetail
	) {
	    try {
	        Optional<Anime> foundAnime = animeRepository.findById(animeID);
	        if (foundAnime.isPresent()) {
	            Anime anime = foundAnime.get();
	            return new ResponseEntity<>(anime, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Anime not found", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@PutMapping("/anime/{animeID}")
	public ResponseEntity<Object> updateAnime(@PathVariable("animeID") Integer animeID, @RequestBody Anime body) {
	    try {
	        Optional<Anime> foundanime = animeRepository.findById(animeID);
	        
	        if (foundanime.isPresent()) {
	            Anime animeEdit = foundanime.get();
	            animeEdit.setAdminRating(body.getAdminRating());
				animeEdit.setAnimename(body.getAnimename());
				animeEdit.setAnimephoto(body.getAnimephoto());
				animeEdit.setPhotoData(body.getPhotoData());
				
				animeEdit.setAnimestudio(body.getAnimestudio());
				animeEdit.setAnimesynopsis(body.getAnimesynopsis());
				animeEdit.setAnimeType(body.getAnimeType());
				animeEdit.setAnimeyear(body.getAnimeyear());
				
		
				animeEdit.setAverageRating(body.getAverageRating());
				animeEdit.setUser(body.getUser());

	     
	            animeRepository.save(animeEdit);
	            
	            return ResponseEntity.ok("Update Anime Success");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anime not found");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
	    }
	}

	
	
}
