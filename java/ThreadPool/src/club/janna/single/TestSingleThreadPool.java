package club.janna.single;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleThreadPool {
	public static void main(String[] args) {
		System.out.println("main thread start");
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		threadPool.execute(new MyThread(1));
		threadPool.execute(new MyThread(2));
		threadPool.execute(new MyThread(3));
		threadPool.execute(new MyThread(4));
		threadPool.execute(new MyThread(5));
		threadPool.execute(new MyThread(6));
		System.out.println("main thread end");
	}
}
