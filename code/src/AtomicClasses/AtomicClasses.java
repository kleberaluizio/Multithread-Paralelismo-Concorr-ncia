package AtomicClasses;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClasses
{
	static AtomicInteger i = new AtomicInteger(-1);

	public static void main(String[] args)
	{
		MyRunnable runnable = new MyRunnable();

		Thread t0 = new Thread(runnable);
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);

		t0.start();
		t1.start();
		t2.start();

		System.out.println(i);
	}

	public static class MyRunnable implements Runnable {

		@Override
		public void run()
		{
			String name = Thread.currentThread().getName();
			System.out.println(name + ": " + i.incrementAndGet());
		}
	}

	public static void printThreadName()
	{

	}
}
