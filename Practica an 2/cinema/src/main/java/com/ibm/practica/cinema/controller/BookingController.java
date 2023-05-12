package com.ibm.practica.cinema.controller;

import com.ibm.practica.cinema.dto.BookingRequestDTO;
import com.ibm.practica.cinema.dto.BookingResponseDTO;
import com.ibm.practica.cinema.service.BookingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PutMapping("/book") public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody @Valid BookingRequestDTO bookingRequest) {
        log.info("createBooking(): A new Booking Request has been received:" + bookingRequest);
        BookingResponseDTO response = bookingService.processBookingRequest(bookingRequest);
        log.info("createBooking(): Process ended.");
        return response.isBookingConfirmed() ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deletebooking/{bookingID}") public ResponseEntity<String> deleteBooking(@PathVariable int bookingID) {
        log.info("deleteBooking(): A new Delete Booking Request has been received for bookingID: " + bookingID);
        boolean deleted = bookingService.processDeleteBooking(bookingID);
        log.info("createBooking(): Process ended.");
        return deleted ? new ResponseEntity<>("Booking has been deleted", HttpStatus.OK) : new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
    }
}
