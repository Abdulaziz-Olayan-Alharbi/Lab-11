package com.example.lab_11.Repository;

import com.example.lab_11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    @Query("select u from User u where u.username = ?1")
    User getByUsername(String username);
    List<User> findUsersByRegistrationDate(LocalDate registrationDate);
}
