package executors;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Executors_Scheduled
{
	public static void main(String[] args)
	{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
		executor.schedule(new Tarefa(),2, TimeUnit.SECONDS);

		executor.scheduleAtFixedRate(()-> System.out.println("Periodic Task executed!"),2,3,TimeUnit.SECONDS);
		executor.scheduleWithFixedDelay(()-> System.out.println("FixedDelay Task!"),2,2,TimeUnit.SECONDS);

		System.out.println("Teste");
	}

	public static class Tarefa implements Runnable
	{

		@Override
		public void run()
		{
			String name = Thread.currentThread().getName();
			int nextInt = new Random().nextInt(1000);
			System.out.println(name + ": Executa tarefa : "+ nextInt );
		}
	}
}
