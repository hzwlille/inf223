package inf223;


public class Semaphore {
    int count;

    Semaphore (int count) {
        this.count = count;
    }

    public synchronized void acquire() throws InterruptedException {
        count--;



        // Suspend until resources available

    }

    public synchronized void release() throws InterruptedException {
        count++;



        // Resume a thread when needed

    }
}
