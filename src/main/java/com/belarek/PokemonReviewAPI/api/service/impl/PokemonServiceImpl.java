package com.belarek.PokemonReviewAPI.api.service.impl;

import com.belarek.PokemonReviewAPI.api.dto.PokemonResponse;
import com.belarek.PokemonReviewAPI.api.models.Pokemon;
import com.belarek.PokemonReviewAPI.api.dto.PokemonDTO;
import com.belarek.PokemonReviewAPI.api.exceptions.PokemonNotFoundException;
import com.belarek.PokemonReviewAPI.api.repository.PokemonRepository;
import com.belarek.PokemonReviewAPI.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;
    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDTO pockemonResponse = new PokemonDTO();
        pockemonResponse.setId(newPokemon.getId());
        pockemonResponse.setName(newPokemon.getName());
        pockemonResponse.setType(newPokemon.getType());

        return  pockemonResponse;
    }

    @Override
    public PokemonResponse getAllPokemon(int pageNo, int noSize) {
        Pageable pageable = PageRequest.of(pageNo, noSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> listOfPokemon = pokemons.getContent();
        List<PokemonDTO> content = pokemons.stream().map(p->mapToDTO(p)).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalELements(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());

        return pokemonResponse;
    }

    @Override
    public PokemonDTO getById(Integer id) {
       Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found by id"));
               return mapToDTO(pokemon);
    }

    @Override
    public PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found by id"));
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());

        pokemonRepository.save(pokemon);
        return mapToDTO(pokemon);
    }

    @Override
    public void deletePokemon(Integer id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found by id"));
        pokemonRepository.deleteById(pokemon.getId());
    }

    private PokemonDTO mapToDTO(Pokemon pokemon){
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setId(pokemon.getId());
        pokemonDTO.setName(pokemon.getName());
        pokemonDTO.setType(pokemon.getType());
        return pokemonDTO;
    }

    private PokemonDTO mapToEntity(Pokemon pokemon){
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setId(pokemon.getId());
        pokemonDTO.setName(pokemon.getName());
        pokemonDTO.setType(pokemon.getType());
        return pokemonDTO;
    }


}
