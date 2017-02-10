package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueService implements Runnable {
    private final BlockingQueue<QueueInterface> queryList = new LinkedBlockingQueue<QueueInterface>();
    private final ExecutorService executor = Executors.newFixedThreadPool(100);
    private final Logger logger = LoggerFactory.getLogger("QUEUE");
    private Logger errLogger = LoggerFactory.getLogger("ROOT");

    public QueueService() {
        Thread queueService = new Thread(this, "queueService");

        queueService.start();
        logger.info("queueService start");
    }

    public void add(QueueInterface qif) {
        try {
            queryList.put(qif);
        } catch (InterruptedException ignore) {
            logger.error(ignore.toString());
        }
    }

    @Override
    public void run() {
        boolean interrupted = false;
        while (true) {

            QueueInterface qif = null;
            try {
                qif = queryList.take();
                if (queryList.size() > 50)
                    logger.warn("[비동기큐] : {} 개", queryList.size());

                if (interrupted) {
                    Thread.currentThread().interrupt();
                    return;
                }
            } catch (InterruptedException ignore) {
                interrupted = true;
                logger.debug("## InterruptedException : {}", ignore.toString());
            }
            if (qif != null) {
                executor.submit(new Task(qif));
            } else {
                logger.error("qif is null");
            }

        }

    }

    private class Task implements Runnable {

        QueueInterface qif;

        public Task(QueueInterface qif) {
            this.qif = qif;
            // logger.debug("Thread : {}", Thread.currentThread().getName());
        }

        @Override
        public void run() {
            try {
                long start = System.currentTimeMillis();
                qif.doAsyncProcess();
                long end = System.currentTimeMillis();
                logger.info("{},{},{},{}",
                        new Object[] { qif.getId(), qif.getParameter(), end - start, end - qif.getStartTime() });
            } catch (Exception e) {
                errLogger.error(qif.getId(), e);
                logger.error("{} [{}]", qif.getId(), e.getMessage());
            }
        }
    }
}
