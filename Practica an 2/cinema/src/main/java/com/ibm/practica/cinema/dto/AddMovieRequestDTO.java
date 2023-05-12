package com.ibm.practica.cinema.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddMovieRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    private float price;

    @NotNull
    private int maxSeats;

    @NotNull
    private LocalTime runningHours;

    @NotNull
    private boolean is3D;

    @NotNull
    private String movieHall;

}
