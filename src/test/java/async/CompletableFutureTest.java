package async;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CompletableFutureTest {
    @Test
    @DisplayName("반환 타입이 void")
    void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        future.get();
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    @Test
    @DisplayName("반환 타입이 있음")
    void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->
                "Thread: " + Thread.currentThread().getName());

        System.out.println(future.get());
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    @Test
    @DisplayName("반환 값을 받아서 다른 값으로 반환")
    void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->
                "Thread: " + Thread.currentThread().getName()
        ).thenApply(String::toUpperCase);

        System.out.println(future.get());
    }

    @Test
    @DisplayName("반환 값을 받아서 사용 후 반환하지 않음")
    void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() ->
                "Thread: " + Thread.currentThread().getName()
        ).thenAccept(System.out::println);

        future.get();
    }

    @Test
    @DisplayName("반환 값을 받지 않고 다른 작업 함")
    void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() ->
                "Thread: " + Thread.currentThread().getName()
        ).thenRun(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });

        future.get();
    }

    @Test
    @DisplayName("두 작업을 이어서 실행하도록 조합함. 앞선 작업의 결과를 받아서 사용")
    void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> helloworld = hello.thenCompose(r -> CompletableFuture.supplyAsync(() -> r + "world"));

        System.out.println(helloworld.get());
    }

    @Test
    @DisplayName("각각의 작업들이 독립적으로 실행하고 얻어진 두 결과를 조합함.")
    void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<String> helloworld = hello.thenCombine(world, (h, w) -> h + w);
        System.out.println(helloworld.get());
    }

    @Test
    @DisplayName("여러 작업들을 동시에 실행하고, 모든 작업 결과에 콜백을 실행함")
    void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "World";
        });

        List<CompletableFuture<String>> futures = List.of(hello, world);

        CompletableFuture<List<String>> result = CompletableFuture.allOf(
                        futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        result.get().forEach(System.out::println);
    }

    @Test
    @DisplayName("여러 작업 중 가장 빨리 끝난 1개의 작업만 콜백이 실행함")
    void anyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "World";
        });

        CompletableFuture<Void> future = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        future.get();
    }

    @ParameterizedTest
    @ValueSource(booleans =  {true, false})
    @DisplayName("예외 처리")
    void exceptionally(boolean doThrow) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (doThrow) {
                throw new IllegalArgumentException("Invalid Argument");
            }

            return "Thread: " + Thread.currentThread().getName();
        }).exceptionally(Throwable::getMessage);

        System.out.println(future.get());
    }
}
