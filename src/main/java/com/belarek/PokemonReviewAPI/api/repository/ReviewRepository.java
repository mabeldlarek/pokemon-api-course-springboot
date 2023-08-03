package com.belarek.PokemonReviewAPI.api.repository;

import com.belarek.PokemonReviewAPI.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByPokemonId(int pokemonId);
}
