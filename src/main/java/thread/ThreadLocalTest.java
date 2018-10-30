package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
  private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
  private static final AtomicInteger threadCount = new AtomicInteger();
  private static Integer threadGlobal = new Integer(0);

  public static void main(String[] args) throws InterruptedException {
    test();
  }

  public static void test() throws InterruptedException {
    ExecutorService executor = Executors.newCachedThreadPool();
    for (int i = 0; i < 100; i++) {
      executor.execute(new Runnable() {
        @Override
        public void run() {
          int i = threadGlobal = threadCount.incrementAndGet();
          threadLocal.set(i);
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(String.format("Thread No : %d, Thread Loacl : %d, Thread Global : %d", i,
              threadLocal.get(), threadGlobal));
        }
      });
    }
    System.out.println("End");
  }
}
