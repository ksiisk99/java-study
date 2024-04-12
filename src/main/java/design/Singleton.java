package design;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Singleton {
    private Singleton() {
    }

    public static class Holder {
        private static final Singleton INSTANCE = new Singleton();

        public static Singleton getSingleton() {
            return INSTANCE;
        }

        private Holder() {
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        IntStream.range(0, 10)
                .parallel()
                .forEach(n -> executorService.submit(() -> {
                    Singleton singleton = Holder.getSingleton();
                    System.out.println(singleton);
                }));
        executorService.shutdown();
    }
}
