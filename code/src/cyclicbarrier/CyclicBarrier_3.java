package cyclicbarrier;

import java.util.concurrent.*;

public class CyclicBarrier_3
{
	private static BlockingQueue<Double> results = new LinkedBlockingQueue<>();
	private static ExecutorService executor;
	private static Runnable r1;
	private static Runnable r2;
	private static Runnable r3;

	public static void main(String[] args)
	{
		Runnable finalization = () -> {
			Double finalResult = 0d;
			finalResult += results.poll();
			finalResult += results.poll();
			finalResult += results.poll();
			System.out.println("Sum: " + finalResult + "! Thank you very much!");
			System.out.println("-----------------");
			restart();
		};

		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, finalization);

		executor = Executors.newFixedThreadPool(3);
		r1 = () -> {
			results.add(432d*3d);
			System.out.println(432d*3d);
			await(cyclicBarrier);
		};
		r2 = () -> {
			results.add(Math.pow(3, 14));
			System.out.println(Math.pow(3, 14));
			await(cyclicBarrier);
		};
		r3 = () -> {
			results.add(45d*127d/12d);
			System.out.println(45d*127d/12d);
			await(cyclicBarrier);
		};

		restart();
	}

	private static void await(CyclicBarrier cyclicBarrier)
	{
		try
		{
			cyclicBarrier.await();
		}
		catch (InterruptedException | BrokenBarrierException e)
		{
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
	private static void restart()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
		executor.submit(r1);
		executor.submit(r2);
		executor.submit(r3);
	}
}

