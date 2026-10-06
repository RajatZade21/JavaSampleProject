package thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class producer implements Runnable{
    BlockingDeque<Integer> obj;

    public producer(BlockingDeque<Integer> deque){
        obj = deque;
    }

    @Override
    public void run(){
        for(int i=1;i <= 4;i++){
            try {
                obj.put(i);
                System.out.println("Producer " + i);
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}

class consumer implements Runnable{
    BlockingDeque<Integer> obj;

    int taken=-1;
    public consumer(BlockingDeque<Integer> deque){
        obj = deque;
    }

    @Override
    public void run(){
        while (taken!=4){
            try {
                taken = obj.take();
                System.out.println("Consumer " + taken);
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args){

        // Create an ArrayBlockingQueue object with capacity
        BlockingDeque<Integer> deque = new LinkedBlockingDeque<Integer>(4);

        consumer consumer = new consumer(deque);
        producer producer = new producer(deque);

        Thread t1 = new Thread(consumer);
        Thread t2 = new Thread(producer);

        t1.start();
        t2.start();
    }
}
