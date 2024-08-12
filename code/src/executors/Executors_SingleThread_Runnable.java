package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Executors_SingleThread_Runnable
{
	public static void main(String[] args) {

		ExecutorService executor = null;

		try{
			executor = Executors.newSingleThreadExecutor();
			executor.execute(new Tarefa());
			executor.execute(new Tarefa());
			executor.execute(new Tarefa());
			Future<?> future = executor.submit(new Tarefa());
			System.out.println(future.isDone());
			executor.close();
			executor.awaitTermination(10, TimeUnit.SECONDS);
			System.out.println(future.isDone());

		}catch(Exception e){
			System.out.println(e);
		}finally
		{
			if (executor != null) {
				executor.shutdownNow();
			}
		}

	}

	public static class Tarefa implements Runnable {

		@Override
		public void run()
		{
			String name = Thread.currentThread().getName();
			System.out.println("Executa tarefa "+ name);
		}
	}
}
