package thread;

public class DaemonTest extends Thread {
    private int i = 0;

    @Override
    public void run() {
        while (i++ < 10) {
            try {
                Thread.sleep(1000);
                System.out.println("a");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("쓰레드 종료");
    }

    public static void main(String[] args) {
        DaemonTest daemonTest = new DaemonTest();
        // daemonTest.setDaemon(true);
        daemonTest.setDaemon(false);
        daemonTest.start();
    }
}
