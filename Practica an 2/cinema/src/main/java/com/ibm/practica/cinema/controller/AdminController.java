package com.ibm.practica.cinema.controller;

import com.ibm.practica.cinema.dto.AddMovieRequestDTO;
import com.ibm.practica.cinema.dto.AddMovieResponseDTO;
import com.ibm.practica.cinema.dto.EditUserRequestDTO;
import com.ibm.practica.cinema.dto.EditUserResponseDTO;
import com.ibm.practica.cinema.dto.GetUserDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;


    @PostMapping("/addMovie")
    public ResponseEntity addMovieToCinemaList(@RequestBody @Valid AddMovieRequestDTO request){
        log.info("addMovieToCinemaList(): A new add Movie Request has been received, with the following details: " + request);
        AddMovieResponseDTO response = new AddMovieResponseDTO();
        try{
            response = movieService.addMovie(request);
        } catch (DuplicateKeyException ex) {
            log.error(ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public List<GetUserDTO> getAllActiveUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteuser") public ResponseEntity<String> deleteUser(@RequestParam String userToBeDeleted) {
        log.info("editUser(): A delete User Request has been received for: " + userToBeDeleted);

        boolean deleted = userService.deleteUser(userToBeDeleted);
        return new ResponseEntity<>(deleted ? "User deleted." : "User not found.", HttpStatus.OK);
    }

    @PostMapping("/editUser/{userName}") public ResponseEntity<EditUserResponseDTO> editUser(@PathVariable String userName, @RequestBody
    @Valid EditUserRequestDTO editUserDTO) {
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
