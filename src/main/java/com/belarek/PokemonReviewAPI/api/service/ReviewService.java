package com.belarek.PokemonReviewAPI.api.service;

import com.belarek.PokemonReviewAPI.api.dto.ReviewDTO;
import com.belarek.PokemonReviewAPI.api.models.Review;

import java.util.List;

public interface ReviewService {

    ReviewDTO createReview(int pokemonId, ReviewDTO reviewDTO);
    ReviewDTO updateReview(int pokemonId, int id, ReviewDTO reviewDTO);
    void deleteReview(int pokemonId, int id);
    List<ReviewDTO> getReviewsByPokemonId(int id);
    ReviewDTO getReviewById(int review, int pokemonId);
}
