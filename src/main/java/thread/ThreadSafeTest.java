package thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadSafeTest {
    private final static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        test3();
    }

    public static void test2() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            final BlockingQueue<Object> answer = new LinkedBlockingQueue<Object>();
            answer.put(i);
            CountDownLatchTest.time(executor, 1000, new Runnable() {
                @Override
                public void run() {
                    Object poll = answer.poll();
                    if (poll != null) {
                        System.out.println(poll);
                    }
                }
            });
        }
        System.out.println("End");
    }

    public static void test3() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            final Queue<Object> answer = new LinkedList<Object>();
            answer.offer(i);
            CountDownLatchTest.time(executor, 1000, new Runnable() {
                @Override
                public void run() {
                    Object poll = answer.poll();
                    if (poll != null) {
                        System.out.println(poll);
                    }
                }
            });
        }
        System.out.println("End");
    }
}
