package thread;

/**
 * ThreadA Start
 * ThreadA wait
 * Thread In Sync
 * ThreadA End
 * MainThread End
 */
public class ThreadHandler {

    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();

        synchronized (threadA) {
            threadA.start();

            Thread.sleep(500L);

            System.out.println("ThreadA wait");
            try {
                /**
                 * wait()를 걸게 되면 lock을 소유하고 있던 스레드는 lock을 release하며 WAITING 또는 TIMED_WAITING 상태로 바뀌게 된다.
                 * 이렇게 WAITING 또는 TIMED_WAITING 상태에 들어가게 된 스레드는 notify() 혹은 notifyAll() 메서드를 호출함으로써 RUNNABLE 상태로 변경될 수 있다.
                 */
                threadA.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.print("MainThread End");
    }

    static class ThreadA extends Thread {

        @Override
        public void run() {
            System.out.println("ThreadA Start");

            synchronized (this) {
                System.out.println("Thread In Synchronized");

                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("ThreadA End");
                notify();
            }
        }
    }
}
