package thread;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleThreadPool {
    private Queue<Runnable> taskQueue;
    private Thread[] threads;
    private volatile boolean isShutdown;

    public SimpleThreadPool(int numThreads) {
        this.taskQueue = new LinkedList<>();
        this.threads = new Thread[numThreads];
        this.isShutdown = false;

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
    }

    public void submit(Runnable task) {
        if (!isShutdown) {
            synchronized (taskQueue) {
                taskQueue.offer(task);
                taskQueue.notifyAll();
            }
        }
    }

    public void shutdown() {
        isShutdown = true;

        synchronized (taskQueue) {
            taskQueue.notifyAll();
        }

        for (Thread thread : threads) {
            try {
                System.out.println("join start");
                thread.join();
                System.out.println("join end");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {
                Runnable task;

                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutdown) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                if (!taskQueue.isEmpty()) {
                    task = taskQueue.poll();
                }else {
                    continue;
                }

                task.run();
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            simpleThreadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ": 작업 " + taskId + " 수행중..");

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + ": 작업 " + taskId + " 완료");
            });
        }

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        simpleThreadPool.shutdown();
    }
}
