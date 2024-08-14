package com.example.lab_11.Service;

import com.example.lab_11.Api.ApiException;
import com.example.lab_11.Model.User;
import com.example.lab_11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRegistrationDate(user.getRegistrationDate());
        userRepository.save(u);
    }

    public void deleteUser(Integer id) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public List<User> getAllUserByRegistrationDate(LocalDate registrationDate) {
        List<User> users = userRepository.findUsersByRegistrationDate(registrationDate);
        if (users == null){
            throw new ApiException("There is no Users with the given registration date");
        }
        return users;
    }

}
