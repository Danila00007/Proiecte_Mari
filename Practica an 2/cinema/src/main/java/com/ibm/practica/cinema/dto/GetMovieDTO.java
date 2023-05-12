package com.ibm.practica.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Builder
public class GetMovieDTO {

    @NotNull
    private String name;

    @NotNull
    private float price;

    @NotNull
    private int openSeats;

    @NotNull
    private LocalTime runningHours;

    @NotNull
    private boolean is3D;

    @NotNull
    private String movieHall;

    @NotNull
    private boolean isMoviePlayingNow;
}
