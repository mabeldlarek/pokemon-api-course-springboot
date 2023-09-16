package com.belarek.PokemonReviewAPI.api.repository;

import com.belarek.PokemonReviewAPI.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
