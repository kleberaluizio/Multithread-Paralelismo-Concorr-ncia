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

	public static void printThreadName()
	{
		synchronized(Synchronized_1.class){ // Apenas uma thread na minha JVM poderá acessar esses recursos. O bloqueio acontece na classe.
			i++;
			String name = Thread.currentThread().getName();
			System.out.println(name + ": " + i);
		}
	}
	public static class MyRunnable implements Runnable {

		@Override
		public void run()
		{
			printThreadName();
		}
	}
}
