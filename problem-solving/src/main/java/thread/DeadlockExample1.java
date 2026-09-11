package thread;

import java.util.stream.Collectors;

class RunnableTask1 implements Runnable {

    private String resource1, resource2;
    //Because private applies to the field, not the object.
    //Other classes cannot directly access Task.lock
    //The object referenced by Task.lock cannot be shared.

    public RunnableTask1(String resource1, String resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        //synchronized (resource1) does not lock the variable resource1.
        //It locks the object referenced by resource1
        synchronized (resource1) {
            System.out.println("Task 1: Printing : " + resource1);

            try { Thread.sleep(100);} catch (Exception e) {}

            synchronized (resource2) {
                System.out.println("Task 1: Printing : " + resource2);
            }
        }
    }
}

class RunnableTask2 implements Runnable {
    private String resource1, resource2;

    public RunnableTask2(String resource1, String resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        //synchronized locks the monitor of the object.
        //The variable is merely a reference to that object.
        synchronized (resource2) {
            System.out.println("Task 2: Printing : " + resource2);

            try { Thread.sleep(100);} catch (Exception e) {}

            synchronized (resource1) {
                System.out.println("Task 2: Printing : " + resource1);
            }
        }
    }
}

public class DeadlockExample1 {
    public static void main(String[] args) {
        final String resource1 = "Test1";
        final String resource2 = "Test2";

        //resource1 and resource2 are local instance variables,
        // but the objects they reference are NOT local.

        Thread t1 = new Thread(new RunnableTask1(resource1, resource2));
        Thread t2 = new Thread(new RunnableTask2(resource1, resource2));
        //task1.resource1 and task2.resource1 are different variables.
        //But they point to the same object.

        /*Example -
          Object a = new Object();
          Object b = a;
          synchronized(a) and synchronized(b)
          are locking the same object.
         */

        /*
        RunnableTask1 task1 = new RunnableTask1(new Object(), new Object());
        RunnableTask2 task2 = new RunnableTask2(new Object(), new Object());
        Task1.resource1 → Object A
        Task2.resource1 → Object C
        Task1.resource2 → Object B
        Task2.resource2 → Object D

        No circular wait between the two threads.
        So this particular deadlock won't happen.
         */

        t1.start();
        t2.start();



    }
}
