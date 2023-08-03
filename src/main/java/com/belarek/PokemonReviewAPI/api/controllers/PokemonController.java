package com.belarek.PokemonReviewAPI.api.controllers;

import com.belarek.PokemonReviewAPI.api.dto.PokemonDTO;
import com.belarek.PokemonReviewAPI.api.dto.PokemonResponse;
import com.belarek.PokemonReviewAPI.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemons(@RequestParam (value="pageNo", defaultValue = "0", required = false) int pageNo,
                                       @RequestParam (value="pageSize", defaultValue = "5", required = false) int pageSize) {
        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDTO> getDetailPokemon(@PathVariable int id) {
        return ResponseEntity.ok(pokemonService.getById(id));
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDTO> createPokemon (@RequestBody PokemonDTO pokemonDTO){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDTO> updatePokemon (@PathVariable Integer id, @RequestBody PokemonDTO pokemonDTO){
        PokemonDTO reponse = pokemonService.updatePokemon(pokemonDTO, id);
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") Integer pokemonId) {
        pokemonService.deletePokemon(pokemonId);
        return new ResponseEntity<>("Pokemon delete", HttpStatus.OK);
    }


}
