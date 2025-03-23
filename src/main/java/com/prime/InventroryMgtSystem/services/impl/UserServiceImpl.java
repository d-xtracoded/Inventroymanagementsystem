package com.prime.InventroryMgtSystem.services.impl;

import com.prime.InventroryMgtSystem.dtos.LoginRequest;
import com.prime.InventroryMgtSystem.dtos.RegistrationRequest;
import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.UserDTO;
import com.prime.InventroryMgtSystem.enums.UserRole;
import com.prime.InventroryMgtSystem.exceptions.InvalidCredentialException;
import com.prime.InventroryMgtSystem.exceptions.NotFoundExecption;
import com.prime.InventroryMgtSystem.models.User;
import com.prime.InventroryMgtSystem.reposit.UserRepository;
import com.prime.InventroryMgtSystem.security.JwtUtils;
import com.prime.InventroryMgtSystem.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final ModelMapper modelMapper;
    private ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    @Override
    public Response registerUser(RegistrationRequest registrationRequest) {
        //return null;
        UserRole role= UserRole.MANAGER;
        if (registrationRequest.getRole() !=null){
            role=registrationRequest.getRole();

        }
    User userTosave = User.builder()
            .name(registrationRequest.getName())
            .email(registrationRequest.getEmail())
            .password(passwordEncoder.encode(registrationRequest.getPassword()))
            .role(role).build();
        userRepository.save(userTosave);

        return Response.builder()
                .status(200)
                .message("User was succesfuuly created")
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        User user= userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new NotFoundExecption("Email not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialException("Password Does Not Match");
        }
        String token = jwtUtils.generatetoken(user.getEmail());


        return Response.builder()
                .status(200)
                .message("User Logged in successfully")
                .role(user.getRole())
                .token(token)
                .expirationTime("6 months")
                .build();
    }

    @Override
    public Response getAllUsers() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        users.forEach(user -> user.setTransaction(null));

        List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}
                .getType());

        return Response.builder()
                .status(200)
                .message("success")
                .users(userDTOS)
                .build();
    }

    @Override
    public Response getUserbyID(Long id) {
        return null;
    }

    @Override
    public User getCurrentLoggedInUser() {
        return null;
    }

    @Override
    public Response updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public Response deleteUser(Long id) {
        return null;
    }

    @Override
    public Response getUserTransaction(Long id) {
        return null;
    }
}
