package com.account.service.impl;

import com.account.AccountRequest;
import com.account.AccountResponse;
import com.account.entity.Account;
import com.account.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountManagementService {

    private AccountRepository accountRepository;

    public void createAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        // validate request
        if (validateRequest(request, responseObserver)) return;

        Account account = new Account();
        account.setAccountId(request.getAccountId());
        account.setName(request.getName());
        account.setAccountType(request.getAccountType());
        account.setBalance(BigDecimal.valueOf(request.getBalance()));
        account.setPhoneNumber(request.getPhoneNumber());
        account.setEmail(request.getEmail());
        account.setAccountStatus(Boolean.valueOf(request.getAccountStatus()));
        account.setAddress(request.getAddress());
        accountRepository.save(account);


        AccountResponse response = AccountResponse.newBuilder()
                .setAccountId(account.getAccountId())
                .setName(account.getName())
                .setAccountType(account.getAccountType())
                .setBalance(account.getBalance().doubleValue())
                .setPhoneNumber(account.getPhoneNumber())
                .setEmail(account.getEmail())
                .setAccountStatus(account.getAccountStatus() ? "ACTIVE" : "INACTIVE")
                .setAddress(account.getAddress())
                .setStatus("SUCCESS")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private static boolean validateRequest(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        if (request.getAccountId().isEmpty()) {
            responseObserver.onError(new IllegalArgumentException("Account ID cannot be null or empty"));
            return true;
        }

        if (request.getName().isEmpty()) {
            responseObserver.onError(new IllegalArgumentException("Name cannot be null or empty"));
            return true;
        }
        if (request.getAccountType().isEmpty()) {
            responseObserver.onError(new IllegalArgumentException("Account Type cannot be null or empty"));
            return true;
        }
        if (request.getBalance() <= 0) {
            responseObserver.onError(new IllegalArgumentException("Balance must be greater than 0"));
            return true;
        }
        return false;
    }

    public void getAccount() {
        // Logic to retrieve an account
    }

    public void updateAccount() {
        // Logic to update an account
    }

    public void deleteAccount() {
        // Logic to delete an account
    }
}
