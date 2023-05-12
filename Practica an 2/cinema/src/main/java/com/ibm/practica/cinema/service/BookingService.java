package com.ibm.practica.cinema.service;

import com.ibm.practica.cinema.dto.BookingRequestDTO;
import com.ibm.practica.cinema.dto.BookingResponseDTO;
import com.ibm.practica.cinema.entity.Booking;
import com.ibm.practica.cinema.entity.Movie;
import com.ibm.practica.cinema.entity.User;
import com.ibm.practica.cinema.repository.BookingRepository;
import com.ibm.practica.cinema.repository.MovieRepository;
import com.ibm.practica.cinema.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public BookingResponseDTO processBookingRequest(BookingRequestDTO bookingRequest){
        log.info("processBookingRequest(): processing booking Request...");
        // start building the response
        BookingResponseDTO response = new BookingResponseDTO();

        // does the userName exist?
        Optional<User> userInDB = userRepository.findByUserName(bookingRequest.getUserName());
        if(userInDB.isEmpty()){
            log.error("processBookingRequest(): username invalid.Process will end here");
            return response;
        }

        // does the movieName exist
        Optional<Movie> movieInDB = movieRepository.findByName(bookingRequest.getMovieName());
        if(movieInDB.isEmpty()){
            log.error("processBookingRequest(): we are not playing movie " + bookingRequest.getMovieName() + ".Process will end here");
            return response;
        }

        // is the date ok for the movie
        if(!movieInDB.get().getRunningHours().equals(bookingRequest.getBookingDate().toLocalTime())){
            log.info("processBookingRequest(): booking time is different than the time the movie is playing .Process will end here");
            return response;
        }

        // does the movieHall have enough free seats
        if (movieInDB.get().getOpenSeats() < bookingRequest.getNoOfPeople()){
            log.info("processBookingRequest(): movie does not have enough room to accomodate the booking.Process will end here");
            return response;
        }

        Booking booking = new Booking();
        booking.setBookingDate(bookingRequest.getBookingDate());
        booking.setNoOfPeople(bookingRequest.getNoOfPeople());
        booking.setUser(userInDB.get());
        booking.setMovieID(movieInDB.get().getID());
        booking.setBookingConfirmed(true);

        Booking saved = bookingRepository.save(booking);

        response.setBookingID(saved.getID());
        response.setNoOfPeople(bookingRequest.getNoOfPeople());
        response.setMovieName(bookingRequest.getMovieName());
        response.setUserName(saved.getUser().getUserName());
        response.setBookingDate(bookingRequest.getBookingDate());
        response.setBookingDate(bookingRequest.getBookingDate());
        response.setBookingConfirmed(true);

        // we return the response to the caller
        log.info("processBookingRequest(): process ended.");
        return response;
    }

    public boolean processDeleteBooking(int bookingID){
        log.info("processDeleteBooking(): processing deleteBooking Request...");

        // search for the bookingID in the DB
        // if booking exists => delete it
        // if booking does not exist => return false for error
        return (bookingID > 0 && bookingID < 100) ;
    }

}
