import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadCreationExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": RunnableTest");
        System.out.println("Calling Thread instance with Thread class start method");

        // Anonymous Runnable
        Runnable task1 = new Runnable(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        };

        // Passing a Runnable when creating a new thread
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        });

        // Lambda Runnable
        Runnable runnableTask = () -> {
            System.out.println(Thread.currentThread().getName() + " is running inside Runnable");
        };

        Thread thread1 = new Thread(task1);
        thread1.start();
        thread2.start();
        new Thread(runnableTask).start();

        Thread.sleep( 10000);
        System.out.println("************************");
        System.out.println("Calling Callable instance with FutureTask object");

        // Lambda Callable
        Callable<Integer> callableTask = () -> {
            Random randObj = new Random();
            Integer randNo = randObj.nextInt(10);
            return randNo;
        };

        FutureTask randomNoTasks = new FutureTask(callableTask);
        randomNoTasks.run();
        System.out.println("Result from Callable thread " + randomNoTasks.get());

        Thread.sleep( 10000);
        System.out.println("************************");
        System.out.println("Using Executor Service for Execution");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Submit a callable task - returns a Future
        Future<Integer> callableFuture = executor.submit(callableTask);

        // Execute a runnable task - doesn't return anything
        executor.execute(runnableTask);

        // Retrieve the result from the callable task (blocks until finished)
        Integer callableResult = callableFuture.get();
        System.out.println("Callable result: " + callableResult);

        executor.shutdown();
    }
}
