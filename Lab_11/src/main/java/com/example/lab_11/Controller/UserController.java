package com.example.lab_11.Controller;


import com.example.lab_11.Api.ApiResponse;
import com.example.lab_11.Model.User;
import com.example.lab_11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity searchUserByUsername(@PathVariable String username){
        return ResponseEntity.status(200).body(userService.getByUsername(username));
    }

    @GetMapping("/search/date/{date}")
    public ResponseEntity searchByDate(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(userService.getAllUserByRegistrationDate(date));
    }























}
