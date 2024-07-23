package concurrencecollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrenceCollections
{
	// Coleções sincronizadas que servem como alternativas para não precisar usar blocos com synchronized
	private static List<String> list =new CopyOnWriteArrayList<>();

	public static void main(String[] args) throws InterruptedException
	{
		MyRunnable2 runnable = new MyRunnable2();
		Thread t0 = new Thread(runnable);
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		t0.start();
		t1.start();
		t2.start();
		Thread.sleep(200);
		System.out.println(list);
	}

	public static class MyRunnable2 implements Runnable
	{
		@Override
		public void run()
		{
			list.add("Estudando sobre Threads!");
			System.out.println(Thread.currentThread().getName()+": inseriu na lista") ;
		}
	}
}

