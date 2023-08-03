package com.belarek.PokemonReviewAPI.api.service.impl;

import com.belarek.PokemonReviewAPI.api.dto.ReviewDTO;
import com.belarek.PokemonReviewAPI.api.exceptions.PokemonNotFoundException;
import com.belarek.PokemonReviewAPI.api.exceptions.ReviewNotFoundException;
import com.belarek.PokemonReviewAPI.api.models.Pokemon;
import com.belarek.PokemonReviewAPI.api.models.Review;
import com.belarek.PokemonReviewAPI.api.repository.PokemonRepository;
import com.belarek.PokemonReviewAPI.api.repository.ReviewRepository;
import com.belarek.PokemonReviewAPI.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<ReviewDTO> getReviewsByPokemonId(int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream().map(review -> mapToDTO(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(int pokemonId, int reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associated pokemon not found"));
        System.out.println(review.getPokemon().getId() + "&&&&&&&" + pokemon.getId());
        if(review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belond to a pokemon");
        }

        return mapToDTO(review);
    }

    @Override
    public ReviewDTO createReview(int pokemonId, ReviewDTO reviewDTO) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundException("Pokemon not found"));
        Review review = new Review();
        review.setTitle(reviewDTO.getTitle());
        review.setStars(reviewDTO.getStars());
        review.setContent(reviewDTO.getContent());
        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);

        ReviewDTO reviewResponse = new ReviewDTO();
        reviewResponse.setTitle(newReview.getTitle());
        reviewResponse.setStars(newReview.getStars());
        reviewResponse.setContent(newReview.getContent());

        return reviewResponse;
    }

    @Override
    public ReviewDTO updateReview(int pokemonId, int reviewID, ReviewDTO reviewDTO) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundException("Pokemon not found"));
        Review review = reviewRepository.findById(reviewID).orElseThrow(()-> new ReviewNotFoundException("Review not found"));

        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belond to a pokemon");
        }

        review.setTitle(reviewDTO.getTitle());
        review.setStars(reviewDTO.getStars());
        review.setContent(reviewDTO.getContent());

        Review updateReview = reviewRepository.save(review);

        return mapToDTO(updateReview);
    }

    @Override
    public void deleteReview(int pokemonId, int id) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundException("Pokemon not found"));
        Review review = reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException("Review not found"));

        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belond to a pokemon");
        }

        reviewRepository.deleteById(review.getId());
    }

    private ReviewDTO mapToDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setStars(review.getStars());
        reviewDTO.setContent(review.getContent());
        return reviewDTO;
    }

    private Review mapToEntity(ReviewDTO reviewDTO){
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setTitle(reviewDTO.getTitle());
        review.setStars(reviewDTO.getStars());
        review.setContent(reviewDTO.getContent());
        return review;
    }
}
