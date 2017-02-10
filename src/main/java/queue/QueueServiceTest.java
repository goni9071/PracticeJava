package queue;

public class QueueServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QueueService qs = new QueueService();
		for (int i = 0; i < 100; i++) {
			qs.add(new QueueInterface(String.valueOf(i)) {
				@Override
				public void doAsyncProcess() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

	}
}
