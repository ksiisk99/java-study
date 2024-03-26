package lock.synchro;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
/**
 * 하나의 인스턴스를 서로 다른 스레드가 synchronized 키워드가 붙은 메소드를 실행할 때.
 * 결과: 여러 스레드가 서로 다른 메소드를 실행해도 먼저 진입한 스레드가 호출한 메소드가 끝날 때까지 기다림
 */
public class SynchronizedHandler {
    public static void main(String[] args) {
        SynchronizedHandler sync = new SynchronizedHandler();

        Thread thread1 = new Thread(() -> {
            System.out.println("스레드1 시작 " + LocalDateTime.now());
            sync.syncMethod1("스레드1");
            System.out.println("스레드1 종료 " + LocalDateTime.now());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("스레드2 시작 " + LocalDateTime.now());
            sync.syncMethod2("스레드2");
            System.out.println("스레드2 종료 " + LocalDateTime.now());
        });

        thread1.start();
        thread2.start();
    }

    private synchronized void syncMethod1(String msg) {
        System.out.println(msg + "의 syncMethod1 실행중" + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void syncMethod2(String msg) {
        System.out.println(msg + "의 syncMethod2 실행중" + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
