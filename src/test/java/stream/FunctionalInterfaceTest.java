package stream;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FunctionalInterfaceTest {
    @Test
    void runnableTest() {
        Runnable runnable = () -> {
            System.out.println("SANG");
            System.out.println("IN");
        };

        runnable.run();

        Thread thread = new Thread(runnable);
        thread.run();
    }

    @Test
    @DisplayName("매개 변수 값을 받아 리턴 없이 처리")
    void consumerTest() {
        Consumer<Integer> consumer = n -> System.out.println(n);
        consumer.accept(1);

        BiConsumer<Integer, Integer> biConsumer = (n1, n2) -> System.out.println(n1 + n2);
        biConsumer.accept(1, 1);
    }

    @Test
    @DisplayName("아무 매개 변수 값 없이 리턴")
    void supplierTest() {
        Supplier<Integer> supplier = () -> 1;
        supplier.get();
    }

    @Test
    @DisplayName("매개 변수 값을 받아서 처리 후 리턴")
    void functionTest() {
        Function<Integer, String> function = t -> String.valueOf(t);
        System.out.println(function.apply(100));

        BiFunction<String, String, Integer> biFunction = (n1,n2) -> Integer.parseInt(n1) + Integer.parseInt(n2);
        System.out.println(biFunction.apply("10","20"));
    }
}
