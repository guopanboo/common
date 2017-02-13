package club.janna.single;

import java.util.Date;

public class MyThread implements Runnable {

	private int name;
	
	public MyThread(int name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(Math.round(Math.random()) * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[" + new Date() + "] " + name + " |" + Thread.currentThread().getName());
	}

}
