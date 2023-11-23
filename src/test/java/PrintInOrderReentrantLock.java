import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintInOrderReentrantLock {

    @Test
    public void test() {
        PrintInOrderReentrantLock foo = new PrintInOrderReentrantLock();

        Runnable printFirst = () -> System.out.print("first");
        Runnable printSecond = () -> System.out.print("second");
        Runnable printThird = () -> System.out.print("third");

        Thread t1 = new Thread(() -> {
            try {
                foo.first(printFirst);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                foo.second(printSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                foo.third(printThird);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t3.start();
        t1.start();
        t2.start();
    }

    private int threadNumber;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void PrintInOrderReentrantLock() {
        this.threadNumber = 1;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            this.threadNumber++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while(this.threadNumber != 2) {
                condition.await();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            this.threadNumber++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while(this.threadNumber != 3) {
                condition.await();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            this.threadNumber++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
