package com.ibm.practica.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="price")
    private float price;

    @NotNull
    @Column(name="open_seats")
    private int openSeats;

    @NotNull
    @Column(name="running_hours")
    private LocalTime runningHours;

    @NotNull
    @Column(name="is_3D")
    private boolean is3D;

    @NotNull
    @Column(name="movie_hall")
    private String movieHall;

    @NotNull
    @Column(name="is_movie_playing_now")
    private boolean isMoviePlayingNow;
    //comment

}
