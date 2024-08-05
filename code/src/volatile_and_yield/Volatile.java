package volatile_and_yield;

public class Volatile
{
	private static int number = 0;
	private static boolean ready = false;

	public static class MainRunnable implements Runnable {

		@Override
		public void run()
		{
			while (!ready){
				Thread.yield();
			}

			System.out.println(number);
		}
	}
	public static void main(String[] args)
	{
		Thread t0 = new Thread(new MainRunnable());
		t0.start();
		System.out.println(number);
		number = 42;
		ready = true;
	}
}
