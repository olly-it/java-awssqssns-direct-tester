package it.olly.awssqssns.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SqsBlockingSubmissionPolicy implements RejectedExecutionHandler {
    private final long timeout;

    public SqsBlockingSubmissionPolicy(long timeout) {
        this.timeout = timeout;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            BlockingQueue queue = executor.getQueue();
            if (!queue.offer(r, this.timeout, TimeUnit.MILLISECONDS)) {
                throw new RejectedExecutionException("Timeout");
            }
        } catch (InterruptedException e) {
            Thread.currentThread()
                    .interrupt();
        }
    }
}
