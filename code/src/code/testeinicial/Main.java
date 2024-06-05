package code.testeinicial;

public class Main
{
	public static void main(String[] args) {
		// Submeter algumas tarefas
		for (int i = 1; i <= 20; i++) {
			TaskManager.submitTask(new Task(i));
		}

		// Esperar um pouco e então fazer shutdown
		try {
			Thread.sleep(5000); // 5 segundos
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		TaskManager.shutdown();

		// Submeter mais tarefas após o shutdown
		for (int i = 21; i <= 30; i++) {
			TaskManager.submitTask(new Task(i)); // Isso recriará o ExecutorService
		}
		TaskManager.shutdown();
	}
}

class Task implements Runnable {
	private final int taskId;

	public Task(int taskId) {
		this.taskId = taskId;
	}

	@Override
	public void run() {
		System.out.println("Task ID : " + this.taskId + " performed by " + Thread.currentThread().getName());
		try {
			Thread.sleep(2000); // Simulate work
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}