package volatile_and_yield;

public class Volatile2
{
	private static volatile int number = 0;
	private static volatile boolean ready = false;

	public static class MainRunnable implements Runnable {

		@Override
		public void run()
		{
			while (!ready){
				Thread.yield();
			}

			if(number != 42) {
				System.out.println(number);
			}
		}
	}

	public static void main(String[] args)
	{
		while(true){
			Thread t0 = new Thread(new MainRunnable());
			t0.start();
			Thread t1 = new Thread(new MainRunnable());
			t1.start();
			Thread t2 = new Thread(new MainRunnable());
			t2.start();

			number = 42;
			ready = true;

			while (t0.getState() != Thread.State.TERMINATED || t1.getState() != Thread.State.TERMINATED
				|| t2.getState() != Thread.State.TERMINATED)
			{
				//wait
			}
			number = 0;
			ready = false;
		}

	}
}
