package com.capgemini.ocean.institute.training.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ocean.institute.training.dto.UserDto;
import com.capgemini.ocean.institute.training.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
  
    @PostMapping("/signup")
    public ResponseEntity<UserDto> createUser( @RequestBody UserDto userDto) {
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    @GetMapping("/usersByRole/{role}")
    public ResponseEntity<List<UserDto>> getAllUsersByRole(@PathVariable String role){
    	List<UserDto> userDtos = this.userService.getAllUsersByRole(role);
    	return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        return this.userService.loginUser(userDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    
    
    
   
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> viewAllUsers(){
        List<UserDto> userDtos = this.userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
    
    @PutMapping("/user/feeStatusUpdate/{userId}/{status}")
    public ResponseEntity<UserDto> feeStatusUpdate(@PathVariable Integer userId, @PathVariable Integer status){
    	UserDto userDto = this.userService.feeStatusUpdate(userId, status);
    	return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

}
