package com.ibm.practica.cinema.repository;

import com.ibm.practica.cinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // interacting with the user DB or table
    Optional<User> findByUserName(String username);

    List<User> getUsersByIsActive(boolean isActive);

    @Query(value = "select * from cinema_user where is_active=true", nativeQuery = true)
    List<User> getAllActiveUsers();
}
