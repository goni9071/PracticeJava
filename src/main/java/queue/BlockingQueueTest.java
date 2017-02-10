package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {
    static final BlockingQueue<Object> answer = new LinkedBlockingQueue<Object>(1);
    private final static ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws InterruptedException {
        pollTimeoutTest();
    }

    /**
     * BlockingQueue offer 메서드 timeout test
     */
    public static void offerTimeoutTest() {
        new TakeClass(answer).start();
        for (int i = 0; i < 10000; i++) {
            new OfferClass(answer, i).start();
        }
        System.out.println("End");
    }

    /**
     * BlockingQueue poll 메서드 timeout test
     * 
     * @throws InterruptedException
     */
    public static void pollTimeoutTest() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            executor.submit(new PollClass(answer, i));
        }
        System.out.println("End");
    }

}

class TakeClass extends Thread {
    private BlockingQueue<Object> answer;

    public TakeClass(BlockingQueue<Object> answer) {
        this.answer = answer;
    }

    @Override
    public void run() {
        boolean interrupted = false;
        for (;;) {
            try {
                answer.take();
                Thread.sleep(10);
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
    }
}

class PollClass extends Thread {
    private BlockingQueue<Object> answer;
    private int index;

    public PollClass(BlockingQueue<Object> answer, int index) {
        this.answer = answer;
        this.index = index;
    }

    @Override
    public void run() {
        boolean interrupted = false;
        try {
            long start = System.currentTimeMillis();
            answer.poll(1000, TimeUnit.MILLISECONDS);
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            long gap = System.currentTimeMillis() - start;
            //if (gap > 1000)
                System.out.println(String.format("[%d] poll elapsed time : %d", this.index, gap));
        } catch (InterruptedException e) {
            interrupted = true;
        }
    }
}

class OfferClass extends Thread {
    private BlockingQueue<Object> bq;
    private int index;

    public OfferClass(BlockingQueue<Object> bq, int index) {
        this.bq = bq;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            long start = System.currentTimeMillis();
            boolean result = bq.offer("hi", 1000, TimeUnit.MILLISECONDS);
            if (!result)
                System.out.println(String.format("[%d] offer elapsed time : %d", this.index, System.currentTimeMillis()
                        - start));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}