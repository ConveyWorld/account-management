package com.account.service.impl;

import com.account.AccountRequest;
import com.account.AccountResponse;
import com.account.AllAccountsResponse;
import com.account.entity.Account;
import com.account.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountManagementService {

    private final AccountRepository accountRepository;

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
        account.setAccountStatus(request.getAccountStatus());
        account.setAddress(request.getAddress());
        accountRepository.save(account);


        AccountResponse response = AccountResponse.newBuilder()
                .setAccountId(account.getAccountId())
                .setName(account.getName())
                .setAccountType(account.getAccountType())
                .setBalance(account.getBalance().doubleValue())
                .setPhoneNumber(account.getPhoneNumber())
                .setEmail(account.getEmail())
                .setAccountStatus(account.getAccountStatus())
                .setAddress(account.getAddress())
                .setStatus("CREATED")
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

    public void getAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        log.info("request: {}", request);

        Account account = accountRepository.findById(request.getAccountId()).orElse(null);
        if(account == null) {
            responseObserver.onNext(AccountResponse.newBuilder().setStatus("No data found!").build());
            responseObserver.onCompleted();
            return;
        }
        AccountResponse response = AccountResponse.newBuilder()
                .setAccountId(account.getAccountId())
                .setName(account.getName())
                .setAccountType(account.getAccountType())
                .setBalance(account.getBalance().doubleValue())
                .setPhoneNumber(account.getPhoneNumber())
                .setEmail(account.getEmail())
                .setAccountStatus(account.getAccountStatus())
                .setAddress(account.getAddress())
                .setStatus("SUCCESS")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void updateAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        // Logic to update an account
    }

    public void deleteAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        String accountId = request.getAccountId();
        Account account = accountRepository.findById(accountId).orElse(null);
        if(account == null) {
            responseObserver.onNext(AccountResponse.newBuilder().setStatus("No data found!").build());
            responseObserver.onCompleted();
            return;
        }
        accountRepository.delete(account);
        AccountResponse response = AccountResponse.newBuilder()
                .setStatus("DELETED")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void findAllAccounts(AccountRequest request, StreamObserver<AllAccountsResponse> responseObserver) {
        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) {
            List<AccountResponse> accountList = accounts.stream()
                    .map(acc -> AccountResponse.newBuilder()
                            .setAccountId(acc.getAccountId())
                            .setName(acc.getName())
                            .setAccountType(acc.getAccountType())
                            .setBalance(acc.getBalance().doubleValue())
                            .setPhoneNumber(acc.getPhoneNumber())
                            .setEmail(acc.getEmail())
                            .setAccountStatus(acc.getAccountStatus())
                            .setAddress(acc.getAddress())
                            .build()).toList();
            
            AllAccountsResponse response = AllAccountsResponse.newBuilder()
                    .addAllAccounts(accountList)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

    }
}
