package com.account.grpc;

import com.account.AccountManagementServiceGrpc;
import com.account.AccountRequest;
import com.account.AccountResponse;
import com.account.AllAccountsResponse;
import com.account.service.impl.AccountManagementService;
import com.account.virtual_thread.VirtualThreadExecutor;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class AccountManagementGrpc  extends AccountManagementServiceGrpc.AccountManagementServiceImplBase {

    private final AccountManagementService accountManagementService;
    private final VirtualThreadExecutor virtualThreadExecutor;

    @Override
    public void createAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        virtualThreadExecutor.executeInVirtualThread("thread: ", ()-> accountManagementService.createAccount(request, responseObserver));
    }

    @Override
    public void getAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        virtualThreadExecutor.executeInVirtualThread("thread: ", () ->accountManagementService.getAccount(request, responseObserver));
    }

    @Override
    public void updateAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        accountManagementService.updateAccount(request, responseObserver);
    }

    @Override
    public void deleteAccount(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        virtualThreadExecutor.executeInVirtualThread("thread: ", ()-> accountManagementService.deleteAccount(request, responseObserver));
    }

    @Override
    public void findAllAccounts(AccountRequest request, StreamObserver<AllAccountsResponse> responseObserver) {
        virtualThreadExecutor.executeInVirtualThread("thread: ", () -> accountManagementService.findAllAccounts(request, responseObserver));
    }
}
