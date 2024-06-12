package criar_threads;

public class Main
{
	public static void main(String[] args)
	{
		// Thread atual
		Thread thread = Thread.currentThread();
		printThreadName(thread);

		// Nova Thread 0
		Thread thread0 = new Thread(()-> {
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				throw new RuntimeException(e);
			}
			printThreadName(Thread.currentThread());
		});

		// Nova Thread 1
		Thread thread1 = new Thread(()-> {
			printThreadName(Thread.currentThread());
		});

		// Nova Thread 2
		Thread thread2 = new Thread(()-> {
			printThreadName(Thread.currentThread());
		});

		// Inicializa threads
		thread0.start();
		thread1.start();

	}
	private static void printThreadName(Thread thread) {
		System.out.println(thread.getName());
	}
}
