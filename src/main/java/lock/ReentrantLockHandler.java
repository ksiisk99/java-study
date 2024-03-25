package lock;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockHandler {

    private final ConcurrentLinkedDeque startThreads = new ConcurrentLinkedDeque();
    private final ConcurrentLinkedDeque endThreads = new ConcurrentLinkedDeque();
    private final ReentrantLock reentrantLock;

    public ReentrantLockHandler(boolean fair) {
        this.reentrantLock = new ReentrantLock(fair);
    }

    public void lock(int num) {
        startThreads.offerLast(num);

        reentrantLock.lock();

        startThreads.offerLast(num);

        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public ConcurrentLinkedDeque getStartThreads() {
        return startThreads;
    }

    public ConcurrentLinkedDeque getEndThreads() {
        return endThreads;
    }
}
