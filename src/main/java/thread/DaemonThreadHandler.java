package thread;

/**
 * 데몬 스레드는 백그라운드에서 동작하여 주 스레드(main 스레드)를 보조하거나 특정 작업을 처리
 *
 * 결과
 * DaemonThread 시작
 * MainThread 종료
 *
 */
public class DaemonThreadHandler {

    public static void main(String[] args) throws InterruptedException {
        Thread daemonThread = new Thread(() -> {
            while(true) {
                System.out.println("DaemonThread 시작");
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("DaemonThread 종료"); //출력되지 않음.
            }
        });

        daemonThread.setDaemon(true); // 데몬 스레드로 설정
        daemonThread.start();

        Thread.sleep(3000L);

        System.out.println("MainThread 종료");

    }
}
