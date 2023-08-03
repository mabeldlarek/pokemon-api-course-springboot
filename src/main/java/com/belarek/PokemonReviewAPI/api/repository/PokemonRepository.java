package com.belarek.PokemonReviewAPI.api.repository;


import com.belarek.PokemonReviewAPI.api.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

}
