package thread;

public class WaitTest {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            tes2();
        }
    }

    public static void test1() throws InterruptedException {
        while (true) {
            long start = System.currentTimeMillis();
            synchronized (lock) {
                lock.wait(20);
            }
            System.out.println(" RECEIVE : " + (System.currentTimeMillis() - start));
        }
    }

    public static void tes2() throws InterruptedException {
        new ShowThread("1").start();//
        new ShowThread("2").start();//
        new ShowThread("3").start();//
        new ShowThread("4").start();//
        new ShowThread("5").start();//
        Thread.sleep(1000);
        synchronized (lock) {
            lock.notifyAll();
        }
        System.out.println("");
    }

    public static String waitTest() throws InterruptedException {
        synchronized (lock) {
            lock.wait();
            return "hi";
        }
    }

}

class ShowThread extends Thread {// java 에서는 Thread 도 인스턴스로 표현
    String threadname;

    public ShowThread(String name) {
        threadname = name;
    }

    public void run() {// Thread 클래스의 run메소드를 오버라이딩(재정의) 한것이다.
        System.out.print(threadname);
    }
}
