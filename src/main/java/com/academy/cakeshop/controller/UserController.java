package com.academy.cakeshop.controller;

import com.academy.cakeshop.dto.*;
import com.academy.cakeshop.service.BankAccountService;
import com.academy.cakeshop.service.ContractService;
import com.academy.cakeshop.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    private final UserService userService;
    private final ContractService contractService;
    private final BankAccountService bankAccountService;

    public UserController(UserService userService, ContractService contractService, BankAccountService bankAccountService) {
        this.userService = userService;
        this.contractService = contractService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam(required = false) String lastName) {
        if (lastName == null) {
            List<UserResponse> users = userService.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            List<UserResponse> userResponseList = userService.getByLastName(lastName);
            return new ResponseEntity<>(userResponseList, HttpStatus.OK);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getById(@NotNull(message = "Required field!")
                                                @Min(value = 1, message = "No negative values allowed!")
                                                @PathVariable(name = "userId")
                                                Long id) {
        UserResponse userResponse = userService.getById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
        userService.create(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO how to do it with requestbody?
//    @PatchMapping(value = {"/userId"})
//    public ResponseEntity<?> updateFirstName(@RequestBody UserRequest userRequest){
//        int updatedRows = userService.updateUserFirstName(userRequest.firstName(), userRequest.);
//        if(updatedRows > 0) {
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping(value = {"/edited/lastName"})
//    public ResponseEntity<?> updateLastName(@RequestParam @NotBlank(message = "Required field!") String lastName,
//                                             @RequestParam @NotBlank(message = "Required field!")
//                                             @Min(value = 1, message = "No negative values allowed!") Long id){
//        int updatedRows = userService.updateUserLastName(lastName, id);
//        if(updatedRows > 0) {
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PatchMapping
    public ResponseEntity<?> updateById(@RequestBody UserUpdateDTO userUpdateDTO){
        int updatedRows = userService.updateById(userUpdateDTO);
        if(updatedRows > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable @NotNull(message = "Required field!")
                                            @Min(value = 1, message = "No negative values allowed!") Long id) {
        userService.deleteUserByID(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/contracts/{userId}")
    public ResponseEntity<List<ContractResponse>> getContractByUserId(@NotNull(message = "Required field!")
                                                              @Min(value = 1, message = "No negative values allowed")
                                                              @PathVariable(name = "userId")
                                                              Long id) {
        List<ContractResponse> contractResponseList = contractService.getByUserID(id);
        return new ResponseEntity<>(contractResponseList, HttpStatus.OK);
    }

    @GetMapping("/bankAccounts/{userId}")
    public ResponseEntity<BankAccountResponse> getByUserId(@NotNull(message = "Required field!")
                                                           @Min(value = 1, message = "No negative values allowed")
                                                           @RequestParam(name = "userId")
                                                           Long userId) {
        BankAccountResponse bankAccountResponse = bankAccountService.getByUserID(userId);
        return new ResponseEntity<>(bankAccountResponse, HttpStatus.OK);
    }

    @PatchMapping("/bankAccounts/{userId}")
    public ResponseEntity<?> updateAccount(@RequestBody BankAccountRequest bankAccountRequest,
                                           @RequestParam(name = "userId") @NotNull(message = "Required field!")
                                           @Min(value = 1, message = "No negative values allowed!") Long userId) {
        int updatedRows = bankAccountService.updateBankAccount(bankAccountRequest, userId);
        if (updatedRows > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}