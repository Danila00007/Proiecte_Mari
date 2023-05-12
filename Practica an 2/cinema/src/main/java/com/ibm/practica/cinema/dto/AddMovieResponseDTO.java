package com.ibm.practica.cinema.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddMovieResponseDTO {

    private String name;
    private float price;
    private int openSeats;
    private LocalTime runningHours;
    private boolean is3D;
    private String movieHall;
    private boolean isMoviePlayingNow;

}
