package lock;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ReentrantLockHandlerTest {
    private static final int THREAD_COUNT = 1000;

    @Test
    void fairLock() throws InterruptedException {
        // given
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        ReentrantLockHandler reentrantLockHandler = new ReentrantLockHandler(true);
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

        // when
        IntStream.range(0, THREAD_COUNT)
                .forEach(i -> {
                    executorService.submit(() -> {
                        reentrantLockHandler.lock(i);
                        countDownLatch.countDown();
                    });
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        countDownLatch.await();

        // then
        List<Integer> startThreads = reentrantLockHandler.getStartThreads()
                .stream()
                .mapToInt(i -> (Integer)i)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> endThreads = reentrantLockHandler.getEndThreads()
                .stream()
                .mapToInt(i -> (Integer)i)
                .boxed()
                .collect(Collectors.toList());

        assertThat(startThreads.equals(endThreads))
                .isTrue();

    }

    @Test
    void unfairLock() throws InterruptedException {
        // given
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        ReentrantLockHandler reentrantLockHandler = new ReentrantLockHandler(false);
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

        // when
        IntStream.range(0, THREAD_COUNT)
                .forEach(i -> {
                    executorService.submit(() -> {
                        reentrantLockHandler.lock(i);
                        countDownLatch.countDown();
                    });
                });
        countDownLatch.await();

        // then
        List<Integer> startThreads = reentrantLockHandler.getStartThreads()
                .stream()
                .mapToInt(i -> (Integer)i)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> endThreads = reentrantLockHandler.getEndThreads()
                .stream()
                .mapToInt(i -> (Integer)i)
                .boxed()
                .collect(Collectors.toList());

        assertThat(startThreads.equals(endThreads))
                .isFalse();
    }
}
