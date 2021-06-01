package com.HowlingWolfe.HowlingWolfe.services;

import com.HowlingWolfe.HowlingWolfe.models.Account;
import com.HowlingWolfe.HowlingWolfe.repositories.AccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountsService {

    AccountsRepo accountsRepo;

    @Autowired
    public AccountsService(AccountsRepo accountsRepo) {
        this.accountsRepo = accountsRepo;
    }

    public String getPass(String username){
        Account account = accountsRepo.findByUsername(username);
        return account.getPassword();
    }
}
