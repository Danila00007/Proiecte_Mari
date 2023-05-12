package com.ibm.practica.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {

    private int bookingID;
    private String movieName;
    private LocalDateTime bookingDate;
    private int noOfPeople;
    private boolean bookingConfirmed;
    private String userName;

}
