package com.belarek.PokemonReviewAPI.api.controllers;

import com.belarek.PokemonReviewAPI.api.dto.ReviewDTO;
import com.belarek.PokemonReviewAPI.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable (value = "pokemonId") int pokemonId, @RequestBody ReviewDTO reviewDto){
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public List<ReviewDTO> getReviewsByPokemonId(@PathVariable (value = "pokemonId") int pokemonId){
        return reviewService.getReviewsByPokemonId(pokemonId);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews/{id}")
    public  ResponseEntity<ReviewDTO> getReviewsById(@PathVariable (value = "pokemonId") int pokemonId, @PathVariable (value = "id") int reviewId){
        ReviewDTO reviewDTO = reviewService.getReviewById(pokemonId,reviewId);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    @PutMapping("/pokemon/{pokemonId}/reviews/{id}")
    public  ResponseEntity<ReviewDTO> updateReview(@PathVariable (value = "pokemonId") int pokemonId, @PathVariable (value = "id") int reviewId,  @RequestBody ReviewDTO reviewDTO){
        ReviewDTO updatedReview = reviewService.updateReview(pokemonId, reviewId, reviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{pokemonId}/reviews/{id}")
    public  ResponseEntity<String> updateReview(@PathVariable (value = "pokemonId") int pokemonId, @PathVariable (value = "id") int reviewId){
       reviewService.deleteReview(pokemonId, reviewId);
        return new ResponseEntity<>("Review deleted sucessfully", HttpStatus.OK);
    }
}
