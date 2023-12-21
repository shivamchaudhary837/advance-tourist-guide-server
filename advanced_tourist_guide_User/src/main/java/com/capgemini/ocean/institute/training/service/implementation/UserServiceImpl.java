package com.capgemini.ocean.institute.training.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.ocean.institute.training.dto.UserDto;
import com.capgemini.ocean.institute.training.entites.User;
import com.capgemini.ocean.institute.training.exception.EmailIdAlreadyExistsException;
import com.capgemini.ocean.institute.training.jwt.JwtTokenProvider;
import com.capgemini.ocean.institute.training.repository.UserRepository;
import com.capgemini.ocean.institute.training.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (this.userRepository.findByEmailId(userDto.getEmailId()).isPresent()) {
            throw new EmailIdAlreadyExistsException();
        }
        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        User savedUser = this.userRepository.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow();

        user.setName(userDto.getName());
        
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());
        

        User updatedUser = this.userRepository.save(user);

        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow();

        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public ResponseEntity<?> loginUser(UserDto userDto) {
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmailId(), 
                    		userDto.getPassword()));
            String token = jwtTokenProvider.createToken(authentication);

            UserDto user = this.getUserByEmail(userDto.getEmailId());
            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getUserId());
            response.put("name", user.getName());
            response.put("token", token);
            response.put("role", user.getRole());
           
            // Add other fields as needed

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid username/password supplied");
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return this.modelMapper.map(users, List.class);
    }

    @Override
    public List<UserDto> getAllUsersByRole(String role) {
        List<User> users = this.userRepository.findAllByRole(role);
        return this.modelMapper.map(users, List.class);
    }

    private UserDto getUserByEmail(String emailId) {
        User user = this.userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("User not found with email id: " + emailId));
        return this.modelMapper.map(user, UserDto.class);
    }

	@Override
	public UserDto feeStatusUpdate(Integer userId, Integer status) {
		// TODO Auto-generated method stub
		return null;
	}
}
