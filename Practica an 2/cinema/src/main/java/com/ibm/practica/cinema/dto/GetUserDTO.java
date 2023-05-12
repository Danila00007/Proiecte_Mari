package com.ibm.practica.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
public class GetUserDTO {

    @NotNull
    private String userName;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
