package executors;

import java.util.Random;
import java.util.concurrent.*;

public class Executors_SingleThread_Callable
{
	public static void main(String[] args) {

		ExecutorService executor = null;

		try{
			executor = Executors.newSingleThreadExecutor();

			Future<String> future = executor.submit(new Tarefa());

			System.out.println(future.isDone()); // Aguarda a execução
			System.out.println(future.get(1, TimeUnit.SECONDS));
			System.out.println(future.isDone());

			 // Aguarda a execução

		}catch(Exception e){
			System.out.println(e);
		}finally
		{
			if (executor != null) {
				executor.shutdownNow();
			}
		}

	}

	public static class Tarefa implements Callable<String>
	{

		@Override
		public String call() throws InterruptedException
		{
			Thread.sleep(1000);
			String name = Thread.currentThread().getName();
			int nextInt = new Random().nextInt(1000);
			return name + ": Executa tarefa : "+ nextInt ;
		}
	}
}
