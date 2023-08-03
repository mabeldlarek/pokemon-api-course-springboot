package com.belarek.PokemonReviewAPI.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class ErrorObject {
    private int status;
    private String message;
    private Date timeStamp;
}
