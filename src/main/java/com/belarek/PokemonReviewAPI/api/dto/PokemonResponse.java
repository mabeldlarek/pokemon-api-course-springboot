package com.belarek.PokemonReviewAPI.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {
    private List<PokemonDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalELements;
    private int totalPages;
    private boolean last;
}
