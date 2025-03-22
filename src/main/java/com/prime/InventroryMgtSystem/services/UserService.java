package com.prime.InventroryMgtSystem.services;

import com.prime.InventroryMgtSystem.dtos.LoginRequest;
import com.prime.InventroryMgtSystem.dtos.RegistrationRequest;
import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.UserDTO;
import com.prime.InventroryMgtSystem.models.User;

public interface UserService {

    Response registerUser(RegistrationRequest registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    Response getUserbyID(Long id);
    User getCurrentLoggedInUser();
    Response updateUser(Long id, UserDTO userDTO);
    Response deleteUser(Long id);
    Response getUserTransaction(Long id);



}
