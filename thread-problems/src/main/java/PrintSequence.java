public class PrintSequence {
    private static final Object lock = new Object();
    private static int number = 1;
    private static final int MAX_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new PrintRunnable(1));
        Thread thread2 = new Thread(new PrintRunnable(2));
        Thread thread3 = new Thread(new PrintRunnable(3));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

    static class PrintRunnable implements Runnable{
        private final int reminder;

        private PrintRunnable(int reminder){
            this.reminder = reminder % 3;
        }

        @Override
        public void run(){
            synchronized (lock){
                while (number <= MAX_NUMBER){
                    if(number % 3 == reminder){
                        System.out.println("THREAD - " + Thread.currentThread().getName() + " : " + number);
                        number++;
                        lock.notifyAll();  // Notify other threads after printing
                    }
                    else {
                        try{
                            lock.wait(); // Wait for your turn
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

    }
}
