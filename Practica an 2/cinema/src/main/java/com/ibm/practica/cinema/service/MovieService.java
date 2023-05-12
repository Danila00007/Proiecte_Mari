package com.ibm.practica.cinema.service;

import com.ibm.practica.cinema.dto.AddMovieRequestDTO;
import com.ibm.practica.cinema.dto.AddMovieResponseDTO;
import com.ibm.practica.cinema.dto.AddUserRequestDTO;
import com.ibm.practica.cinema.dto.GetMovieDTO;
import com.ibm.practica.cinema.entity.Movie;
import com.ibm.practica.cinema.entity.User;
import com.ibm.practica.cinema.repository.MovieRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public AddMovieResponseDTO addMovie(AddMovieRequestDTO request) throws DuplicateKeyException {
        log.info("addMovie(): processing add Movie Request...");
        // we will build the necessary user entity

        Optional<Movie> userInDB = movieRepository.findByName(request.getName());

        if(userInDB.isPresent()){
            throw new DuplicateKeyException("Movie with same name already in the DB");
        }

        Movie movieToBeInserted = Movie.builder() // returns a new instance of User
            .movieHall(request.getMovieHall())
            .is3D(request.is3D())
            .name(request.getName())
            .openSeats(request.getMaxSeats())
            .price(request.getPrice())
            .runningHours(request.getRunningHours())
            .isMoviePlayingNow(false)
            .build();

        Movie fromDB = movieRepository.save(movieToBeInserted);

        AddMovieResponseDTO responseDTO = new AddMovieResponseDTO();
        responseDTO.set3D(fromDB.is3D());
        responseDTO.setName(fromDB.getName());
        responseDTO.setMovieHall(fromDB.getMovieHall());
        responseDTO.setPrice(fromDB.getPrice());
        responseDTO.setOpenSeats(fromDB.getOpenSeats());
        responseDTO.setRunningHours(fromDB.getRunningHours());
        responseDTO.setMoviePlayingNow(fromDB.isMoviePlayingNow());

        log.info("addMovie(): process ended.");
        return responseDTO;
    }

    public List<GetMovieDTO> getMovieList(){
        log.info("getMovieList(): fetching movie list...");

        return movieRepository.findAll().stream()
            .map(m -> GetMovieDTO.builder()
                .name(m.getName()).price(m.getPrice()).openSeats(m.getOpenSeats()).runningHours(m.getRunningHours())
                .is3D(m.is3D()).movieHall(m.getMovieHall()).isMoviePlayingNow(m.isMoviePlayingNow()).build())
            .collect(Collectors.toList());
    }

}
