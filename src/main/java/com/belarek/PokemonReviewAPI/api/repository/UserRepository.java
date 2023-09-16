package com.belarek.PokemonReviewAPI.api.repository;

import com.belarek.PokemonReviewAPI.api.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserRepository, Integer> {
    Optional<UserEntity> findByUsername(String username);

}
