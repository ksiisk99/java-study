package lock.synchro;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
/**
 * 서로 다른 인스턴스의 서로 다른 스레드가 synchronized 키워드가 붙은 메소드를 실행할 때.
 * 결과: 각각 독립된 메소드 실행함 -> 동기화x
 * synchronized 메소드는 인스턴스 단위로 lock
 */
public class Synchronized2Handler {
    public static void main(String[] args) {
        Synchronized2Handler sync = new Synchronized2Handler();
        Synchronized2Handler sync2 = new Synchronized2Handler();

        Thread thread1 = new Thread(() -> {
            System.out.println("스레드1 시작 " + LocalDateTime.now());
            sync.syncMethod1("스레드1");
            System.out.println("스레드1 종료 " + LocalDateTime.now());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("스레드2 시작 " + LocalDateTime.now());
            sync2.syncMethod2("스레드2");
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
