package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/accounts")
public class AccountsController {

    AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<String> getPassword(@PathVariable String username){
        String response = accountsService.getPass(username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
