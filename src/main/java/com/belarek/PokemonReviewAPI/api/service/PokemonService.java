package com.belarek.PokemonReviewAPI.api.service;

import com.belarek.PokemonReviewAPI.api.dto.PokemonDTO;
import com.belarek.PokemonReviewAPI.api.dto.PokemonResponse;

import java.util.List;

public interface PokemonService {
        PokemonDTO createPokemon(PokemonDTO pokemonDTO);
        PokemonResponse getAllPokemon(int pageNo, int pageSize);
        PokemonDTO getById(Integer id);
        PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id);

        void deletePokemon(Integer id);
}
