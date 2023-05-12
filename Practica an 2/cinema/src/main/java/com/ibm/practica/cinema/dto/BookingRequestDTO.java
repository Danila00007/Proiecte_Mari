package com.ibm.practica.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequestDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String movieName;

    @NotNull
    private LocalDateTime bookingDate;

    @Min(value = 1)
    @Max(value = 9)
    private int noOfPeople;

}
