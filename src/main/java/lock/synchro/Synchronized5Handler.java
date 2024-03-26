package lock.synchro;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 같은 인스턴스가 서로 다른 스레드로 서로 다른 메소드 안에 synchronized(object) block을 실행할 경우
 * 결과: synchronized(Object) 는 같은 인스턴스여도 공유 안 함.
 */
public class Synchronized5Handler {
    private static Object object = new Object();

    public static void main(String[] args) {
        Synchronized5Handler sync = new Synchronized5Handler();

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


    private void syncMethod1(String msg) {

        synchronized (this) {
            System.out.println(msg + "의 syncMethod1 실행중" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void syncMethod2(String msg) {
        synchronized (msg) {
            System.out.println(msg + "의 syncMethod2 실행중" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
