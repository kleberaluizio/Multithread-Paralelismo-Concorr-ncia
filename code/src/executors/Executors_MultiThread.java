package executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Executors_MultiThread
{
	public static void main(String[] args)
	{
		ExecutorService executor = null;

		try
		{
			executor = Executors.newFixedThreadPool(5);
			//executor = Executors.newCachedThreadPool();
			List<MyTask> tasks = new ArrayList<MyTask>();
			for(int i = 0; i < 10; i++) {
				tasks.add(new MyTask());
			}

			List<Future<String>> futures = executor.invokeAll(tasks);

			for (Future<String> future : futures) {
				System.out.println(future.get());
			}

//			Future<String> future1 = executor.submit(new MyTask());
//			System.out.println(future1.get());
//			Future<String> future2 = executor.submit(new MyTask());
//			Future<String> future3 = executor.submit(new MyTask());
//			System.out.println(future2.get());
//			System.out.println(future3.get());
			executor.shutdownNow();

		}catch (Exception e){
			System.out.println(e);
		}finally
		{
			if (executor != null){
				executor.shutdownNow();
			}
		}

	}

	public static class MyTask implements Callable<String>
	{

		@Override
		public String call() throws InterruptedException
		{
			String name = Thread.currentThread().getName();
			int nextInt = new Random().nextInt(1000);
			return name + ": Executa tarefa : "+ nextInt ;
		}
	}
}
