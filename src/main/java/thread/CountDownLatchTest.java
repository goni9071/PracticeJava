package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch 는 특정 개수 조건을 만족해야만 실행?
 * 
 * @author user
 * 
 */
public class CountDownLatchTest {
    static final BlockingQueue<Object> answer = new LinkedBlockingQueue<Object>(1);
    private final static ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        long gap = CountDownLatchTest.time(executor, 10000, new Runnable() {

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
                    if (gap > 1500)
                        System.out.println(String.format("[%s] poll elapsed time : %d", Thread.currentThread()
                                .getName(), gap));
                } catch (InterruptedException e) {
                    interrupted = true;
                }

            }
        });
        System.out.println(gap / 1000 / 1000);
    }

    /**
     * 동시적 실행의 시간을 재는 간단한 프레임워크
     * 
     * @param executor
     * @param concurrency
     *            동시에 수행할 일 개수
     * @param action
     *            동시에 수행할 일
     * @return
     * @throws InterruptedException
     */
    public static long time(Executor executor, int concurrency, final Runnable action) throws InterruptedException {
        final CountDownLatch ready = new CountDownLatch(concurrency);
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    ready.countDown(); // 준비 OK를 타이머에게 알림
                    try {
                        start.await(); // 준비 완료를 기다린다.
                        action.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        done.countDown();
                    }

                }
            });
        }
        ready.await(); // 모든 일꾼 스레드가 준비될 때까지 기다린다.
        long startNaos = System.nanoTime();
        start.countDown(); // 동작을 시작 시킨다.
        done.await(); // 모든 일꾼 스레드가 끝날 때까지 기다린다.
        return System.nanoTime() - startNaos;

    }
}
