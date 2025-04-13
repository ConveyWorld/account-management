package com.account.virtual_thread;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class VirtualThreadExecutor {
    // unique thread names
    private static final AtomicInteger counter = new AtomicInteger(0);

    public void executeInVirtualThread( String threadName, RunnableWithException task) {
        String threadNameIndex = threadName + counter.incrementAndGet();
        Thread thread = Thread.ofVirtual().name(threadNameIndex)
                .unstarted(() -> {
                });

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            thread.start();
            try {
                task.run();
                log.info(" Thread is virtual? {} : [{}] task completed successfully",Thread.currentThread().isVirtual(), threadNameIndex);
            } catch (Exception ex) {
                log.error(" [{}] Error executing task : {}", threadNameIndex, ex.getMessage());
                throw new RuntimeException(ex.getMessage());
            }
        });
        future.join();
    }

    public interface RunnableWithException {
        void run() throws Exception;
    }
}