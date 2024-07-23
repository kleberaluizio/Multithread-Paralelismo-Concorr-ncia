package synchronizedpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SincronizarColecoes
{
	// Coleções sincronizadas que servem como alternativas para não precisar usar blocos com synchronized
	private static List<String> list = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException
	{
		list = Collections.synchronizedList(list); // Apenas uma thread poderá manipula-la por vez.
		MyRunnable1 runnable = new MyRunnable1();
		Thread t0 = new Thread(runnable);
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		t0.start();
		t1.start();
		t2.start();
		Thread.sleep(200);
		System.out.println(list);
	}

	public static class MyRunnable1 implements Runnable
	{
		@Override
		public void run()
		{
			list.add("Estudando sobre Threads!");
			System.out.println(Thread.currentThread().getName()+": inseriu na lista") ;
		}
	}
}
