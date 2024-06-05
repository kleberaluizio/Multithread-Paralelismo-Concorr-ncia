package code.testeinicial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskManager
{
	private static ExecutorService executorService = createExecutorService();

	private TaskManager() {}

	private static synchronized ExecutorService createExecutorService()
	{
		if (executorService == null || executorService.isShutdown() || executorService.isTerminated())
		{
			executorService = Executors.newFixedThreadPool(4);
		}
		return executorService;
	}

	public static synchronized void submitTask(Runnable task)
	{
		if (executorService.isShutdown() || executorService.isTerminated())
		{
			executorService = createExecutorService();
			System.out.println("Creating Executor Service");
		}
		executorService.submit(task);
	}

	public static synchronized void shutdown()
	{
		if (executorService != null && !executorService.isShutdown())
		{
			System.out.println("Executor Service Terminated [Before]");
			executorService.shutdown();
			try
			{
				if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
				{
					System.out.println("Executor Service Terminated");
					executorService.shutdownNow();
				}
			}
			catch (InterruptedException e)
			{
				System.out.println("Executor Service Terminated [Interrupted]");
				executorService.shutdownNow();
				Thread.currentThread().interrupt();
			}
		}
	}
}
