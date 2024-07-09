package com.academy.cakeshop.service;

import com.academy.cakeshop.dto.UserRequest;
import com.academy.cakeshop.dto.UserResponse;
import com.academy.cakeshop.dto.UserUpdateDTO;
import com.academy.cakeshop.enumeration.Role;
import com.academy.cakeshop.errorHandling.BusinessNotFound;
import com.academy.cakeshop.persistance.entity.User;
import com.academy.cakeshop.persistance.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse getById(Long id) {
        User user = userRepository.getReferenceById(id);
        if(user != null) {
            return new UserResponse(user.getFirstName(), user.getLastName(),
                    user.getEmail(), user.getRole().toString());
        } else {
            throw new BusinessNotFound("Userid: " + id + " not found!");
        }
    }

    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            List<UserResponse> userResponseList = new ArrayList<>();

            for (User user : users) {
                UserResponse userResponse = new UserResponse(
                        user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().toString());
                userResponseList.add(userResponse);
            }

            return userResponseList;
        } else {
            throw new BusinessNotFound("No users found!");
        }
    }

    public List<UserResponse> getByLastName(String lastName) {
        List<User> users = userRepository.findLastNameByLastNameLikeIgnoreCaseOrderByLastNameAsc(lastName);
        if (!users.isEmpty()) {
            List<UserResponse> userResponseList = new ArrayList<>();

            for (User user : users) {
                UserResponse userResponse = new UserResponse(
                        user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().toString());
                userResponseList.add(userResponse);
            }

            return userResponseList;
        } else {
            throw new BusinessNotFound("No users with last name: " + lastName + " found!");
        }
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public void create(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setUserName(userRequest.userName());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setEmail(userRequest.email());
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setAddress(userRequest.address());
        user.setRole(Role.getRoleFromString(userRequest.role()));
        userRepository.save(user);
    }

    public int updateUserFirstName(String firstName, Long id) {
        if (existsById(id)) {
            return userRepository.updateUserFirstNameById(firstName, id);
        } else {
            throw new BusinessNotFound("Userid: " + id + " not found!");
        }
    }

    public int updateUserLastName(String lastName, Long id) {
        if (existsById(id)) {
            return userRepository.updateUserLastNameById(lastName, id);
        } else {
            throw new BusinessNotFound("Userid: " + id + " not found!");
        }
    }

    public int updateUserEmailById(String email, Long id) {
        if (existsById(id)) {
            return userRepository.updateUserEmailById(email,id);
        } else {
            throw new BusinessNotFound("Userid: " + id + " not found!");
        }
    }

    public void deleteUserByID(Long id) {
        if (existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new BusinessNotFound("Userid: " + id + " not found!");
        }
    }

    public int updateById(UserUpdateDTO userUpdateDTO) {
        int updatedRows = 0;
        Long userId = userUpdateDTO.id();
        updatedRows += updateUserFirstName(userUpdateDTO.firstName(), userId);
        updatedRows += updateUserLastName(userUpdateDTO.lastName(), userId);
        updatedRows += updateUserEmailById(userUpdateDTO.email(), userId);
        return updatedRows;
    }
}