package com.ibm.practica.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EditUserRequestDTO {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
