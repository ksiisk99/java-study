package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 결과
 * pool-1-thread-1 set: 1
 * pool-1-thread-2 get: null
 * pool-1-thread-1 get: 1
 */
public class ThreadLocalHandler {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        IntStream.range(1, THREAD_COUNT)
                .forEach(i -> {
                    executorService.submit(() -> {
                        threadLocal.set(i);
                        System.out.println(Thread.currentThread().getName() + " set: " + i);
                    });
                });

        IntStream.range(0, THREAD_COUNT)
                .forEach(i -> {
                    executorService.submit(() -> {
                        System.out.println(Thread.currentThread().getName() + " get: " + threadLocal.get());
                        threadLocal.remove(); //스레드 풀에 반환되는 스레드는 값을 비워줘야 다른 스레드에서 사용할 때 문제가 발생하지 않음
                    });
                });

        executorService.shutdown(); //ExecutorService는 기본적으로 데몬 스레드가 아닌 백그라운드 스레드를 사용하기 때문에 모든 스레드가 종료되지 않으면 프로그램이 종료되지 않음
    }

}
