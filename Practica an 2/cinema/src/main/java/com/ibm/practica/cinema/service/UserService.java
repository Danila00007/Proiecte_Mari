package com.ibm.practica.cinema.service;

import com.ibm.practica.cinema.dto.AddUserRequestDTO;
import com.ibm.practica.cinema.dto.EditUserRequestDTO;
import com.ibm.practica.cinema.dto.EditUserResponseDTO;
import com.ibm.practica.cinema.dto.GetUserDTO;
import com.ibm.practica.cinema.entity.User;
import com.ibm.practica.cinema.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    // here is where we will do all the processing for booking-related requests

    public List<GetUserDTO> getAllUsers(){
//        List<GetUserDTO> finalList = new ArrayList<>();
//        for (User user :userRepository.getAllActiveUsers()) {
//            GetUserDTO dto = new GetUserDTO(user.getUserName(),user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
//            finalList.add(dto);
//        }
//        return finalList;
        return userRepository.getAllActiveUsers()
            .stream()
            .map(user -> GetUserDTO.builder()
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build())
            .collect(Collectors.toList());

    }

    public EditUserResponseDTO processEditUser(String userName, EditUserRequestDTO request){
        log.info("processEditUser(): processing editing User Request...");
        EditUserResponseDTO responseDTO = new EditUserResponseDTO();

        // we search for the userName in the DB
        Optional<User> userInDB = userRepository.findByUserName(userName);

        if(userInDB.isEmpty()){
            throw new EntityNotFoundException("User making the changes not found");
        }
        if (StringUtils.isNotBlank(request.getUserName())){
            Optional<User> newUserNameID = userRepository.findByUserName(request.getUserName());
            if(newUserNameID.isPresent()){
                throw new DuplicateKeyException("Desired username already in the DB");
            } else {
                userInDB.get().setUserName(request.getUserName());
            }
        }
        if (StringUtils.isNotBlank(request.getLastName())){
            userInDB.get().setLastName(request.getLastName());
        }
        if (StringUtils.isNotBlank(request.getFirstName())){
            userInDB.get().setFirstName(request.getFirstName());
        }
        if (StringUtils.isNotBlank(request.getPassword())){
            userInDB.get().setPassword(request.getPassword());
        }
        if (StringUtils.isNotBlank(request.getEmail())){
            userInDB.get().setEmail(request.getEmail());
        }

        // we will save the new user in the DB
        userRepository.save(userInDB.get());

        responseDTO.setEmail(userInDB.get().getEmail());
        responseDTO.setUserName(userInDB.get().getUserName());
        responseDTO.setLastName(userInDB.get().getLastName());
        responseDTO.setFirstName(userInDB.get().getFirstName());
        responseDTO.setPassword(userInDB.get().getPassword());
        log.info("processEditUser(): process ended.");
        return responseDTO;
    }

    public User addUser(AddUserRequestDTO request) throws DuplicateKeyException{
        log.info("addUser(): processing add User Request...");
        // we will build the necessary user entity

        Optional<User> userInDB = userRepository.findByUserName(request.getUserName());

        if(userInDB.isPresent()){
            throw new DuplicateKeyException("username already in the DB");
        }

        User userToBeInserted = User.builder() // returns a new instance of User
            .email(request.getEmail())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .userName(request.getUserName())
            .password("aiu213knda=am2!")
            .isActive(true)
            .build();

        User fromDB = userRepository.save(userToBeInserted);

        log.info("addUser(): process ended.");
    return fromDB;
    }

    public boolean deleteUser(String userName) {
        log.info("deleteUser(): processing delete User Request...");

        Optional<User> userToBeDeleted = userRepository.findByUserName(userName);

        if(userToBeDeleted.isEmpty()){
            return false;
        }

        userRepository.delete(userToBeDeleted.get());
        log.info("deleteUser(): username: " + userName + " successfully deleted.");
        return true;
    }
}

