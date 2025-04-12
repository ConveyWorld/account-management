package com.account.grpc;

import com.account.AccountManagementServiceGrpc;
import com.account.AccountRequest;
import com.account.AccountResponse;
import com.account.service.impl.AccountManagementService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class AccountManagementGrpc  extends AccountManagementServiceGrpc.AccountManagementServiceImplBase {

    private final AccountManagementService accountManagementService;

    @Override
    public void createAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
//        accountManagementService.createAccount(request, responseObserver);
    }

    @Override
    public void getAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        super.getAccount(request, responseObserver);
    }

    @Override
    public void updateAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        super.updateAccount(request, responseObserver);
    }

    @Override
    public void deleteAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        super.deleteAccount(request, responseObserver);
    }
}
