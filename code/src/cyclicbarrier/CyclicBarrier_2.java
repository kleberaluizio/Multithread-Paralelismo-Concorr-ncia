package cyclicbarrier;

import java.util.concurrent.*;

public class CyclicBarrier_2
{
	private static BlockingQueue<Double> results = new LinkedBlockingQueue<>();

	public static void main(String[] args)
	{
		Runnable finalization = () -> {
			Double finalResult = results.stream().reduce(Double::sum).get();
			System.out.println("Sum: " + finalResult + "! Thank you very much!");

		};

		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, finalization);
		ExecutorService executor = Executors.newFixedThreadPool(3);

		Runnable r1 = () -> {
			System.out.println("Process finished!" + Thread.currentThread());
			results.add(432d*3d);
			System.out.println(432d*3d);
			await(cyclicBarrier);
			System.out.println("Process finished!" + Thread.currentThread());
		};
		Runnable r2 = () -> {
			System.out.println("Process finished!" + Thread.currentThread());
			results.add(Math.pow(3, 14));
			System.out.println(Math.pow(3, 14));
			await(cyclicBarrier);
			System.out.println("Process finished!" + Thread.currentThread());
		};
		Runnable r3 = () -> {
			System.out.println("Process finished!" + Thread.currentThread());
			results.add(45d*127d/12d);
			System.out.println(45d*127d/12d);
			await(cyclicBarrier);
			System.out.println("Process finished!" + Thread.currentThread());
		};


		executor.submit(r1);
		executor.submit(r2);
		executor.submit(r3);
		executor.shutdown();
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
}
