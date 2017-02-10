package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import thread.CountDownLatchTest;

public class CountQueueTest {
    static final BlockingQueue<Object> answer = new LinkedBlockingQueue<Object>();
    private final static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    public static void test1() throws InterruptedException {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (;;) {
                    try {
                        answer.take();
                        int i = 0;
                        for (; i < 100; i++) {
                            if (answer.poll(500, TimeUnit.MILLISECONDS) == null) {
                                break;
                            }
                        }
                        if (i > 0)
                            System.out.println(i + ":" + answer.size());
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                System.out.println("Thread End");
            }
        });
        t.start();

        CountDownLatchTest.time(executor, 100, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    answer.offer("hi");
                }
            }
        });
    }
}
