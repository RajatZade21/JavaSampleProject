public class FibonacciExample {
    public static void main(String[] args){
        Fibonacci(10);
        System.out.println();
        RecursiveFibonacci(15);
    }

    static void Fibonacci(int N){
        int num1 = 0;
        int num2 = 1;
        for(int i=0;i<N;i++){
            System.out.print(num1 + " ");
            int num3 = num1 + num2;
            num1=num2;
            num2=num3;
        }
    }

    static void RecursiveFibonacci(int N){
        for(int i=0;i<N;i++){
            System.out.print(fib(i) + " ");
        }
    }

    static int fib(int n){
        if(n <= 1)
            return n;

        return fib(n - 1) + fib(n - 2);
    }
}
