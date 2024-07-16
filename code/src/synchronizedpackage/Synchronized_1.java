package synchronizedpackage;

public class Synchronized_1
{
	static int i = -1;

	public static void main(String[] args)
	{
		MyRunnable runnable = new MyRunnable();

		Thread t0 = new Thread(runnable);
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		Thread t3 = new Thread(runnable);
		Thread t4 = new Thread(runnable);

		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		System.out.println(i);
	}

	public static class MyRunnable implements Runnable {
//		@Override
//		public synchronized void run()
//		{
//			i++;
//			String name = Thread.currentThread().getName();
//			System.out.println(name + ": " + i);
//		}

		static Object lock0 = new Object();
		static Object lock1 = new Object();
		@Override
		public void run()
		{
			synchronized (lock0)
			{
				i++;
				String name = Thread.currentThread().getName();
				System.out.println(name + ": " + i);
			}

			synchronized (lock1)
			{
				i++;
				String name = Thread.currentThread().getName();
				System.out.println(name + ": " + i);
			}
		}
	}
}
