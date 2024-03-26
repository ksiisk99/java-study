package lock.synchro;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 서로 다른 스레드로 서로 다른 static 메소드 안에 synchronized(object) block을 실행할 경우
 * 결과: 같은 객체 object이므로 동기화 됨.
 */
public class Synchronized6Handler {
    private static Object object = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("스레드1 시작 " + LocalDateTime.now());
            syncMethod1("스레드1");
            System.out.println("스레드1 종료 " + LocalDateTime.now());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("스레드2 시작 " + LocalDateTime.now());
            syncMethod2("스레드2");
            System.out.println("스레드2 종료 " + LocalDateTime.now());
        });

        thread1.start();
        thread2.start();
    }


    private static void syncMethod1(String msg) {
        synchronized (object) {
            System.out.println(msg + "의 syncMethod1 실행중" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void syncMethod2(String msg) {
        synchronized (object) {
            System.out.println(msg + "의 syncMethod2 실행중" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
