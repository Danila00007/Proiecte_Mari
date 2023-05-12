package com.ibm.practica.cinema.controller;

import com.ibm.practica.cinema.dto.AddUserRequestDTO;
import com.ibm.practica.cinema.dto.AddUserResponseDTO;
import com.ibm.practica.cinema.dto.BookingRequestDTO;
import com.ibm.practica.cinema.dto.BookingResponseDTO;
import com.ibm.practica.cinema.dto.EditUserRequestDTO;
import com.ibm.practica.cinema.dto.EditUserResponseDTO;
import com.ibm.practica.cinema.dto.GetMovieDTO;
import com.ibm.practica.cinema.entity.User;
import com.ibm.practica.cinema.service.MovieService;
import com.ibm.practica.cinema.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Log4j2 @RestController @RequestMapping("/user") public class CinemaController {

    @Autowired private UserService userService;

    @Autowired private MovieService movieService;

    @GetMapping("/getMovieList") public List<GetMovieDTO> getMovieList() {
        log.info("getMovieList(): A new Request for the movie list has been received. Processing...");
        List<GetMovieDTO> movieListFromService = movieService.getMovieList();
        log.info("CinemaController.getMovieList(): Processing has finished.");
        return movieListFromService;
    }

//    will handle in case we have enough time
//    @PostMapping("/addfavoritemovies") public List<Movie> addFavoriteMovie() {
//        // return the fav movie list that the user has after the new addition
//        return new ArrayList<>();
//    }

    @PostMapping("/adduser") public ResponseEntity<AddUserResponseDTO> createUser(@RequestBody @Valid AddUserRequestDTO addUserRequestDTO) {
        log.info("createUser(): A new add User Request has been received, with the following details: " + addUserRequestDTO);
        User user;
        AddUserResponseDTO response = new AddUserResponseDTO();
        try{
            user = userService.addUser(addUserRequestDTO);
        } catch (DuplicateKeyException ex) {
            log.error(ex);
            response.setMessage("Username already in use. Please choose another username and try again!");
           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.setUserName(user.getUserName());
        response.setPassword(user.getPassword());
        response.setMessage("User successfully created! Please make sure you replace you password at the first login");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser") public ResponseEntity<String> deleteUser(@RequestParam String userName, @RequestParam String userToBeDeleted) {
        log.info("editUser(): A delete User Request has been received for: " + userToBeDeleted);

        if(!userToBeDeleted.equals(userName)){
            return new ResponseEntity<>("You cannot delete any other user besides yourself.", HttpStatus.BAD_REQUEST);
        }

        boolean deleted = userService.deleteUser(userToBeDeleted);
        return new ResponseEntity<>(deleted ? "User deleted." : "User not found.", HttpStatus.OK);
    }

    //http://localhost:8080/user/editUser/{userName}?userName=test
    @PostMapping("/editUser/{userName}") public ResponseEntity<EditUserResponseDTO> editUser(@PathVariable String userName, @RequestBody @Valid EditUserRequestDTO editUserDTO) {
        // user can edit his own account
        log.info("editUser(): A new edit User Request has been received for: " + userName);
        EditUserResponseDTO response;
        try{
            response = userService.processEditUser(userName, editUserDTO);
        } catch (DuplicateKeyException ex) {
            log.error(ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }  catch (EntityNotFoundException ex) {
            log.error(ex);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
