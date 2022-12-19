package cs.jou;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

public class MainTest {

    @Test
    void fun() throws InterruptedException {
        LinkedBlockingQueue<Object> a = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Object> b = new LinkedBlockingQueue<>();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("a");
            try {
                a.put("b");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(a.take());
                b.put("c");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(b.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(5000);
    }

    @Test
    void fun1() throws InterruptedException, ExecutionException {
        LinkedBlockingQueue<Object> a = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Object> b = new LinkedBlockingQueue<>();

        CompletableFuture<Void> a1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("a");
            try {
                a.put("b");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Void> a2 = CompletableFuture.runAsync(() -> {
            try {
                System.out.println(a.take());
                b.put("c");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Void> a3 = CompletableFuture.runAsync(() -> {
            try {
                System.out.println(b.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        a3.get();
    }
}
