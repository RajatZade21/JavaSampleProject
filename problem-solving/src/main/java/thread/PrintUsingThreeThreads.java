package thread;

class NumberPrinter{
    private int number=1;
    private final int max=10;

    public synchronized void print(int threadId){
        while (number<=max){
            if((number - 1)%3 == threadId){
                System.out.println(
                        Thread.currentThread().getName() + " : " +number
                );
                number++;
                notifyAll();
            }
            else {
                try {
                    wait();
                }
                catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        notifyAll();
    }

}
public class PrintUsingThreeThreads {
    public static void main(String args[]){
        NumberPrinter printer = new NumberPrinter();

        Thread t1 = new Thread(() -> printer.print(0),"Thread-1");
        Thread t2 = new Thread(() -> printer.print(1),"Thread-2");
        Thread t3 = new Thread(() -> printer.print(2),"Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
