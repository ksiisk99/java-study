package lock.synchro;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
/**
 * 같은 클래스에 서로 다른 스레드가 static synchronized 키워드가 붙은 메소드를 실행할 때.
 * 결과: 동기화됨
 * static synchronized는 클래스 단위 lock
 *
 * 이 상황에서 인스턴스가 syncrhonized 메소드를 실행한다면?
 * 결과: 같은 클래스 내여도 인스턴스 단위는 클래수 단위와 lock 공유가 안됨.
 */
public class Synchronized3Handler {
    public static void main(String[] args) {
        Synchronized3Handler sync = new Synchronized3Handler();

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

        Thread thread3 = new Thread(() -> {
            System.out.println("스레드3 시작 " + LocalDateTime.now());
            sync.syncMethod3("스레드3");
            System.out.println("스레드3 종료 " + LocalDateTime.now());
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static synchronized void syncMethod1(String msg) {
        System.out.println(msg + "의 syncMethod1 실행중" + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void syncMethod2(String msg) {
        System.out.println(msg + "의 syncMethod2 실행중" + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void syncMethod3(String msg) {
        System.out.println(msg + "의 syncMethod3 실행중" + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
